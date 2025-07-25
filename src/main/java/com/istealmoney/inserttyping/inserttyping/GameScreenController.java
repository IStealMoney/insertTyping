package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameScreenController {
    private final GameData gameData = GameData.getInstance();
    private boolean gameIsRunning;
    private char keyInpChar;
    private int progressI;  // up to 40 (progress in displayedChar)
    public int typingMistakes = 0;
    private static GameScreenController instance;
    private boolean showTMist, showProBar;
    private String startInsertedTxt;
    private boolean gameJustOpened;
    private double progressPB;
    private boolean tMistLocked;
    private int displayedCharCounter;   // up to startInsertedChar.length()
    private char[] startInsertedChar;
    private boolean textNeedsToUpdate;

    @FXML
    private Pane gamePane = new Pane();

    @FXML
    private javafx.scene.control.Button settingsBtn;

    @FXML
    private javafx.scene.control.Label firstLabel = new javafx.scene.control.Label();

    @FXML
    private final javafx.scene.control.Label secondLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label startLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label tMistLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label userInputLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.ProgressBar proBar = new javafx.scene.control.ProgressBar();

    public GameScreenController() {
        instance = this;
    }

    @FXML
    public void handleSettingsBtn() throws IOException {
        pushToGameData();
        Main main = Main.getInstance();
        main.switchScene("settings-menu.fxml");
    }

    @FXML
    private void initialize() {
        firstLabel.setVisible(false);
        userInputLabel.setVisible(false);
        gameJustOpened = gameData.getGameJustOpened();
        proBar.setVisible(false);
        gameIsRunning = false;
        if (gameJustOpened) {
            gameJustOpened = false;
            gameData.setGameJustOpened(false);
            startLabel.setText("Press space to start!");
        } else {
            startLabel.setText("Press space to continue!");
        }
        pullFromGameData();
        settingsBtn.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && gameIsRunning) {
                userInputLabel.setText(String.valueOf(keyInpChar));
                //userInputLabel.setVisible(true);
                keyInpChar = ' ';
                isRightInput(keyInpChar);
                startLabel.setVisible(false);
                proBar.setVisible(true);
                tMistLabel.setVisible(true);
                showConfStats();
                event.consume();
            } else if (event.getCode() == KeyCode.SPACE && !gameIsRunning) { // start game
                // handles space here bc KeyEvent ignores every input
                // if 'settingsBtn.setFocusTraversable(false)'
                firstLabel.setVisible(true);
                userInputLabel.setVisible(true);
                startLabel.setVisible(false);
                tMistLabel.setVisible(true);
                gameIsRunning = true;
                gameData.setDisplayedCharCounter(0);
                gameData.setProgressI(0);
                showConfStats();
                event.consume();
            }
        });
        startInsertedChar = makeTxtReady();
        updateTextLabel(startInsertedChar);
        gamePane.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (gameIsRunning) {
            String keyInpStr = keyEvent.getText();
            if (keyInpStr.isEmpty()) {
                return;
            }
            keyInpChar = keyInpStr.charAt(0);
            updateUserInputLabel(keyInpChar);
            if (keyEvent.getCode() == KeyCode.SPACE) {  // space is handled in initialize()
                keyEvent.consume();
            }
            try {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    gameIsRunning = false;
                    pushToGameData();
                    Main main = Main.getInstance();
                    main.switchScene("pause-screen.fxml");
                }
                if (isRightInput(keyInpChar)) {
                    firstLabel.setStyle("-fx-text-fill: #ffffff");
                    if (gameData.getTextNeedsToUpdate()) {
                        updateTextLabel(startInsertedChar);
                    }
                    checkGameFinished();
                } else {
                    firstLabel.setStyle("-fx-text-fill: #ff0000");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserInputLabel(char key) {
        userInputLabel.setText(String.valueOf(key));
    }

    private char[] makeTxtReady() {
        startInsertedChar = new char[startInsertedTxt.length()];
        for (int i = 0; i < startInsertedTxt.length(); i++) {    // transform String to a char array
            startInsertedChar[i] = startInsertedTxt.charAt(i);
        }
        return startInsertedChar;
    }

    private boolean isRightInput(char keyInpChar) {
        displayedCharCounter = gameData.getDisplayedCharCounter();
        if (keyInpChar == startInsertedChar[displayedCharCounter]) {
            displayedCharCounter++;
            gameData.setDisplayedCharCounter(displayedCharCounter);
            progressI++;
            gameData.setProgressI(progressI);
            progressPB = displayedCharCounter * ((double) 1 / startInsertedTxt.length());
            proBar.setProgress(progressPB);
            tMistLocked = false;
            if (progressI == 40 /*|| displayedCharCounter == startInsertedTxt.length()*/) { // end of array
                gameData.setTextNeedsToUpdate(true);
                updateTextLabel(startInsertedChar);
                progressI = 0;
                gameData.setProgressI(0);
            } else {
                gameData.setTextNeedsToUpdate(false);
                textNeedsToUpdate = false;
            }
            return true;
        } else {
            if (!tMistLocked) {
                typingMistakes++;
            }
            tMistLocked = true;
            showConfStats();
        }
        return false;
    }

    private void checkGameFinished() throws IOException {
        if (displayedCharCounter == startInsertedTxt.length()) { // user finished typing text
            gameIsRunning = false;
            pushToGameData();
            Main main = Main.getInstance();
            main.switchScene("game-finished-screen.fxml");
        }
    }

    private void updateTextLabel(char[] startInsertedChar) {
        char[] displayedChar = new char[40];
        this.displayedCharCounter = gameData.getDisplayedCharCounter();
        for (int i = 0; i < startInsertedTxt.length() && i < 40; i++) {     // fill array for displaying purposes
            displayedChar[i] = startInsertedChar[displayedCharCounter];
            displayedCharCounter++;
            gameData.setDisplayedCharCounter(displayedCharCounter);
            if (displayedCharCounter >= startInsertedTxt.length()) {
                gameIsRunning = false;
                displayedCharCounter = 0;
            }
        }
        firstLabel.setText(String.valueOf(displayedChar));
    }

    private void showConfStats() {  // things that can be modified in SettingsMenuController.java
        SettingsMenuController sMenCon = SettingsMenuController.getInstance();
        if (sMenCon != null) {
            showTMist = sMenCon.getTMistState(); // settings data
            showProBar = sMenCon.getProBarState();
        } else {
            showTMist = true;
            showProBar = true;
        }
        if (showTMist) {    // show typing mistakes
            tMistLabel.setText("Typing mistakes: " + typingMistakes);
        } else {
            tMistLabel.setVisible(false);
        }
        if (showProBar) {   // show progress bar
            proBar.setProgress(progressPB);
            proBar.setVisible(true);
        } else {
            proBar.setVisible(false);
        }
    }

    public static GameScreenController getInstance() {
        return instance;
    }

    private void pushToGameData() {
        GameData gameData = GameData.getInstance();
        gameData.setInsertedText(startInsertedTxt);
        gameData.setInsertedChar(startInsertedChar);
        gameData.setKeyInpChar(keyInpChar);
        gameData.setTMists(typingMistakes);
        gameData.setProgressI(progressI);
        gameData.setProgressPB(progressPB);
        gameData.setDisplayedCharCounter(displayedCharCounter);
    }

    private void pullFromGameData() {
        GameData gameData = GameData.getInstance();
        this.startInsertedTxt = gameData.getInsertedText();
        this.startInsertedChar = gameData.getInsertedChar();
        this.keyInpChar = gameData.getKeyInpChar();
        this.typingMistakes = gameData.getTMists();
        this.progressI = gameData.getProgressI();
        this.progressPB = gameData.getProgressPB();
        this.displayedCharCounter = gameData.getDisplayedCharCounter();
    }
}
