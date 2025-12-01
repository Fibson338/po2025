package com.example.samochod;

public abstract class Komponent {

    private String nazwa;
    private double cena;
    private double waga;

    public Komponent(String nazwa, double cena, double waga) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.waga = waga;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getWaga() {
        return waga;
    }

    public void setWaga(double waga) {
        this.waga = waga;
    }
}
