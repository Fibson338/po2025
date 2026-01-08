package com.example.samochod;

public class SkrzyniaBiegów extends Komponent {

    private int bieg;

    public SkrzyniaBiegów(String nazwa, double cena, double waga) {
        super(nazwa, cena, waga);
        this.bieg = 0;
    }

    public void zwiększBieg() {
        if (bieg < 6) {
            bieg++;
        }
    }

    public void zmniejszBieg() {
        if (bieg > -1) {
            bieg--;
        }
    }

    public int getBieg() {
        return bieg;
    }

    public void setBieg(int bieg) {
        this.bieg = bieg;
    }
}
