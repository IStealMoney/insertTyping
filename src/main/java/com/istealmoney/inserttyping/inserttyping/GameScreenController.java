package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.StartScreenController.insertedTxt;

public class GameScreenController {
    private boolean gameIsRunning = false;
    private static char[] insertedChar;
    private char keyInpChar;
    private int progressI = 0;
    public int typingMistakes = 0;
    private static GameScreenController instance;
    private boolean showTMist, showProBar;

    @FXML
    private Pane gamePane = new Pane();

    @FXML
    private javafx.scene.control.Button settingsBtn;

    @FXML
    private javafx.scene.control.Label firstLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label secondLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label startLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label typos = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label userInputLabel = new javafx.scene.control.Label();

    public GameScreenController() {
        instance = this;
    }

    @FXML
    public void handleSettingsBtn() throws IOException {
        Main main = new Main();
        main.switchScene("settings-menu.fxml");
    }

    @FXML
    private void initialize() {
        typos.setVisible(false);
        showConfStats();
        settingsBtn.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && !gameIsRunning) {   // start game
                // handles space here bc KeyEvent ignores every input
                // if 'settingsBtn.setFocusTraversable(false)'
                System.out.println("game is running");
                startLabel.setVisible(false);
                typos.setVisible(true);
                updateLabel(insertedChar);
                gameIsRunning = true;
                event.consume();
            } else if (event.getCode() == KeyCode.SPACE && gameIsRunning) {
                updateLabel(insertedChar);
                keyInpChar = ' ';
                isRightInput(keyInpChar);
                event.consume();
            }
        });
        insertedChar = makeTxtReady();
        gamePane.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(javafx.scene.input.KeyEvent keyEvent) {
        String keyInpStr = keyEvent.getText();
        keyInpChar = keyInpStr.charAt(0);
        updateUserInputLabel(keyInpChar);
        if (keyEvent.getCode() == KeyCode.SPACE) {  // space is handled in initialize()
            keyEvent.consume();
        }
        try {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                Main main = Main.getInstance();
                main.switchScene("pause-screen.fxml");
            }
            if (gameIsRunning) {
                if (isRightInput(keyInpChar)) {
                    // move char in label to the left
                }
                checkGameFinished();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        System.out.println("You typed: " + keyInpChar);
        System.out.println("I want: " + insertedChar[progressI]);
        if (keyInpChar == insertedChar[progressI]) {
            System.out.println("right input");
            progressI++;
            updateLabel(insertedChar);
            return true;
        } else {
            System.out.println("wrong input");
            typingMistakes++;
            typos.setText("Typing mistakes: " + typingMistakes);
        }
        return false;
    }

    private void checkGameFinished() throws IOException {
        if (progressI == insertedTxt.length()) { // user finished typing text
            Main main = Main.getInstance();
            main.switchScene("game-finished-screen.fxml");
        }
    }

    private void updateLabel(char[] insertedChar) {
        firstLabel.setText(String.valueOf(insertedChar));
    }

    private void showConfStats() {
        SettingsMenuController sMenCon = SettingsMenuController.getInstance();
        if (sMenCon != null) {
            showTMist = sMenCon.getTMistState();
            showProBar = sMenCon.getProBarState();
        } else {
            showTMist = true;
            showProBar = true;
        }
        if (showTMist) {    // show typing mistakes
            typos.setText("Typing Mistakes: " + typingMistakes);
        }
        if (showProBar) {   // show progress bar

        }
    }

    public int getMistakes() {
        return typingMistakes;
    }

    public static GameScreenController getInstance() {
        return instance;
    }
}
