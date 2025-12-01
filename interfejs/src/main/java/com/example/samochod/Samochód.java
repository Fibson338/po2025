package com.example.samochod;

public class Samochód {

    private String model;
    private String numerRejestracyjny;
    private double waga;
    private double prędkość;

    private final Silnik silnik;
    private final SkrzyniaBiegów skrzynia;
    private final Sprzęgło sprzęgło;

    public Samochód(String model,
                    String numerRejestracyjny,
                    double waga,
                    Silnik silnik,
                    SkrzyniaBiegów skrzynia,
                    Sprzęgło sprzęgło) {
        this.model = model;
        this.numerRejestracyjny = numerRejestracyjny;
        this.waga = waga;
        this.prędkość = 0.0;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.sprzęgło = sprzęgło;
    }

    public void włącz() {
        silnik.włącz();
    }

    public void wyłącz() {
        silnik.wyłącz();
        prędkość = 0;

    }

    public boolean isWłączony() {
        return silnik.isWłączony();
    }

    public void zwiększBieg() {
        if(isWłączony()) {
            skrzynia.zwiększBieg();
        }
    }

    public void zmniejszBieg() {
        if(isWłączony()) {
            skrzynia.zmniejszBieg();
        }
    }

    public void dodajGazu() {
        silnik.zwiększObroty();
        if(prędkość <=240 && isWłączony()) {
            prędkość += 10;
        }
    }

    public void luzujGazu() {
        silnik.zmniejszObroty();
        if (prędkość >= 10 && isWłączony() ) {
            prędkość -= 10;
        }
    }

    public void zaciągnijSprzęgło() {
        sprzęgło.zaciągnij();
        prędkość = 0 ;
        silnik.zeroObroty();
    }

    public void zwolnijSprzęgło() {
        sprzęgło.zwolnij();
    }


    public String getModel() {
        return model;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public double getWaga() {
        return waga;
    }

    public double getPrędkość() {
        return prędkość;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public SkrzyniaBiegów getSkrzynia() {
        return skrzynia;
    }

    public Sprzęgło getSprzęgło() {
        return sprzęgło;
    }

    @Override
    public String toString() {
        return model + " (" + numerRejestracyjny + ")";
    }
}
