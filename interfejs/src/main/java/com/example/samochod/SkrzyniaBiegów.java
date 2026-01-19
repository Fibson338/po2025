package com.example.samochod;

public class SkrzyniaBiegów extends Komponent {

    private int bieg;

    public SkrzyniaBiegów(String nazwa, double cena, double waga) {
        super(nazwa, cena, waga);
        this.bieg = 0;
    }

    public void zwiększBieg() {
        setBieg(bieg + 1);
    }

    public void zmniejszBieg() {
        setBieg(bieg - 1);
    }

    public int getBieg() {
        return bieg;
    }

    public void setBieg(int bieg) {
        if (bieg < -1) bieg = -1;
        if (bieg > 5) bieg = 5;
        this.bieg = bieg;
    }
}
