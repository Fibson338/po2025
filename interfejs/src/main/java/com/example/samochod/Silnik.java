package com.example.samochod;

public class Silnik extends Komponent {

    private int obroty;
    private boolean włączony;

    private static final int MAX_RPM = 3000;
    private static final int IDLE_RPM = 900;

    public Silnik(String nazwa, double cena, double waga) {
        super(nazwa, cena, waga);
        this.obroty = 0;
        this.włączony = false;
    }

    public void włącz() {
        włączony = true;
        if (obroty < IDLE_RPM) obroty = IDLE_RPM;
    }

    public void wyłącz() {
        włączony = false;
        obroty = 0;
    }

    public void zwiększObroty() {
        if (włączony && obroty < MAX_RPM) {
            obroty += 250;
            if (obroty > MAX_RPM) obroty = MAX_RPM;
        }
    }

    public void zmniejszObroty() {
        if (włączony) {
            obroty -= 250;
            if (obroty < 0) obroty = 0;
        }
    }


    public void setObroty(int rpm) {
        if (!włączony) { obroty = 0; return; }
        if (rpm < 0) rpm = 0;
        if (rpm > 3000) rpm = 3000;
        obroty = rpm;
    }


    public boolean isWłączony() {
        return włączony;
    }

    public int getObroty() {
        return obroty;
    }
}
