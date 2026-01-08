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
    private static final int IDLE_RPM = 0;

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

    public void addListener(Listener l) { listeners.add(l); }
    public void removeListener(Listener l) { listeners.remove(l); }

    private void notifyListeners() {
        var snapshot = new java.util.ArrayList<>(listeners);
        for (Listener l : snapshot) {
            if (l != null) {
                l.update();
            }
        }
    }




    public void jedzDo(Pozycja nowaPozycja) {
        if (!isWłączony()) return;
        this.cel = nowaPozycja;
    }

    public Pozycja getPozycja() { return pozycja; }

    @Override
    public void run() {
        double deltat = 0.1; // 100 ms

        while (running) {
            if (cel != null && prędkość > 0) {

                double dx = cel.getX() - pozycja.getX();
                double dy = cel.getY() - pozycja.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < 1) {
                    pozycja.set(cel.getX(), cel.getY());
                    cel = null;
                } else {
                    double v = Math.max(prędkość, 20);
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
        silnik.zeroObroty();
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
        silnik.zeroObroty();
        notifyListeners();
    }

    public void zmniejszBieg() {
        if (!isWłączony()) return;
        if (!sprzęgło.isWciśnięte()) return;

        skrzynia.zmniejszBieg();

        silnik.zeroObroty();

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
        if (bieg > 0 && prędkość < minPredkosc(bieg)) return;

        sprzęgło.zwolnij();
        notifyListeners();
    }

    public void dodajGazu() {
        if (!isWłączony()) return;

        if (silnik.getObroty() < MAX_RPM) {
            silnik.zwiększObroty();
            while (silnik.getObroty() > MAX_RPM) silnik.zmniejszObroty();
        }

        if (sprzęgło.isWciśnięte()) {
            notifyListeners();
            return;
        }

        int bieg = skrzynia.getBieg();
        if (bieg <= 0) {
            notifyListeners();
            return;
        }
        if (silnik.getObroty() >= MAX_RPM) {
            notifyListeners();
            return;
        }

        double vmax = maxPredkosc(bieg);

        if (prędkość < vmax) {
            prędkość = Math.min(vmax, prędkość + przyspieszenie(bieg));
        }

        notifyListeners();
    }

    public void luzujGazu() {
        if (!isWłączony()) return;
        silnik.zmniejszObroty();
        if (prędkość >= 5) prędkość -= 5;
        else prędkość = 0;

        notifyListeners();
    }

    private double maxPredkosc(int bieg) {
        return switch (bieg) {
            case 1 -> 30;
            case 2 -> 60;
            case 3 -> 90;
            case 4 -> 130;
            case 5 -> 180;
            case 6 -> 240;
            default -> 0;
        };
    }

    private double minPredkosc(int bieg) {
        return switch (bieg) {
            case 1 -> 0;
            case 2 -> 15;
            case 3 -> 35;
            case 4 -> 55;
            case 5 -> 75;
            case 6 -> 105;
            default -> 0;
        };
    }

    private double przyspieszenie(int bieg) {
        return switch (bieg) {
            case 1 -> 10;
            case 2 -> 8;
            case 3 -> 6;
            case 4 -> 4.5;
            case 5 -> 3.5;
            case 6 -> 2.5;
            default -> 0;
        };
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
