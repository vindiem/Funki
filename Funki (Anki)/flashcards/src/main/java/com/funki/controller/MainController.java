package com.funki.controller;

import com.funki.model.Flashcard;
import com.funki.service.FlashcardService;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainController {

    private final FlashcardService service = new FlashcardService();

    private final Label frontLabel = new Label();
    private final Label backLabel = new Label();

    private final Button showAnswerButton = new Button("Show Answer");
    private final Button nextCardButton = new Button("Next Card");
    private final Button prevCardButton = new Button("Prevoius Card");
    private final Button shuffleCardButton = new Button("Shuffle Card");

    private final VBox root = new VBox(20);

    public MainController() {

        frontLabel.setFont(new Font(28));
        backLabel.setFont(new Font(22));

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                frontLabel,
                backLabel,
                showAnswerButton,
                nextCardButton,
                prevCardButton,
                shuffleCardButton
        );

        loadCard();

        showAnswerButton.setOnAction(e -> showAnswer());
        nextCardButton.setOnAction(e -> nextCard());
        prevCardButton.setOnAction(e -> prevCard());
        shuffleCardButton.setOnAction(e -> shuffleCard());
    }

    private void loadCard() {
        Flashcard card = service.getCurrentCard();

        frontLabel.setText(card.getFront());
        backLabel.setText("");
    }

    private void showAnswer() {
        Flashcard card = service.getCurrentCard();

        backLabel.setText(card.getBack());
    }

    private void nextCard() {
        service.nextCard();
        loadCard();
    }

    private void prevCard() {
        service.prevCard();
        loadCard();
    }

    private void shuffleCard() {
        service.shuffleCard();
        loadCard();
    }

    public Parent getRoot() {
        return root;
    }
}