package symulator;

public class Pozycja  {
    private double x;
    private double y;

    public Pozycja(){
        this.x = 0;
        this.y = 0;
    }

    public void aktualizacjaPozycja(double deltaX, double deltaY) {
        this.x =  deltaX;
        this.y = deltaY;
    }

    public void getPozycja() {
        System.out.println("(" + x + "," + y + ")");
    }

}
