package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameScreenController {

    @FXML
    javafx.scene.control.Label fisrtLabel = new javafx.scene.control.Label();

    @FXML
    javafx.scene.control.Label secondLabel = new javafx.scene.control.Label();

    @FXML
    TextField gameField = new TextField();

    @FXML
    public void handleSettingsBtn() throws IOException {
        Main main = new Main();
        main.switchScene("settings-menu.fxml");
    }

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
