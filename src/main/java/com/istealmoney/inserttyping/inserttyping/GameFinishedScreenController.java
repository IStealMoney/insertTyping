package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameFinishedScreenController {

    private int tMist = 0;

    @FXML
    private Label typingMistakes;

    public GameFinishedScreenController() {

    }

    @FXML
    public void initialize() {
        showStats();
    }

    private void showStats() {
        GameScreenController gaScrCon = GameScreenController.getInstance();
        tMist = gaScrCon.getMistakes();
        typingMistakes.setText("Your typing mistakes: " + tMist);
    }
}
