module com.funki {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.funki;
    exports com.funki.controller;

    opens com.funki.controller to javafx.fxml;
    opens com.funki.model to javafx.base;
}