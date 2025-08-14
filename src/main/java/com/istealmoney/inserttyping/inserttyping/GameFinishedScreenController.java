package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class GameFinishedScreenController {
    private final Main main = Main.getInstance();
    private final GameData gameData = GameData.getInstance();

    @FXML
    private TextArea statsTxtArea = new TextArea();

    @FXML
    private final Label exitLabel = new Label();

    public GameFinishedScreenController() {

    }

    @FXML
    public void initialize() {
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
        gameData.resetProgressI();
        gameData.setProgressPB(0);
        gameData.resetDisplayedCharCounter();
        gameData.resetTMists();
        gameData.setGameJustOpened(true);
        gameData.setInsertedText("");
        gameData.setKeyInpChar(' ');
        main.switchScene("start-screen.fxml");
    }

    @FXML
    private void handleRetryBtn() throws IOException {
        gameData.resetProgressI();
        gameData.resetDisplayedCharCounter();
        gameData.resetProgressI();
        gameData.setProgressPB(0);
        gameData.resetTMists();
        main.switchScene("game-screen.fxml");
    }

    private void showStats() {
        statsTxtArea.setText("Typing mistakes: " + gameData.getTMists());
    }
}
