package com.example.interfejs;

import com.example.samochod.Samochód;
import com.example.samochod.Silnik;
import com.example.samochod.SkrzyniaBiegów;
import com.example.samochod.Sprzęgło;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML private TextField tfCarModel;
    @FXML private TextField tfCarPlate;
    @FXML private TextField tfCarWeight;
    @FXML private TextField tfCarSpeed;

    @FXML private Button włączButton;
    @FXML private Button wyłączButton;

    @FXML private TextField tfGearName;
    @FXML private TextField tfGearPrice;
    @FXML private TextField tfGearWeight;
    @FXML private TextField tfGearCurrent;

    @FXML private Button zwiększbiegButton;
    @FXML private Button zmniejszbiegButton;

    @FXML private TextField tfEngineName;
    @FXML private TextField tfEnginePrice;
    @FXML private TextField tfEngineWeight;
    @FXML private TextField tfEngineCurrent;

    @FXML private Button dodajgazuButton;
    @FXML private Button luzujButton;

    @FXML private TextField tfGearClutchName;
    @FXML private TextField tfClutchPrice;
    @FXML private TextField tfClutchWeight;
    @FXML private TextField tfClutchCurrent;

    @FXML private Button zaciągnijButton;
    @FXML private Button zwolnijButton;

    @FXML private ComboBox<Samochód> samochodComboBox;
    @FXML private Button addCarButton;
    @FXML private Button removeCarButton;


    private Samochód aktualnySamochód;


    @FXML
    private void initialize() {

        samochodComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldCar, newCar) -> {
                    if (newCar != null) {
                        aktualnySamochód = newCar;
                        refresh();
                    }
                }
        );
    }




    private void refresh() {
        if (aktualnySamochód == null) {
            onMenuNowy();
            return;
        }


        tfCarModel.setText(aktualnySamochód.getModel());
        tfCarPlate.setText(aktualnySamochód.getNumerRejestracyjny());
        tfCarWeight.setText(String.valueOf(aktualnySamochód.getWaga()));
        tfCarSpeed.setText(String.valueOf(aktualnySamochód.getPrędkość()));


        Silnik silnik = aktualnySamochód.getSilnik();
        tfEngineName.setText(silnik.getNazwa());
        tfEnginePrice.setText(String.valueOf(silnik.getCena()));
        tfEngineWeight.setText(String.valueOf(silnik.getWaga()));
        tfEngineCurrent.setText(String.valueOf(silnik.getObroty()));


        SkrzyniaBiegów skrzynia = aktualnySamochód.getSkrzynia();
        tfGearName.setText(skrzynia.getNazwa());
        tfGearPrice.setText(String.valueOf(skrzynia.getCena()));
        tfGearWeight.setText(String.valueOf(skrzynia.getWaga()));
        tfGearCurrent.setText(String.valueOf(skrzynia.getBieg()));


        Sprzęgło sprzęgło = aktualnySamochód.getSprzęgło();
        tfGearClutchName.setText(sprzęgło.getNazwa());
        tfClutchPrice.setText(String.valueOf(sprzęgło.getCena()));
        tfClutchWeight.setText(String.valueOf(sprzęgło.getWaga()));
        tfClutchCurrent.setText(sprzęgło.isWciśnięte() ? "Zaciągnięte" : "Zwolnione");
    }

    private double parseDoubleOrZero(String text) {
        try {
            return Double.parseDouble(text.replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void removeSelectedCar() {
        Samochód selected = samochodComboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            samochodComboBox.getItems().remove(selected);
            System.out.println("Usunięto: " + selected);

            if (!samochodComboBox.getItems().isEmpty()) {
                samochodComboBox.getSelectionModel().selectFirst();
                aktualnySamochód = samochodComboBox.getSelectionModel().getSelectedItem();
                refresh();
            } else {
                aktualnySamochód = null;
                onMenuNowy();
            }
        }
    }


    @FXML
    private void onwłączButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.włącz();
            System.out.println("Samochód uruchomiony!");
            refresh();
        }
    }

    @FXML
    private void onwyłączButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.wyłącz();
            System.out.println("Samochód wyłączony!");
            refresh();
        }
    }

    @FXML
    private void onzwiększbiegButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.zwiększBieg();
            System.out.println("Bieg zwiększony");
            refresh();
        }
    }

    @FXML
    private void onzmniejszbiegButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.zmniejszBieg();
            System.out.println("Bieg zmniejszony");
            refresh();
        }
    }

    @FXML
    private void ondodajgazuButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.dodajGazu();
            System.out.println("Dodano gazu");
            refresh();
        }
    }

    @FXML
    private void onluzujButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.luzujGazu();
            System.out.println("Zluzowano");
            refresh();
        }
    }

    @FXML
    private void onzaciągnijButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.zaciągnijSprzęgło();
            System.out.println("Sprzęgło zaciągnięte");
            refresh();
        }
    }

    @FXML
    private void onzwolnijButton() {
        if (aktualnySamochód != null) {
            aktualnySamochód.zwolnijSprzęgło();
            System.out.println("Sprzęgło zwolnione");
            refresh();
        }
    }


    @FXML
    private void onAddCarButton() {
        String model   = tfCarModel.getText();
        String plate   = tfCarPlate.getText();
        double waga    = parseDoubleOrZero(tfCarWeight.getText());

        String engName   = tfEngineName.getText();
        double engPrice  = parseDoubleOrZero(tfEnginePrice.getText());
        double engWeight = parseDoubleOrZero(tfEngineWeight.getText());

        String gearName   = tfGearName.getText();
        double gearPrice  = parseDoubleOrZero(tfGearPrice.getText());
        double gearWeight = parseDoubleOrZero(tfGearWeight.getText());

        String clutchName   = tfGearClutchName.getText();
        double clutchPrice  = parseDoubleOrZero(tfClutchPrice.getText());
        double clutchWeight = parseDoubleOrZero(tfClutchWeight.getText());

        Silnik silnik = new Silnik(engName, engPrice, engWeight);
        SkrzyniaBiegów skrzynia = new SkrzyniaBiegów(gearName, gearPrice, gearWeight);
        Sprzęgło sprzęgło = new Sprzęgło(clutchName, clutchPrice, clutchWeight);

        Samochód nowy = new Samochód(model, plate, waga, silnik, skrzynia, sprzęgło);

        samochodComboBox.getItems().add(nowy);
        samochodComboBox.getSelectionModel().select(nowy);

        aktualnySamochód = nowy;
        refresh();

        System.out.println("Dodano nowy samochód: " + nowy);
    }

    @FXML
    private void onRemoveCarButton() {
        removeSelectedCar();
    }


    @FXML
    private void onMenuNowy() {
        tfCarModel.clear();
        tfCarPlate.clear();
        tfCarWeight.clear();
        tfCarSpeed.clear();

        tfGearName.clear();
        tfGearPrice.clear();
        tfGearWeight.clear();
        tfGearCurrent.clear();

        tfEngineName.clear();
        tfEnginePrice.clear();
        tfEngineWeight.clear();
        tfEngineCurrent.clear();

        tfGearClutchName.clear();
        tfClutchPrice.clear();
        tfClutchWeight.clear();
        tfClutchCurrent.clear();

        System.out.println("Nowy – wyczyszczono formularz.");
    }

    @FXML
    private void onMenuZamknij() {
        System.out.println("Zamykanie aplikacji...");
        Platform.exit();
    }

    @FXML
    private void onMenuUsun() {
        removeSelectedCar();
    }
}
