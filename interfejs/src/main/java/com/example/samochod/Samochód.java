package com.example.samochod;

import java.util.ArrayList;
import java.util.List;

public class Samochód extends Thread {

    private String model;
    private String numerRejestracyjny;
    private double waga;
    private double prędkość;

    private final Silnik silnik;
    private final SkrzyniaBiegów skrzynia;
    private final Sprzęgło sprzęgło;

    private final Pozycja pozycja;
    private volatile Pozycja cel;
    private volatile boolean running = true;

    private final List<Listener> listeners = new ArrayList<>();

    private static final int MAX_RPM = 3000;
    private static final int MIN_RPM_HIGH_GEARS = 2000;

    public Samochód(String model,
                    String numerRejestracyjny,
                    double waga,
                    Silnik silnik,
                    SkrzyniaBiegów skrzynia,
                    Sprzęgło sprzęgło,
                    double startX,
                    double startY) {

        this.model = model;
        this.numerRejestracyjny = numerRejestracyjny;
        this.waga = waga;
        this.prędkość = 0.0;

        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.sprzęgło = sprzęgło;

        this.pozycja = new Pozycja(startX, startY);

        start();
    }

    public void addListener(Listener l) {
        if (l != null && !listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    private void notifyListeners() {
        var snapshot = new java.util.ArrayList<>(listeners);
        for (Listener l : snapshot) {
            if (l != null) l.update();
        }
    }

    public void jedzDo(Pozycja nowaPozycja) {
        if (!isWłączony()) {
            throw new IllegalStateException("Nie pojadę! Włącz silnik.");
        }
        this.cel = nowaPozycja;
        notifyListeners();
    }


    public Pozycja getPozycja() { return pozycja; }

    @Override
    public void run() {
        double deltat = 0.1;

        while (running) {
            if (cel != null && Math.abs(prędkość) > 0) {

                double dx = cel.getX() - pozycja.getX();
                double dy = cel.getY() - pozycja.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < 1) {
                    pozycja.set(cel.getX(), cel.getY());
                    cel = null;
                } else {
                    double v = Math.max(Math.abs(prędkość), 20);
                    pozycja.przesun(v * deltat * dx / dist,
                            v * deltat * dy / dist);
                }

                notifyListeners();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }
    }

    public void włącz() {
        silnik.włącz();
        silnik.setObroty(0);
        notifyListeners();
    }

    public void wyłącz() {
        silnik.wyłącz();
        prędkość = 0;
        skrzynia.setBieg(0);
        cel = null;
        notifyListeners();
    }

    public boolean isWłączony() {
        return silnik.isWłączony();
    }

    public void zwiększBieg() {
        if (!isWłączony()) return;
        if (!sprzęgło.isWciśnięte()) return;
        skrzynia.zwiększBieg();
        notifyListeners();
    }

    public void zmniejszBieg() {
        if (!isWłączony()) return;
        if (!sprzęgło.isWciśnięte()) return;
        skrzynia.zmniejszBieg();
        notifyListeners();
    }

    public void zaciągnijSprzęgło() {
        if (!isWłączony()) return;
        sprzęgło.zaciągnij();
        notifyListeners();
    }

    public void zwolnijSprzęgło() {
        if (!isWłączony()) return;

        int bieg = skrzynia.getBieg();

        if (bieg >= 2) {
            double minV = minPredkoscDlaBiegu(bieg);

            if (Math.abs(prędkość) < minV) {
                silnik.setObroty(2000);
                double sign = (bieg < 0) ? -1.0 : 1.0;
                prędkość = sign * minV;
            }
        }

        sprzęgło.zwolnij();
        notifyListeners();
    }

    public void dodajGazu() {
        if (!isWłączony()) return;

        silnik.zwiększObroty();

        if (sprzęgło.isWciśnięte()) {
            notifyListeners();
            return;
        }

        int bieg = skrzynia.getBieg();
        if (bieg == 0) {
            notifyListeners();
            return;
        }

        ustawPredkoscZObrotow();
        notifyListeners();
    }

    public void luzujGazu() {
        if (!isWłączony()) return;

        silnik.zmniejszObroty();

        if (sprzęgło.isWciśnięte() || skrzynia.getBieg() == 0) {
            if (Math.abs(prędkość) >= 2) prędkość -= Math.signum(prędkość) * 2;
            else prędkość = 0;
            notifyListeners();
            return;
        }

        ustawPredkoscZObrotow();
        notifyListeners();
    }

    private void ustawPredkoscZObrotow() {
        int bieg = skrzynia.getBieg();
        if (bieg == 0) return;

        double vmax = maxPredkosc(bieg);
        if (vmax <= 0) return;

        int rpm = silnik.getObroty();
        if (rpm < 0) rpm = 0;
        if (rpm > MAX_RPM) rpm = MAX_RPM;

        double targetAbs = (rpm / (double) MAX_RPM) * vmax;

        if (bieg >= 2) {
            double minV = minPredkoscDlaBiegu(bieg);
            if (targetAbs > 0 && targetAbs < minV) {
                targetAbs = minV;
                silnik.setObroty(MIN_RPM_HIGH_GEARS);
            }
        }

        double sign = (bieg < 0) ? -1.0 : 1.0;
        double target = sign * targetAbs;

        double maxStep = 8.0;
        double delta = target - prędkość;

        if (Math.abs(delta) <= maxStep) prędkość = target;
        else prędkość += Math.signum(delta) * maxStep;
    }

    private double maxPredkosc(int bieg) {
        return switch (bieg) {
            case -1 -> 20;
            case 1 -> 30;
            case 2 -> 60;
            case 3 -> 90;
            case 4 -> 130;
            case 5 -> 180;
            default -> 0;
        };
    }

    private double minPredkoscDlaBiegu(int bieg) {
        if (bieg < 2) return 0;
        double vmax = maxPredkosc(bieg);
        return (MIN_RPM_HIGH_GEARS / (double) MAX_RPM) * vmax;
    }

    public String getModel() { return model; }
    public String getNumerRejestracyjny() { return numerRejestracyjny; }
    public double getWaga() { return waga; }
    public double getPrędkość() { return prędkość; }

    public Silnik getSilnik() { return silnik; }
    public SkrzyniaBiegów getSkrzynia() { return skrzynia; }
    public Sprzęgło getSprzęgło() { return sprzęgło; }

    @Override
    public String toString() {
        return model + " (" + numerRejestracyjny + ")";
    }
}
