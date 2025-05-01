package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StartScreenController {
    private static boolean isLightMode = true;
    private static String insertedText;

    @FXML
    private TextField insertionField = new TextField();

    @FXML
    private void initialize() {
        insertionField.setEditable(true);
    }

    @FXML
    public void handleLookButton() {
        isLightMode = !isLightMode;
        if (!isLightMode) { //switch to dark mode

        }
        if (isLightMode) { //switch to light mode

        }
    }

    @FXML
    public String getInsertedText() {
        insertedText = insertionField.getText();
        return insertedText;
    }
}
