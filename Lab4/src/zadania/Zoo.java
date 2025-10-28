package zadania;
import animals.*;
import java.util.Random;

public class Zoo {
    private Animal[] animals = new Animal[100];
    private Random randomAnimals = new Random();

    public Zoo() {
        fillAnimals();
    }

    private void fillAnimals(){
        for (int i = 0; i < animals.length; i++) {
            int type = randomAnimals.nextInt(3);
            switch (type){
                case 0:
                    animals[i] = new Dog("Dog "+i);
                    break;

                case 1:
                    animals[i] = new Parrot("Parrot "+i);
                    break;

                case 2:
                    animals[i] = new Snake("Snake "+i);
                    break;
            }

        }
    }

    public int getTotalLegs() {
        int total = 0;
        for (Animal a : animals) {
            if (a != null) total += a.getLegs();
        }
        return total;
    }

    public void descriptionAnimals() {
        for (Animal a : animals) {
            if (a != null) System.out.println(a.getDescription());
        }
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.descriptionAnimals();
        System.out.println("\nTotal number of legs: " + zoo.getTotalLegs());
    }



}
