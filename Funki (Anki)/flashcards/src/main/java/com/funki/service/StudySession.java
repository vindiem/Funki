package com.funki.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.funki.model.Flashcard;

public class StudySession {
    private final FlashcardService service;

    private int currentIndex = 0;
    private boolean flipped = false;

    private final List<Integer> shuffleOrder = new ArrayList<>();
    private int shufflePosition = 0;

    public StudySession(FlashcardService service) { this.service = service; }

    // Current card
    public Flashcard getCurrentCard() { return service.getCard(currentIndex); }
    public int getCurrentIndex() { return currentIndex; }

    // Navigation
    public void nextCard() {
        currentIndex++;
        if (currentIndex >= service.getCardsCount()) {
            currentIndex = 0;
        }
        resetCard();
    }

    public void prevCard() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = service.getCardsCount() - 1;
        }
        resetCard();
    }

    // FLip state
    public boolean isFlipped() { return flipped; }
    public void flipCard() { flipped = !flipped; }
    public void resetCard() { flipped = false; }

    // Shuffle 
    public void shuffleCard() {
        if (shuffleOrder.isEmpty() || shufflePosition >= shuffleOrder.size()) {
            createShuffleOrder();
        }

        currentIndex = shuffleOrder.get(shufflePosition++);
        resetCard();
    }

    private void createShuffleOrder() {
        shuffleOrder.clear();
        for (int i = 0; i < service.getCardsCount(); i++) {
            shuffleOrder.add(i);
        }

        Collections.shuffle(shuffleOrder);
        shufflePosition = 0;
    }

}
