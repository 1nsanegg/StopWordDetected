package engine;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Word {
    public static Set<String> stopWords;
    private String rawText;

    public Word(String rawText) {
        this.rawText = rawText;
    }

    public static Word createWord(String rawText) {
        return new Word(rawText);
    }

    public boolean isKeyword() {
        return isValidWord() && !stopWords.contains(rawText.toLowerCase());
    }
    public boolean isValidWord() {
        String regexForValidWord = "\\W*[a-zA-Z\\-']+\\W*";
        return rawText.matches(regexForValidWord) && !rawText.contains(" ");
    }

    public String getPrefix() {
        if (!isValidWord()) return "";
        for (int i = 0; i < rawText.length(); i++) {
            char presentCharacter = rawText.charAt(i);
            if (presentCharacter == '-' || presentCharacter == '\'' || Character.isLetter(presentCharacter)) {
                return rawText.substring(0, i);
            }
        }
        return "";
    }

    public String getSuffix() {
        if (!isValidWord()) return "";
        if (rawText.contains("'s")) return rawText.substring(rawText.indexOf("'s"));
        for (int i = rawText.length() - 1; i >= 0; i--) {
            char presentChar = rawText.charAt(i);
            if (presentChar == '-' || presentChar == '\'' || Character.isLetter(presentChar)) {
                return rawText.substring(i + 1);
            }
        }
        return "";
    }

    public String getText() {
        return rawText.substring(getPrefix().length(), rawText.length() - getSuffix().length());
    }

    public static boolean loadStopWords(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            stopWords = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                stopWords.add(line);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean equals(Object o) {
        Word w = (Word) o;
        return this.getText().equalsIgnoreCase(w.getText());
    }

    public String toString() {
        return rawText;
    }
}
