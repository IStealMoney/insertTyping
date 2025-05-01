package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class GameScreenController {

    @FXML javafx.scene.control.Label fisrtLabel = new javafx.scene.control.Label();
    @FXML javafx.scene.control.Label secondLabel = new javafx.scene.control.Label();
    @FXML TextField gameField = new TextField();

    public GameScreenController() {
        initialize();
    }

    private void initialize() {
        gameField.setOnKeyPressed(event -> {
            System.out.println("Key pressed " + event.getCharacter());
        });
    }
    public void processKeyEvent(KeyEvent keyEvent) {

    }

    public boolean inputIsCorrect() {

        return true;
    }
}
