package com.funki.controller;

import java.io.File;
import java.io.IOException;

import com.funki.service.CSVDeckLoader;
import com.funki.service.FlashcardService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class MainController {
    @FXML
    private StackPane contentPane;

    private final FlashcardService service = new FlashcardService();
    private final CSVDeckLoader deckLoader = new CSVDeckLoader();

    private DeckEditorController deckEditorController;
    private StudyController studyController;

    private String currentDeckName;

    @FXML
    private void initialize() throws IOException {
        showStudyView();
    }

    @FXML
    private void showStudyView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/funki/view/StudyView.fxml"));

        Parent view = loader.load();

        studyController = loader.getController();
        studyController.setService(service);

        if (currentDeckName != null) {
            studyController.onDeckLoaded(currentDeckName);
        }

        contentPane.getChildren().setAll(view);
    }

    @FXML
    private void showDeckEditor() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/funki/view/DeckEditor.fxml"));

        Parent view = loader.load();

        deckEditorController = loader.getController();
        deckEditorController.setService(service);

        if (service.hasCards()) {
            deckEditorController.refresh();
        }

        contentPane.getChildren().setAll(view);
    }

    @FXML
    private void chooseDeck() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Flashcard Deck");

        chooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV files", "*.csv")
        );
        File file = chooser.showOpenDialog(contentPane.getScene().getWindow());

        if (file != null) {
            openDeck(file);
        }
    }

    private void openDeck(File file) {
        service.setCards(deckLoader.parseDeck(file));
        currentDeckName = file.getName();

        if (studyController != null) {
            studyController.onDeckLoaded(currentDeckName);
        }

        if (deckEditorController != null) {
            deckEditorController.refresh();
        }
    }

    @FXML
    private void exitApplication() {
        javafx.application.Platform.exit();
    }

    @FXML
    private void about() {
        // TODO
    }

    @FXML
    private void setGreyTheme() {
        // TODO
    }
}