package com.funki.controller;

import com.funki.model.Flashcard;
import com.funki.service.FlashcardService;
import com.funki.service.StudySession;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudyController {

    private FlashcardService service;
    private StudySession session;

    @FXML
    private Label wordLabel;

    @FXML
    private Label currentCardNumber;

    @FXML
    private Label deckPathField;

    @FXML
    private void initialize() {
        // Service is injected later by MainController.
    }

    @FXML
    private void showAnswer() {
        if (!hasDeck()) {
            return;
        }

        Flashcard card = session.getCurrentCard();
        session.flipCard();

        wordLabel.setText(
                session.isFlipped()
                        ? card.getBack()
                        : card.getFront()
        );
    }

    @FXML
    private void nextCard() {
        if (!hasDeck()) {
            return;
        }

        session.nextCard();
        loadCard();
    }

    @FXML
    private void prevCard() {
        if (!hasDeck()) {
            return;
        }

        session.prevCard();
        loadCard();
    }

    @FXML
    private void shuffleCard() {
        if (!hasDeck()) {
            return;
        }

        session.shuffleCard();
        loadCard();
    }

    @FXML
    private void restartSession() {
        if (!hasDeck()) {
            return;
        }

        session.resetCardsAfterDeckLoaded();
        loadCard();
    }

    public void setService(FlashcardService service) {
        this.service = service;
        this.session = new StudySession(service);

        if (service.hasCards()) {
            loadCard();
        }
    }

    public void onDeckLoaded(String deckName) {
        deckPathField.setText(deckName);
        session.resetCardsAfterDeckLoaded();

        if (service.hasCards()) {
            loadCard();
        } else {
            wordLabel.setText("Open a deck");
            currentCardNumber.setText("0 / 0");
        }
    }

    private void loadCard() {
        Flashcard card = session.getCurrentCard();
        wordLabel.setText(card.getFront());

        currentCardNumber.setText(
                "Card: "
                        + (session.getCurrentIndex() + 1)
                        + " / "
                        + service.getCardsCount()
        );

        session.resetCard();
    }

    private boolean hasDeck() {
        return service != null && service.hasCards();
    }
}