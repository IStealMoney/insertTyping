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
    private String keyInpStr;
    private char keyInpChar;
    private int progressI = 0;

    @FXML
    javafx.scene.control.Label firstLabel = new javafx.scene.control.Label();

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
        keyInpStr = keyEvent.getCode().toString();
        keyInpChar = keyInpStr.charAt(0);
        System.out.println(keyInpChar);
        if (gameIsRunning) {
            if (isRightInput(keyInpChar)) {
                System.out.println("nice");
            }
            if (progressI == insertedTxt.length()) { // user finished typing text
                txtFinished();
            }
        } else if ((keyEvent.getCode() == KeyCode.S) && !gameIsRunning) {    //start game
            System.out.println("Game starts now!");
            updateLabel(insertedChar);
            gameIsRunning = true;
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
        System.out.println("I want:" + insertedChar[progressI]);
        if (keyInpChar == insertedChar[progressI]) {
            System.out.println("right input");
            progressI++;
            updateLabel(insertedChar);
            return true;
        } else {
            System.out.println("wrong input");
        }
        return false;
    }

    private void updateLabel(char[] insertedChar) {
        firstLabel.setText(String.valueOf(insertedChar));
    }

    private void txtFinished() {
        firstLabel.setText("Stats"); // copy button?
        //System.exit(0);
    }
}
