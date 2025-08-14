package com.istealmoney.inserttyping.inserttyping;

public class GameData {
    // contains game data from start screen, game screen and game finished screen

    /*  List of variables that contain '+=':
        progressI, displayedCharCounter, tMists
     */

    private static GameData instance;
    private int tMists;
    private int progressI;  // up to 40 (progress in displayedChar)
    private String insertedText;
    private boolean gameIsRunning;
    private boolean gameJustOpened = true;
    private char keyInpChar;
    private double progressPB;
    private int displayedCharCounter;   // up to insertedText.length()
    private boolean textNeedsToUpdate;
    private String themeSwitcher;
    private boolean tMistLocked;
    private char[] startInsertedChar;

    private GameData() {

    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public int getTMists() {
        return tMists;
    }

    public void setTMists(int tMists) {
        this.tMists += tMists;
    }

    public void resetTMists() {
        this.tMists = 0;
    }

    public int getProgressI() {
        return progressI;
    }

    public void setProgressI(int progressI) {
        this.progressI += progressI;
    }
    public void resetProgressI() {
        this.progressI = 0;
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
        this.displayedCharCounter += displayedCharCounter;
    }

    public int getDisplayedCharCounter() {
        return displayedCharCounter;
    }

    public void resetDisplayedCharCounter() {
        this.displayedCharCounter = 0;
    }

    public void setTextNeedsToUpdate(boolean textNeedsToUpdate) {
        this.textNeedsToUpdate = textNeedsToUpdate;
    }

    public boolean getTextNeedsToUpdate() {
        return textNeedsToUpdate;
    }

    public void setCurrentTheme(String themeSwitcher) {
        this.themeSwitcher = themeSwitcher;
    }

    public String getCurrentTheme() {
        return themeSwitcher;
    }

    public void setTMistLocked(boolean tMistStat) {
        this.tMistLocked = tMistStat;
    }

    public boolean getTMistLocked() {
        return tMistLocked;
    }

    public void setGameIsRunning(boolean gameState) {
        this.gameIsRunning = gameState;
    }

    public boolean getGameIsRunning() {
        return gameIsRunning;
    }

    public void setStartInsertedChar(char[] startInsertedChar) {
        this.startInsertedChar = startInsertedChar;
    }

    public char[] getStartInsertedChar() {
        return startInsertedChar;
    }
}
