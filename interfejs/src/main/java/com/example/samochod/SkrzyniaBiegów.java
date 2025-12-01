package com.example.samochod;

public class SkrzyniaBiegów extends Komponent {

    // -1 – wsteczny, 0 – luz, 1..6 – biegi do przodu
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
        if (bieg > -1) {  // nie schodzimy poniżej wstecznego
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
