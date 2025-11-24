package com.example.interfejs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button włączButton;

    @FXML
    private void onwłączButton() {

        System.out.println("Samochód uruchomiony!");
    }

    @FXML
    private Button wyłączButton;

    @FXML
    private void onwyłączButton() {
        System.out.println("Samochód wyłączony!");
    }

    @FXML
    private Button zwiększbiegButton;

    @FXML
    private void onzwiększbiegButton() {
        System.out.println("Bieg wziększony!");
    }

    @FXML
    private Button zmniejszbiegButton;

    @FXML
    private void onzmniejszbiegButton() {
        System.out.println("Bieg zmniejszony!");
    }

    @FXML
    private Button dodajgazuButton;

    @FXML
    private void ondodajgazuButton() {
        System.out.println("Dodano Gazu!");
    }

    @FXML
    private Button luzujButton;

    @FXML
    private void onluzujButton() {
        System.out.println("Zluzowano!");
    }

    @FXML
    private Button zaciągnijButton;

    @FXML
    private void onzaciągnijButton() {
        System.out.println("Sprzęgło zaciągnięte!");
    }

    @FXML
    private Button zwolnijButton;

    @FXML
    private void onzwolnijButton() {
        System.out.println("Sprzęgło zwolnione!");
    }

    @FXML
    private void onMenuNowy() {

    }

    @FXML
    private void onMenuZamknij() {

    }

    @FXML
    private void onMenuUsun() {

    }

}
