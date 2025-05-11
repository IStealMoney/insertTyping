package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameScreenController {
    private final GameData gameData = GameData.getInstance();
    private boolean gameIsRunning;
    private char[] insertedChar;
    private char keyInpChar;
    private int progressI = 0;
    public int typingMistakes = 0;
    private static GameScreenController instance;
    private boolean showTMist, showProBar;
    private String insertedTxt;
    private boolean gameJustOpened;
    private double progressPB;
    private boolean tMistLocked;

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
        //firstLabel.setVisible(true);
        //userInputLabel.setVisible(false);
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
                userInputLabel.setVisible(true);
                updateLabel(insertedChar);
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
                userInputLabel.setVisible(true);
                updateLabel(insertedChar);
                gameIsRunning = true;
                startLabel.setVisible(false);
                tMistLabel.setVisible(true);
                showConfStats();
                event.consume();
            }
        });
        insertedChar = makeTxtReady();
        gamePane.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(javafx.scene.input.KeyEvent keyEvent) {
        if (gameIsRunning) {
            String keyInpStr = keyEvent.getText();
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
                if (gameIsRunning) {
                    if (isRightInput(keyInpChar)) {

                    } else {

                    }
                    checkGameFinished();
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
        char[] insertedChar = new char[insertedTxt.length()];
        for (int i = 0; i < insertedTxt.length(); i++) {    // transform String to a char array
            insertedChar[i] = insertedTxt.charAt(i);
        }
        return insertedChar;
    }

    private boolean isRightInput(char keyInpChar) {
        if (keyInpChar == insertedChar[progressI]) {
            progressI++;
            progressPB = progressI * ((double) 1 / insertedTxt.length());
            proBar.setProgress(progressPB);
            updateLabel(insertedChar);
            tMistLocked = false;
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
        if (progressI == insertedTxt.length()) { // user finished typing text
            gameIsRunning = false;
            pushToGameData();
            Main main = Main.getInstance();
            main.switchScene("game-finished-screen.fxml");
        }
    }

    private void updateLabel(char[] insertedChar) {
        firstLabel.setText(String.valueOf(insertedChar));
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
        gameData.setInsertedText(insertedTxt);
        gameData.setInsertedChar(insertedChar);
        gameData.setKeyInpChar(keyInpChar);
        gameData.setTMists(typingMistakes);
        gameData.setProgressI(progressI);
        gameData.setProgressPB(progressPB);
    }

    private void pullFromGameData() {
        GameData gameData = GameData.getInstance();
        this.insertedTxt = gameData.getInsertedText();
        this.insertedChar = gameData.getInsertedChar();
        this.keyInpChar = gameData.getKeyInpChar();
        this.typingMistakes = gameData.getTMists();
        this.progressI = gameData.getProgressI();
        this.progressPB = gameData.getProgressPB();
    }
}
