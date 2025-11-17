package symulator;

public class Main {
    public static void main(String[] args) {

        // SAMOCHÓD
        System.out.println("SAMOCHÓD:");
        Samochód auto = new Samochód();

        auto.włącz();             // uruchomienie silnika
        auto.getWaga();           // waga samochodu
        auto.getAktPredkosc();
        auto.getAktPozycja();

        // jazda do zadanych współrzędnych (wersja z double)
        auto.jedźDo(10, 5);
        auto.getAktPozycja();

        // jazda do pozycji zadanej obiektem Pozycja
        Pozycja cel = new Pozycja();
        cel.aktualizacjaPozycja(100, 50);
        auto.jedźDo(cel);
        auto.getAktPozycja();

        auto.wyłącz();

        // SILNIK
        System.out.println("\nSILNIK:");
        Silnik silnik = new Silnik(5000, 0);
        silnik.uruchom();
        silnik.zwiększObroty();
        silnik.zwiększObroty();
        silnik.zmniejszObroty();
        silnik.zatrzymaj();

        // SKRZYNIA BIEGÓW
        System.out.println("\nSKRZYNIA BIEGÓW:");
        SkrzyniaBiegów skrzynia = new SkrzyniaBiegów(5, 0);
        skrzynia.getAktualnyBieg();
        skrzynia.getAktBieg();
        skrzynia.getAktPrzełożenie();

        skrzynia.zwiększBieg();
        skrzynia.getAktualnyBieg();
        skrzynia.getAktBieg();
        skrzynia.getAktPrzełożenie();

        skrzynia.zwiększBieg();
        skrzynia.zmniejszBieg();
        skrzynia.BiegZero();
        skrzynia.getAktualnyBieg();
        skrzynia.getAktPrzełożenie();

        // SPRZĘGŁO
        System.out.println("\nSPRZĘGŁO:");
        Sprzęgło sprzęgło = new Sprzęgło();
        sprzęgło.getStanSprzęgła();
        sprzęgło.wciśnij();
        sprzęgło.getStanSprzęgła();
        sprzęgło.zwolnij();
        sprzęgło.getStanSprzęgła();

        // POZYCJA
        System.out.println("\nPOZYCJA:");
        Pozycja p = new Pozycja();
        p.getPozycja();
        p.aktualizacjaPozycja(3.5, 7.2);
        p.getPozycja();


        System.out.println("\n KOMPONENT :");
        Komponent k2 = new Komponent("Felga aluminiowa", 8.5, 799.99);
        k2.getNazwa();
        k2.getWaga();
        k2.getCena();
    }
}
