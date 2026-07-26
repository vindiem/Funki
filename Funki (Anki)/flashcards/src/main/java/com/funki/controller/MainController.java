package com.funki.controller;

import com.funki.model.Flashcard;
import com.funki.service.FlashcardService;
import com.funki.service.StudySession;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label wordLabel;
    
    @FXML
    private Label currentCardNumber;

    private enum MoveType {
        NEXT, 
        PREV,
        SHUFFLE
    }

    private final FlashcardService service = new FlashcardService();
    private final StudySession session = new StudySession(service);

    @FXML
    private void initialize() { 
        // session.parseDeck(); 
        // loadCard(); 
    }
    
    @FXML
    public void showAnswer() {
        Flashcard card = session.getCurrentCard();
        session.flipCard();

        wordLabel.setText(
            session.isFlipped() ? card.getBack() : card.getFront() 
        );
    }

    @FXML
    private void nextCard() { moveCard(MoveType.NEXT); }

    @FXML
    private void prevCard() { moveCard(MoveType.PREV); }
    
    @FXML
    private void shuffleCard () { moveCard(MoveType.SHUFFLE); }

    // Methods
    private void moveCard(MoveType s) {
        switch (s) {
            case MoveType.NEXT -> session.nextCard();
            case MoveType.PREV -> session.prevCard();
            case MoveType.SHUFFLE -> session.shuffleCard();
            default -> throw new AssertionError();
        }
        loadCard();
    }

    private void loadCard() {
        try {
            Flashcard card = session.getCurrentCard();
            wordLabel.setText(card.getFront());
            currentCardNumber.setText("Card: " + (session.getCurrentIndex() + 1) + " / " + service.getCardsCount());

            session.resetCard();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    
}