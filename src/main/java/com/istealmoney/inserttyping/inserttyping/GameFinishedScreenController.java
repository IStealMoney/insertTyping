package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GameFinishedScreenController {

    private int tMist;

    @FXML
    private TextArea statsTxtArea = new TextArea();

    @FXML
    private Label exitLabel = new Label();

    @FXML
    private Button copyBtn = new Button();

    public GameFinishedScreenController() {

    }

    @FXML
    public void initialize() {
        pullFromGameData();
        statsTxtArea.setEditable(false);
        exitLabel.setVisible(false);
        showStats();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleCopyBtn() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(statsTxtArea.getText());
        clipboard.setContents(selection, null);
    }

    private void showStats() {
        GameData gameData = GameData.getInstance();
        tMist = gameData.getTMists();
        statsTxtArea.setText("Your typing mistakes: " + tMist);
    }

    private void pushToGameData() {

    }

    private void pullFromGameData() {
        GameData gameData = GameData.getInstance();
        this.tMist = gameData.getTMists();
    }
}
