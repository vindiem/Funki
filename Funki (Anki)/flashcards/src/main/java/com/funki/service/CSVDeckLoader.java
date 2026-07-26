package com.funki.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.funki.model.Flashcard;

public class CSVDeckLoader {
    private String deckPath = "";

    public CSVDeckLoader(String deckPath) {
        this.deckPath = deckPath;
    }

    public List<Flashcard> parseDeck() {
        InputStream input = CSVDeckLoader.class.getResourceAsStream(deckPath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;

        List<Flashcard> parsedCards = new ArrayList<Flashcard>();

        try {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);

                String front = parts[0];
                String back = parts[1];

                parsedCards.add(new Flashcard(front, back));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return parsedCards;
    }
}
