package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.StartScreenController.insertedTxt;

public class GameScreenController {
    private boolean gameIsRunning = false;
    private static char[] insertedChar;
    private String keyInpStr;
    private char keyInpChar;
    private int progressI = 0;
    public int typingMistakes = 0;
    private static GameScreenController instance;

    @FXML
    javafx.scene.control.Label firstLabel = new javafx.scene.control.Label();

    @FXML
    Pane gamePane = new Pane();

    @FXML
    javafx.scene.control.Button settingsBtn;

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
        settingsBtn.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && !gameIsRunning) {
                // handles space here bc KeyEvent ignores every input
                // if 'settingsBtn.setFocusTraversable(false)'
                System.out.println("game is running");
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
        keyInpStr = keyEvent.getText();
        keyInpChar = keyInpStr.charAt(0);
        if (keyEvent.getCode() == KeyCode.SPACE) {  // space is handled in initialize()
            keyEvent.consume();
        }
        try {
            if (gameIsRunning) {
                if (isRightInput(keyInpChar)) {
                    System.out.println("nice");
                }
                if (progressI == insertedTxt.length()) { // user finished typing text
                    Main main = Main.getInstance();
                    main.switchScene("game-finished-screen.fxml");
                }
            } else if ((keyEvent.getCode() == javafx.scene.input.KeyCode.SPACE) && !gameIsRunning) {    //start game
                System.out.println("Game starts now!");
                updateLabel(insertedChar);
                gameIsRunning = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //if (insertedChar[progressI] == ' ') {

       // }
        if (keyInpChar == insertedChar[progressI]) {
            System.out.println("right input");
            progressI++;
            updateLabel(insertedChar);
            return true;
        } else {
            System.out.println("wrong input");
            typingMistakes++;
        }
        return false;
    }

    private void updateLabel(char[] insertedChar) {
        firstLabel.setText(String.valueOf(insertedChar));
    }

    public static GameScreenController getInstance() {
        return instance;
    }

    public int getMistakes() {
        return typingMistakes;
    }
}
