package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.awt.event.KeyListener;
import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.StartScreenController.insertedTxt;

public class GameScreenController {
    private boolean gameIsRunning = false;
    private static char[] insertedChar;
    private String keyInpChar;
    private int progressI = 0;

    @FXML
    javafx.scene.control.Label fisrtLabel = new javafx.scene.control.Label();

    @FXML
    javafx.scene.control.Label secondLabel = new javafx.scene.control.Label();

    @FXML
    Pane gamePane = new Pane();

    @FXML
    public void handleSettingsBtn() throws IOException {
        Main main = new Main();
        main.switchScene("settings-menu.fxml");
    }

    @FXML
    private void initialize() {
        insertedChar = makeTxtReady();
        gamePane.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(javafx.scene.input.KeyEvent keyEvent) {
        keyInpChar = keyEvent.getCode().toString();
        System.out.println(keyInpChar);
        if ((keyEvent.getCode() == KeyCode.S) && !gameIsRunning) {    //start game
            System.out.println("Game starts now!");
            gameIsRunning = true;
        }
        if (gameIsRunning) {
            isRightInput(keyInpChar);
        }
    }

    private char[] makeTxtReady() {
        char[] insertedChar = new char[insertedTxt.length()];
        for (int i = 0; i < insertedTxt.length() - 1; i++) {    // transform String to a char array
            insertedChar[i] = insertedTxt.charAt(i);
        }
        return insertedChar;
    }

    private boolean isRightInput(String keyInpChar) {
        for (int i = 0; i < insertedTxt.length()-1; i++) {
            if (keyInpChar.equals((insertedChar[i]))) {

            }
        }
        return true;
    }
}
