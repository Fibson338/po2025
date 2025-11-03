package symulator;

public class SkrzyniaBiegów {
    private int aktualnyBieg;
    private int ilośćBiegów;

    public  SkrzyniaBiegów(int ilośćBiegów, int aktualnyBieg){
        this.ilośćBiegów = ilośćBiegów;
        this.aktualnyBieg = aktualnyBieg;
    }

    public void zwiększBieg(){
        if(aktualnyBieg <= ilośćBiegów){
            aktualnyBieg++;
        }
        else {
            System.out.println("Nie da się zwiększyć biegu.");
        }
    }

    public void zmniejszBieg(){
        if(aktualnyBieg != 0){
            aktualnyBieg--;
        }
        else {
            System.out.println("Nie da się zmniejszyć biegu.");
        }
    }

    public void getAktualnyBieg(){
        System.out.println("Aktualny bieg: " + aktualnyBieg);
    }

    public void BiegZero(){
        aktualnyBieg = 0;
    }
}
