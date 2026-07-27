package com.funki.controller;

import java.io.File;

import com.funki.model.Flashcard;
import com.funki.service.CSVDeckLoader;
import com.funki.service.FlashcardService;
import com.funki.service.StudySession;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class MainController {
    @FXML
    private Label wordLabel;
    
    @FXML
    private Label currentCardNumber;

    @FXML
    private Label deckPathField;

    private final CSVDeckLoader csvDeckLoader = new CSVDeckLoader();
    private final FlashcardService service = new FlashcardService();
    private final StudySession session = new StudySession(service);

    @FXML
    private void initialize() { 
        loadDeck(); 

        if (!service.hasCards()) { return; }
        loadCard(); 
    }
    
    @FXML
    public void showAnswer() {
        if (!service.hasCards()) { return; }

        Flashcard card = session.getCurrentCard();
        session.flipCard();

        wordLabel.setText(
            session.isFlipped() ? 
                card.getBack() : 
                card.getFront() 
        );
    }

    @FXML
    private void nextCard() { 
        if (!service.hasCards()) { return; }

        session.nextCard();
        loadCard();
    }

    @FXML
    private void prevCard() { 
        if (!service.hasCards()) { return; }

        session.prevCard();
        loadCard();
    }
    
    @FXML
    private void shuffleCard () { 
        if (!service.hasCards()) { return; }

        session.shuffleCard();
        loadCard();
    }

    @FXML
    private void chooseDeck() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Flashcard Deck");
        chooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter(
                "CSV Files",
                "*.csv"
            )
        );

        File file = chooser.showOpenDialog(wordLabel.getScene().getWindow());

        if (file == null) { return; }

        loadDeck(file);
        loadCard();
    }

    // Methods
    private void loadCard() {
        Flashcard card = session.getCurrentCard();
        wordLabel.setText(card.getFront());
        currentCardNumber.setText("Card: " + (session.getCurrentIndex() + 1) + " / " + service.getCardsCount());

        session.resetCard();
    }

    private void loadDeck() {
        System.out.println("Tried to parse deck!");
        service.setCards(csvDeckLoader.parseDeck());
    }

    private void loadDeck(File file) {
        System.out.println("Tried to parse deck trough file!");
        service.setCards(csvDeckLoader.parseDeck(file));
        
        deckPathField.setText(file.getName());
    }

}