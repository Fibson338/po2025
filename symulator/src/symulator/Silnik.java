package symulator;

public class Silnik {

    private int maxObroty;
    private int obroty;

    public Silnik (int maxObroty, int obroty) {
        this.maxObroty = maxObroty;
        this.obroty = obroty;
    }

    public void uruchom(){
        obroty = 800;
        System.out.println("Silnik uruchomiony, obroty = " + obroty);
    }


    public void zatrzymaj(){
        obroty = 0;
        System.out.println("Silnik zatrzymany, obroty = " + obroty);
    }

    public void zwiększObroty(){
        if(obroty < maxObroty){
            obroty++;
        }
        else{
            System.out.println("Nie da się zwiększyć obrotów.");
        }
    }

    public void zmniejszObroty(){
        if(obroty <= 0){
            obroty--;
        }
        else{
            System.out.println("Nie da się zmniejszyć obrotów.");
        }
    }

}
