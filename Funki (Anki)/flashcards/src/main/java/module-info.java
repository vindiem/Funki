module com.funki {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.funki;
    exports com.funki.controller;

    opens com.funki.controller to javafx.fxml;
}