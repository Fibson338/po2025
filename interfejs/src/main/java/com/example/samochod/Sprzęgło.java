package com.example.samochod;

public class Sprzęgło extends Komponent {

    private boolean wciśnięte;

    public Sprzęgło(String nazwa, double cena, double waga) {
        super(nazwa, cena, waga);
        this.wciśnięte = false;
    }

    public void zaciągnij() {
        wciśnięte = true;

    }

    public void zwolnij() {
        wciśnięte = false;
    }

    public boolean isWciśnięte() {
        return wciśnięte ;
    }


}
