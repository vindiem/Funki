package com.funki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.funki.model.Flashcard;

public class FlashcardService {

    private final List<Flashcard> cards = new ArrayList<>();
    private int currentIndex = 0;

    public FlashcardService() {
        cards.add(new Flashcard("apple", "яблоко"));
        cards.add(new Flashcard("dog", "собака"));
        cards.add(new Flashcard("house", "дом"));
        cards.add(new Flashcard("book", "книга"));
        cards.add(new Flashcard("computer", "компьютер"));
    }

    public Flashcard getCurrentCard() {
        return cards.get(currentIndex);
    }

    public void nextCard() {
        currentIndex++;

        if (currentIndex >= cards.size()) {
            currentIndex = 0;
        }
    }

    public void prevCard() {
        currentIndex--;

        if (currentIndex < 0) {
            currentIndex = cards.size() - 1;
        } 
    }

    public void shuffleCard() {
        Random random = new Random();
        int randomNumber = random.nextInt(0, cards.size() - 1);

        currentIndex = randomNumber;
    }
}