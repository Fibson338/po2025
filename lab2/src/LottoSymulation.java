import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LottoSymulation {
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

        // 2️⃣ Start pomiaru czasu
        long startTime = System.currentTimeMillis();

        // 3️⃣ Symulacja losowań aż do trafienia 6/6
        Random random = new Random();
        long attempts = 0;
        int hits;
        ArrayList<Integer> lottoNumbers;

        do {
            lottoNumbers = new ArrayList<>();
            while (lottoNumbers.size() < 6) {
                int number = random.nextInt(49) + 1;
                if (!lottoNumbers.contains(number)) {
                    lottoNumbers.add(number);
                }
            }

            // policz trafienia
            hits = 0;
            for (int n : userNumbers) {
                if (lottoNumbers.contains(n)) {
                    hits++;
                }
            }

            attempts++;

        } while (hits < 6); // powtarzaj aż trafisz 6/6

        // 4️⃣ Koniec pomiaru czasu
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // 5️⃣ Wynik
        System.out.println("\nTwoje typy: " + userNumbers);
        System.out.println("Wylosowane liczby: " + lottoNumbers);
        System.out.println("Trafiono 6/6 po " + attempts + " losowaniach!");
        System.out.println("Czas działania programu: " + duration + " ms");

        scanner.close();
    }
}