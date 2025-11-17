module com.example.interfejs {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.interfejs to javafx.fxml;
    exports com.example.interfejs;
}