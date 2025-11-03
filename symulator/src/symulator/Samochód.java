package symulator;

public class Samochód {

    private Silnik silnik ;
    private SkrzyniaBiegów skrzynia;
    private Pozycja pozycja;

    public Samochód (){
        this.silnik = new Silnik(2000, 0);
        this.skrzynia = new SkrzyniaBiegów(5,0);
        this.pozycja = new Pozycja();
    }

    public void włącz (){
        this.silnik.uruchom();
    }

    public void wyłącz(){
        this.silnik.zatrzymaj();
        this.skrzynia.BiegZero();
    }

    public void jedźDo(double x, double y){
        this.pozycja.aktualizacjaPozycja(x,y);
    }


}
