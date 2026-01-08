package com.example.interfejs;

import com.example.samochod.Samochód;
import com.example.samochod.Silnik;
import com.example.samochod.SkrzyniaBiegów;
import com.example.samochod.Sprzęgło;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajSamochodController {

    @FXML private TextField modelTextField;
    @FXML private TextField registrationTextField;
    @FXML private TextField weightTextField;

    @FXML private ComboBox<Silnik> engineComboBox;
    @FXML private ComboBox<SkrzyniaBiegów> gearboxComboBox;
    @FXML private ComboBox<Sprzęgło> clutchComboBox;

    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    private HelloController mainController;

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        engineComboBox.getItems().addAll(
                new Silnik("1.6 Benzyna", 8000, 120),
                new Silnik("2.0 Diesel", 11000, 140)
        );

        gearboxComboBox.getItems().addAll(
                new SkrzyniaBiegów("Manual 5", 3500, 60),
                new SkrzyniaBiegów("Automat 6", 7000, 85)
        );

        clutchComboBox.getItems().addAll(
                new Sprzęgło("Standard", 900, 12),
                new Sprzęgło("Sport", 1500, 14)
        );
    }

    @FXML
    private void onConfirmButton() {
        String model = modelTextField.getText();
        String reg = registrationTextField.getText();

        if (model == null || model.isBlank() || reg == null || reg.isBlank()) {
            System.out.println("Model i numer rejestracyjny nie mogą być puste.");
            return;
        }

        double weight;
        try {
            weight = Double.parseDouble(weightTextField.getText().replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Niepoprawna waga.");
            return;
        }

        Silnik silnik = engineComboBox.getValue();
        SkrzyniaBiegów skrzynia = gearboxComboBox.getValue();
        Sprzęgło sprzęgło = clutchComboBox.getValue();

        if (silnik == null || skrzynia == null || sprzęgło == null) {
            System.out.println("Wybierz silnik, skrzynię i sprzęgło.");
            return;
        }

        Samochód nowy = new Samochód(model, reg, weight, silnik, skrzynia, sprzęgło, 0, 0);

        mainController.addCarToList(nowy);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
