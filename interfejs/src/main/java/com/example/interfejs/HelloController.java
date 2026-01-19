package com.example.interfejs;

import com.example.samochod.Listener;
import com.example.samochod.Pozycja;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController implements Listener {

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

    @FXML private Pane mapa;
    @FXML private ImageView carImageView;

    private Samochód aktualnySamochód;

    @FXML
    private void initialize() {

        samochodComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldCar, newCar) -> {

                    if (oldCar != null) {
                        oldCar.removeListener(this);
                    }

                    aktualnySamochód = newCar;

                    if (newCar != null) {
                        newCar.addListener(this);
                        System.out.println("Wybrano samochód: " + newCar);
                    } else {
                        System.out.println("Nie wybrano samochodu.");
                    }

                    refresh();
                }
        );

        Image carImage = new Image(
                getClass().getResource("/images/images.png").toExternalForm()
        );

        carImageView.setImage(carImage);
        carImageView.setFitWidth(60);
        carImageView.setPreserveRatio(true);
        carImageView.setTranslateX(0);
        carImageView.setTranslateY(0);

        mapa.setOnMouseClicked(event -> {
            System.out.println("Klik mapy: X=" + event.getX() + " Y=" + event.getY());

            if (aktualnySamochód == null) {
                System.out.println("Błąd: Najpierw wybierz samochód.");
                return;
            }

            try {
                Pozycja nowaPozycja = new Pozycja(event.getX(), event.getY());
                aktualnySamochód.jedzDo(nowaPozycja);
                System.out.println("Ustawiono cel jazdy: " + nowaPozycja);
            } catch (Exception e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        });

        refresh();
    }

    @Override
    public void update() {
        Platform.runLater(this::refresh);
    }

    private void refresh() {
        Runnable ui = () -> {
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
            tfClutchCurrent.setText(
                    sprzęgło.isWciśnięte() ? "Wciśnięte" : "Zwolnione"
            );

            carImageView.setTranslateX(aktualnySamochód.getPozycja().getX());
            carImageView.setTranslateY(aktualnySamochód.getPozycja().getY());
        };

        if (Platform.isFxApplicationThread()) {
            ui.run();
        } else {
            Platform.runLater(ui);
        }
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
        if (selected == null) {
            System.out.println("Usuń: brak wybranego samochodu.");
            return;
        }

        selected.removeListener(this);
        System.out.println("Usunięto samochód: " + selected);

        samochodComboBox.getItems().remove(selected);

        if (!samochodComboBox.getItems().isEmpty()) {
            samochodComboBox.getSelectionModel().selectFirst();
        } else {
            aktualnySamochód = null;
            refresh();
        }
    }

    @FXML
    private void onwłączButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        try {
            aktualnySamochód.włącz();
            System.out.println("Silnik włączony.");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onwyłączButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        try {
            aktualnySamochód.wyłącz();
            System.out.println("Silnik wyłączony, bieg=0, prędkość=0.");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onzwiększbiegButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        int before = aktualnySamochód.getSkrzynia().getBieg();
        try {
            aktualnySamochód.zwiększBieg();
            int after = aktualnySamochód.getSkrzynia().getBieg();
            System.out.println("Bieg: " + before + " -> " + after);
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onzmniejszbiegButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        int before = aktualnySamochód.getSkrzynia().getBieg();
        try {
            aktualnySamochód.zmniejszBieg();
            int after = aktualnySamochód.getSkrzynia().getBieg();
            System.out.println("Bieg: " + before + " -> " + after);
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void ondodajgazuButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        double beforeV = aktualnySamochód.getPrędkość();
        int beforeRpm = aktualnySamochód.getSilnik().getObroty();

        try {
            aktualnySamochód.dodajGazu();
            double afterV = aktualnySamochód.getPrędkość();
            int afterRpm = aktualnySamochód.getSilnik().getObroty();

            if (beforeV != afterV) {
                System.out.println("Prędkość: " + beforeV + " -> " + afterV);
            } else {
                System.out.println("Prędkość bez zmian: " + afterV);
            }

            if (beforeRpm != afterRpm) {
                System.out.println("Obroty: " + beforeRpm + " -> " + afterRpm);
            } else {
                System.out.println("Obroty bez zmian: " + afterRpm);
            }

        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onluzujButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        double beforeV = aktualnySamochód.getPrędkość();
        int beforeRpm = aktualnySamochód.getSilnik().getObroty();

        try {
            aktualnySamochód.luzujGazu();
            double afterV = aktualnySamochód.getPrędkość();
            int afterRpm = aktualnySamochód.getSilnik().getObroty();

            if (beforeV != afterV) {
                System.out.println("Prędkość: " + beforeV + " -> " + afterV);
            } else {
                System.out.println("Prędkość bez zmian: " + afterV);
            }

            if (beforeRpm != afterRpm) {
                System.out.println("Obroty: " + beforeRpm + " -> " + afterRpm);
            } else {
                System.out.println("Obroty bez zmian: " + afterRpm);
            }

        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onzaciągnijButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        try {
            aktualnySamochód.zaciągnijSprzęgło();
            System.out.println("Sprzęgło: Wciśnięte");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onzwolnijButton() {
        if (aktualnySamochód == null) {
            System.out.println("Błąd: Nie wybrano samochodu.");
            return;
        }
        try {
            aktualnySamochód.zwolnijSprzęgło();
            System.out.println("Sprzęgło: Zwolnione");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    @FXML
    private void onAddCarButton() {
        try {
            openAddCarWindow();
        } catch (IOException e) {
            System.out.println("Błąd: Nie udało się otworzyć okna: " + e.getMessage());
        }
    }

    private void openAddCarWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/interfejs/DodajSamochod.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Dodaj nowy samochód");

        DodajSamochodController controller = loader.getController();
        controller.setMainController(this);

        stage.show();
    }

    public void addCarToList(Samochód nowy) {
        samochodComboBox.getItems().add(nowy);
        samochodComboBox.getSelectionModel().select(nowy);
        System.out.println("Dodano samochód: " + nowy);
    }

    @FXML
    private void onRemoveCarButton() {
        removeSelectedCar();
    }

    @FXML
    private void onMenuNowy() {
        System.out.println("Menu: Nowy");
        clearFields();
    }

    @FXML
    private void onMenuZamknij() {
        System.out.println("Menu: Zamknij");
        Platform.exit();
    }

    @FXML
    private void onMenuUsun() {
        System.out.println("Menu: Usuń");
        removeSelectedCar();
    }
}
