package com.istealmoney.inserttyping.inserttyping;

public class GameData {
    // contains game data from start screen, game screen and game finished screen

    private static GameData instance;
    private int tMists, progressI;
    private String insertedText;
    private boolean gameIsRunning;
    private char[] insertedChar;
    private boolean gameJustOpened = true;
    private char keyInpChar;
    private double progressPB;
    private int displayedCharCounter;
    private boolean textNeedsToUpdate;

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

    public void setGameJustOpened(boolean gameJustOpened) {
        this.gameJustOpened = gameJustOpened;
    }

    public boolean getGameJustOpened() {
        return gameJustOpened;
    }

    public void setKeyInpChar(char keyInpChar) {
        this.keyInpChar = keyInpChar;
    }

    public char getKeyInpChar() {
        return keyInpChar;
    }

    public void setProgressPB(double progressPB) {
        this.progressPB = progressPB;
    }

    public double getProgressPB() {
        return progressPB;
    }

    public void setDisplayedCharCounter(int displayedCharCounter) {
        this.displayedCharCounter = displayedCharCounter;
    }

    public int getDisplayedCharCounter() {
        return displayedCharCounter;
    }

    public void setTextNeedsToUpdate(boolean textNeedsToUpdate) {
        this.gameIsRunning = textNeedsToUpdate;
    }

    public boolean getTextNeedsToUpdate() {
        return textNeedsToUpdate;
    }
}
