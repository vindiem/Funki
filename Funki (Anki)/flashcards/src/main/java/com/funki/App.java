package com.funki;

import com.funki.controller.MainController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainController controller = new MainController();

        Scene scene = new Scene(controller.getRoot(), 500, 400);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}