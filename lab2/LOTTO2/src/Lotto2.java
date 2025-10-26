import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> userNumbers = new ArrayList<>();

        System.out.println("=== LOTTO ===");
        System.out.println("Podaj 6 liczb (bez sprawdzania poprawności):");

        // 1️⃣ Wczytanie 6 liczb od użytkownika
        for (int i = 0; i < 6; i++) {
            System.out.print("Liczba " + (i + 1) + ": ");
            int num = scanner.nextInt();
            userNumbers.add(num);
        }

        // 2️⃣ Losowanie 6 unikalnych liczb
        Random random = new Random();
        ArrayList<Integer> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() < 6) {
            int number = random.nextInt(49) + 1; // zakres 1–49
            if (!lottoNumbers.contains(number)) {
                lottoNumbers.add(number);
            }
        }

        // 3️⃣ Porównanie wyników
        int hits = 0;
        for (int n : userNumbers) {
            if (lottoNumbers.contains(n)) {
                hits++;
            }
        }

        // 4️⃣ Wyświetlenie wyników
        System.out.println("\nTwoje typy: " + userNumbers);
        System.out.println("Wylosowane liczby: " + lottoNumbers);
        System.out.println("Trafienia: " + hits);
    }
}