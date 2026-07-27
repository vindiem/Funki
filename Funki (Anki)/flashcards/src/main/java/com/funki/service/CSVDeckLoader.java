package com.funki.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.funki.model.Flashcard;

public class CSVDeckLoader {
    private String deckPath = "";
    
    public CSVDeckLoader() { }
    public CSVDeckLoader(String deckPath) { this.deckPath = deckPath; }

    public List<Flashcard> parseDeck() {
        if (deckPath == "") return new ArrayList<Flashcard>(); 

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
            System.out.println(e);
        }
        
        System.out.println("Deck has been successfully parsed!");
        return parsedCards;
    }

    public List<Flashcard> parseDeck(File file) {
        List<Flashcard> parsedCards = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);

                if (parts.length != 2) {
                    // Skip malformed lines
                    continue; 
                }

                parsedCards.add(new Flashcard(parts[0], parts[1]));
            }

        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Deck has been successfully parsed (file) !");
        return parsedCards;
    }

    public void setDeckPath(String deckPath) {
        this.deckPath = deckPath;
    }

}
