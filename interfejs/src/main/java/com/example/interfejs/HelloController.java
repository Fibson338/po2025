package com.example.interfejs;

import com.example.samochod.Samochód;
import com.example.samochod.Silnik;
import com.example.samochod.SkrzyniaBiegów;
import com.example.samochod.Sprzęgło;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML private Label welcomeText;

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

    @FXML private ImageView carImageView;

    private Samochód aktualnySamochód;

    @FXML
    private void initialize() {

        samochodComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldCar, newCar) -> {
                    aktualnySamochód = newCar;
                    refresh();
                }
        );

        Image carImage = new Image(
                getClass().getResource("/images/images.png").toExternalForm()
        );

        carImageView.setImage(carImage);
        carImageView.setFitWidth(250);
        carImageView.setPreserveRatio(true);
        carImageView.setTranslateX(0);
        carImageView.setTranslateY(0);

        refresh();
    }

    private void refresh() {
        if (aktualnySamochód == null) {
            clearFields();
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

    private void clearFields() {
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
    }

    private void removeSelectedCar() {
        Samochód selected = samochodComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        samochodComboBox.getItems().remove(selected);

        if (!samochodComboBox.getItems().isEmpty()) {
            samochodComboBox.getSelectionModel().selectFirst();
        } else {
            aktualnySamochód = null;
            refresh();
        }
    }

    // ====== AKCJE (po każdej refresh - u Ciebie już było OK) ======

    @FXML private void onwłączButton() { if (aktualnySamochód != null) { aktualnySamochód.włącz(); refresh(); } }
    @FXML private void onwyłączButton() { if (aktualnySamochód != null) { aktualnySamochód.wyłącz(); refresh(); } }

    @FXML private void onzwiększbiegButton() { if (aktualnySamochód != null) { aktualnySamochód.zwiększBieg(); refresh(); } }
    @FXML private void onzmniejszbiegButton() { if (aktualnySamochód != null) { aktualnySamochód.zmniejszBieg(); refresh(); } }

    @FXML private void ondodajgazuButton() { if (aktualnySamochód != null) { aktualnySamochód.dodajGazu(); refresh(); } }
    @FXML private void onluzujButton() { if (aktualnySamochód != null) { aktualnySamochód.luzujGazu(); refresh(); } }

    @FXML private void onzaciągnijButton() { if (aktualnySamochód != null) { aktualnySamochód.zaciągnijSprzęgło(); refresh(); } }
    @FXML private void onzwolnijButton() { if (aktualnySamochód != null) { aktualnySamochód.zwolnijSprzęgło(); refresh(); } }

    // ====== DODAWANIE AUTA: NOWE OKNO (Stage) ======

    @FXML
    private void onAddCarButton() throws IOException {
        openAddCarWindow();
    }

    private void openAddCarWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");

        DodajSamochodController controller = loader.getController();
        controller.setMainController(this);

        stage.show();
    }

    // Ta metoda będzie wołana z DodajSamochodController
    public void addCarToList(Samochód nowy) {
        samochodComboBox.getItems().add(nowy);
        samochodComboBox.getSelectionModel().select(nowy);
        aktualnySamochód = nowy;
        refresh();
    }

    @FXML private void onRemoveCarButton() { removeSelectedCar(); }

    // ====== MENU ======
    @FXML private void onMenuNowy() { clearFields(); }
    @FXML private void onMenuZamknij() { Platform.exit(); }
    @FXML private void onMenuUsun() { removeSelectedCar(); }
}
