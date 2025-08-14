package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class StartScreenController {
    public String insertedTxt;
    public boolean gameJustOpened;
    private static StartScreenController instance;
    private final GameData gameData = GameData.getInstance();

    @FXML
    private TextArea insertionField = new TextArea();

    @FXML
    private void initialize() {
        instance = this;
        gameData.setGameJustOpened(true);
        insertionField.setEditable(true);
        insertionField.setText(gameData.getInsertedText());
    }

    @FXML
    public void handleSettingsBtn() throws IOException {
        gameData.setInsertedText(insertionField.getText());
        Main main = Main.getInstance();
        main.switchScene("settings-menu.fxml");
    }

    @FXML
    public void handleStartTyping() throws IOException {
        gameData.setInsertedText(insertionField.getText());
        if (checkTxt(gameData.getInsertedText())) { // check length ... make txt playable :)
            Main main = Main.getInstance();
            main.switchScene("game-screen.fxml");
        } else {
            System.out.println("txt is not ready yet!");
        }
    }

    @FXML
    public void handleClearBtn() {
        insertionField.clear();
        gameData.setInsertedText("");
    }

    public boolean checkTxt(String insertedTxt) {
        // some checks
        return !insertedTxt.isEmpty() && insertedTxt.length() < 2500;
    }

    public static StartScreenController getInstance() {
        return instance;
    }
}
