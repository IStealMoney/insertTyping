package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class GameFinishedScreenController {
    private Main main = Main.getInstance();
    private GameData gameData = GameData.getInstance();

    private int tMist;
    private String insertedTxt;

    @FXML
    private TextArea statsTxtArea = new TextArea();

    @FXML
    private Label exitLabel = new Label();

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
    private void handleCopyBtn() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(statsTxtArea.getText());
        clipboard.setContents(selection, null);
    }

    @FXML
    private void handleExitBtn() {
        System.exit(0);
    }

    @FXML
    private void handleHomeBtn() throws IOException {
        gameData.resetGameData();
        main.switchScene("start-screen.fxml");
    }

    @FXML
    private void handleRetryBtn() throws IOException {
        gameData.resetGameData();
        main.switchScene("game-screen.fxml");
    }

    private void showStats() {
        tMist = gameData.getTMists();
        statsTxtArea.setText("Your typing mistakes: " + tMist);
    }

    private void pushToGameData() {

    }

    private void pullFromGameData() {
        this.tMist = gameData.getTMists();
    }
}
