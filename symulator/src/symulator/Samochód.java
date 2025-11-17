package symulator;

public class Samochód {

    private Silnik silnik;
    private SkrzyniaBiegów skrzynia;
    private Sprzęgło sprzęgło;
    private Pozycja pozycja;

    private boolean stanWłączenia;
    private String nrRejest;
    private String model;
    private double prędkośćMax;

    private double aktualnaPrędkość;
    private double waga;

    public Samochód() {
        this.silnik = new Silnik(2000, 0);
        this.skrzynia = new SkrzyniaBiegów(5, 0);
        this.sprzęgło = new Sprzęgło();
        this.pozycja = new Pozycja();

        this.stanWłączenia = false;
        this.nrRejest = "BRAK";
        this.model = "NIEZNANY";
        this.prędkośćMax = 200.0;
        this.aktualnaPrędkość = 0.0;
        this.waga = 1200.0; // przykładowa wartość
    }

    public void włącz() {
        this.silnik.uruchom();
        this.stanWłączenia = true;
    }

    public void wyłącz() {
        this.silnik.zatrzymaj();
        this.skrzynia.BiegZero();
        this.stanWłączenia = false;
        this.aktualnaPrędkość = 0.0;
    }


    public void jedźDo(double x, double y) {
        this.pozycja.aktualizacjaPozycja(x, y);
    }


    public void jedźDo(Pozycja cel) {
        this.pozycja = cel;
    }

    // --------- METODY Z UML ---------

    public void getWaga() {
        System.out.println("Waga samochodu: " + waga + " kg");
    }

    public void getAktPredkosc() {
        System.out.println("Aktualna prędkość: " + aktualnaPrędkość + " km/h");
    }

    public void getAktPozycja() {
        System.out.print("Aktualna pozycja: ");
        this.pozycja.getPozycja();
    }
}

