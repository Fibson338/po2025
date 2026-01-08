package com.example.samochod;

public class Silnik extends Komponent {

    private int obroty;
    private boolean włączony;

    public Silnik(String nazwa, double cena, double waga) {
        super(nazwa, cena, waga);
        this.obroty = 0;
        this.włączony = false;
    }

    public void włącz() {
        włączony = true;
        if (obroty == 0) {
            obroty = 0;
        }
    }

    public void wyłącz() {
        włączony = false;
        obroty = 0;
    }

    public void zwiększObroty() {
        if (włączony && obroty < 3000) {
            obroty += 250;
            if (obroty > 3000) obroty = 3000;
        }

}

    public void zmniejszObroty() {
        if (włączony && obroty != 0) {
            obroty -= 250;
            if (obroty < 0) {
                obroty = 0;
            }
        }
    }

    public boolean isWłączony() {
        return włączony;
    }

    public int getObroty() {
        return obroty;
    }

    public int zeroObroty(){
        return obroty = 0;
    }
}
