package com.example.interfejs;

import com.example.samochod.Samochód;
import com.example.samochod.Silnik;
import com.example.samochod.SkrzyniaBiegów;
import com.example.samochod.Sprzęgło;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }

    @FXML
    private void onConfirmButton() {
        if (mainController == null) {
            pokazBlad("Błąd aplikacji: brak referencji do głównego kontrolera (mainController).");
            return;
        }

        String model = modelTextField.getText();
        String reg = registrationTextField.getText();

        if (model == null || model.isBlank() || reg == null || reg.isBlank()) {
            pokazBlad("Model i numer rejestracyjny nie mogą być puste.");
            return;
        }

        double weight;
        try {
            weight = Double.parseDouble(weightTextField.getText().replace(',', '.'));
        } catch (NumberFormatException e) {
            pokazBlad("Niepoprawna waga. Podaj liczbę, np. 1200 lub 1200,5.");
            return;
        }

        Silnik silnik = engineComboBox.getValue();
        SkrzyniaBiegów skrzynia = gearboxComboBox.getValue();
        Sprzęgło sprzęgło = clutchComboBox.getValue();

        if (silnik == null || skrzynia == null || sprzęgło == null) {
            pokazBlad("Wybierz silnik, skrzynię i sprzęgło.");
            return;
        }

        try {
            Samochód nowy = new Samochód(model, reg, weight, silnik, skrzynia, sprzęgło, 0, 0);
            mainController.addCarToList(nowy);

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            pokazBlad("Nie udało się utworzyć samochodu: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
