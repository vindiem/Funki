package com.funki.controller;

import com.funki.model.Flashcard;
import com.funki.service.FlashcardService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    private final FlashcardService service = new FlashcardService();

    @FXML
    private Label wordLabel;
    
    @FXML
    private Label currentCardNumber;

    private enum MoveType {
        NEXT, 
        PREV,
        SHUFFLE
    }

    private boolean flipped = false;

    @FXML
    private void initialize() {
        loadCard();
    }

    private void loadCard() {
        Flashcard card = service.getCurrentCard();
        wordLabel.setText(card.getFront());
        currentCardNumber.setText("Card: " + (service.getCurrentCardIndex() + 1) + " / " + service.getCardsCount());

        flipped = false;
        
    }

    @FXML
    private void showAnswer() {
        Flashcard card = service.getCurrentCard();

        flipped = !flipped;

        wordLabel.setText(
            flipped ? card.getBack() : card.getFront()
        );
    }

    private void moveCard(MoveType s) {
        switch (s) {
            case MoveType.NEXT -> service.nextCard();
            case MoveType.PREV -> service.prevCard();
            case MoveType.SHUFFLE -> service.shuffleCard();
            default -> throw new AssertionError();
        }
        loadCard();
    }

    @FXML
    private void nextCard() { moveCard(MoveType.NEXT); }

    @FXML
    private void prevCard() { moveCard(MoveType.PREV); }
    
    @FXML
    private void shuffleCard () { moveCard(MoveType.SHUFFLE); }

}