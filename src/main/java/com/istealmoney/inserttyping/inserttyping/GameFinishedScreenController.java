package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class GameFinishedScreenController {

    private int tMist = 0;

    @FXML
    private TextArea statsTxtArea = new TextArea();

    @FXML
    private Label exitLabel = new Label();

    @FXML
    private Button copyBtn = new Button();

    public GameFinishedScreenController() {

    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    public void initialize() {
        statsTxtArea.setEditable(false);
        exitLabel.setVisible(false);
        showStats();
    }

    private void showStats() {
        GameScreenController gaScrCon = GameScreenController.getInstance();
        tMist = gaScrCon.getMistakes();
        statsTxtArea.setText("Your typing mistakes: " + tMist);
    }
}
