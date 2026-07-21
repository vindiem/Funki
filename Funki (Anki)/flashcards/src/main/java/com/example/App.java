package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private final String[][] cards = {
            {"apple", "яблоко"},
            {"dog", "собака"},
            {"house", "дом"},
            {"book", "книга"},
            {"computer", "компьютер"}
    };

    private int currentCard = 0;

    private final Label frontLabel = new Label();
    private final Label backLabel = new Label();

    @Override
    public void start(Stage stage) {

        frontLabel.setFont(new Font(28));
        backLabel.setFont(new Font(22));

        Button showAnswer = new Button("Show Answer");
        Button nextCard = new Button("Next Card");

        loadCard();

        showAnswer.setOnAction(e ->
                backLabel.setText(cards[currentCard][1])
        );

        nextCard.setOnAction(e -> {
            currentCard++;

            if (currentCard >= cards.length) {
                currentCard = 0;
            }

            loadCard();
        });

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                frontLabel,
                backLabel,
                showAnswer,
                nextCard
        );

        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();
    }

    private void loadCard() {
        frontLabel.setText(cards[currentCard][0]);
        backLabel.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}