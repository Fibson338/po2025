package symulator;

public class Komponent {

    private double cena;
    private String producent;
    private String model;

    public Komponent(double cena, String producent, String model){
        this.cena = cena;
        this.producent = producent;
        this.model = model;
    }

    public void getProduceny() {
        System.out.println("Producent;" + producent);
    }

    public void getModel() {
        System.out.println("Model;" + model);
    }

    public void getCena() {
        System.out.println("Cena;" + cena);
    }

}
