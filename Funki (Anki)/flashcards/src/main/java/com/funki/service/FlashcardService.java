package com.funki.service;
import java.util.ArrayList;
import java.util.List;

import com.funki.model.Flashcard;

public class FlashcardService {
    private final List<Flashcard> cards = new ArrayList<>();

    public FlashcardService() {
        cards.add(new Flashcard("apple", "яблоко"));
        cards.add(new Flashcard("dog", "собака"));
        cards.add(new Flashcard("house", "дом"));
        cards.add(new Flashcard("book", "книга"));
        cards.add(new Flashcard("computer", "компьютер"));
    }

    public Flashcard getCard(int index) {
        return cards.get(index);
    }

    public int getCardsCount() {
        return cards.size();
    }
}