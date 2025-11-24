package com.example.samochod;

public class SkrzyniaBiegów {

    private int aktualnyBieg;
    private int ilośćBiegów;
    private double aktualnePrzełożenie; // z UML

    public SkrzyniaBiegów(int ilośćBiegów, int aktualnyBieg) {
        this.ilośćBiegów = ilośćBiegów;
        this.aktualnyBieg = aktualnyBieg;
        this.aktualnePrzełożenie = 0.0;
    }

    public void zwiększBieg() {
        if (aktualnyBieg <= ilośćBiegów) {
            aktualnyBieg++;
            aktualnePrzełożenie = aktualnyBieg; // prosta zależność
        } else {
            System.out.println("Nie da się zwiększyć biegu.");
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg != 0) {
            aktualnyBieg--;
            aktualnePrzełożenie = aktualnyBieg;
        } else {
            System.out.println("Nie da się zmniejszyć biegu.");
        }
    }

    public void getAktualnyBieg() {
        System.out.println("Aktualny bieg: " + aktualnyBieg);
    }

    public void BiegZero() {
        aktualnyBieg = 0;
        aktualnePrzełożenie = 0.0;
    }


    public void getAktBieg() {
        System.out.println("Aktualny bieg: " + aktualnyBieg);
    }

    public void getAktPrzełożenie() {
        System.out.println("Aktualne przełożenie: " + aktualnePrzełożenie);
    }
}
