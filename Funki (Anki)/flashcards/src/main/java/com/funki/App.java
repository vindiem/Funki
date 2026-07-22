package com.funki;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/funki/view/main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 400);
        
        scene.getStylesheets().add(App.class.getResource("/com/funki/css/style.css").toExternalForm());

        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}