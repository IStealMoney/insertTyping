package com.istealmoney.inserttyping.inserttyping;

public class GameData {
    // contains game data from start screen, game screen and game finished screen

    private static GameData instance;
    private int tMists, progressI = 0;
    private String insertedText;
    private boolean gameIsRunning;
    private char[] insertedChar;

    private GameData() {
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public char[] getInsertedChar() {
        return insertedChar;
    }

    public void setInsertedChar(char[] insertedChar) {
        this.insertedChar = insertedChar;
    }

    public int getTMists() {
        return tMists;
    }

    public void setTMists(int tMists) {
        this.tMists = tMists;
    }

    public int getProgressI() {
        return progressI;
    }

    public void setProgressI(int progressI) {
        this.progressI = progressI;
    }

    public String getInsertedText() {
        return insertedText;
    }

    public void setInsertedText(String insertedText) {
        this.insertedText = insertedText;
    }

    // time
    // progress bar
}
