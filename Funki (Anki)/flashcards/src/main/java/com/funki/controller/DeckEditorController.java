package com.funki.controller;

import com.funki.model.Flashcard;
import com.funki.service.FlashcardService;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeckEditorController {
    private FlashcardService service;

    @FXML
    private TableView<Flashcard> cardsTable;

    @FXML
    private TableColumn<Flashcard, String> frontColumn;

    @FXML
    private TableColumn<Flashcard, String> backColumn;

    @FXML
    private void initialize() {
        frontColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getFront())
        );

        backColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getBack())
        );
    }

    public void setService(FlashcardService service) {
        this.service = service;
        refresh();
    }

    public void refresh() {
        if (service == null) {
            cardsTable.getItems().clear();
            return;
        }

        cardsTable.getItems().setAll(service.getCards());
    }

    @FXML
    private void addCard() {
        // TODO
    }

    @FXML
    private void removeSelectedCard() {
        // TODO
    }

    @FXML
    private void saveDeck() {
        // TODO
    }
}