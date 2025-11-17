package symulator;

public class Komponent {

    private double cena;
    private String producent;
    private String model;

    private String nazwa;
    private double waga;

    public Komponent(String nazwa, double waga, double cena) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
        this.producent = nazwa;
        this.model = "";
    }

    public void getProduceny() {
        System.out.println("Producent: " + producent);
    }

    public void getModel() {
        System.out.println("Model: " + model);
    }

    public void getCena() {
        System.out.println("Cena: " + cena);
    }


    public void getNazwa() {
        System.out.println("Nazwa: " + nazwa);
    }

    public void getWaga() {
        System.out.println("Waga: " + waga + " kg");
    }
}
