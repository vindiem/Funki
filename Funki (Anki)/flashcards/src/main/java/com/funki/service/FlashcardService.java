package com.funki.service;
import java.util.ArrayList;
import java.util.List;

import com.funki.model.Flashcard;

public class FlashcardService {
    private List<Flashcard> cards = new ArrayList<>();

    public FlashcardService() { setCards(cards); };

    public Flashcard getCard(int index) {
        return cards.get(index);
    }

    public int getCardsCount() {
        return cards.size();
    }

    public void setCards(List<Flashcard> flashcards) {
        cards = flashcards;
    }
}