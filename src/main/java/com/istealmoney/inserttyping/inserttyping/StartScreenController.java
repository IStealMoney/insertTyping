package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class StartScreenController {
    public static String insertedTxt;

    @FXML
    private TextArea insertionField = new TextArea();

    @FXML
    private void initialize() {
        insertionField.setEditable(true);
        insertionField.setPromptText("Type your text here...");
    }

    @FXML
    public void handleSettingsBtn() throws IOException {
        Main main = Main.getInstance();
        main.switchScene("settings-menu.fxml");
    }

    @FXML
    public void handleStartTyping() throws IOException {
        insertedTxt = getInsertedText();
        if (checkTxt(insertedTxt)) { // check length ... make txt playable :)
            Main main = Main.getInstance();
            main.switchScene("game-screen.fxml");
        } else {
            System.out.println("txt is not ready yet!");
        }
    }

    @FXML
    public void handleClearBtn() {
        insertionField.clear();
    }

    public boolean checkTxt(String insertedTxt) {
        if (!insertedTxt.isEmpty() && insertedTxt.length() < 2500) { // some checks
            return true;
        } else {
            return false;
        }
    }

    public String getInsertedText() {
        System.out.println(insertionField.getText());
        return insertionField.getText();
    }
}
