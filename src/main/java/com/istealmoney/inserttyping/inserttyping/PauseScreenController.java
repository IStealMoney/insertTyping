package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;

import java.io.IOException;

public class PauseScreenController {
    Main main = new Main();

    @FXML
    private void handleContinueBtn() throws IOException { //save data?
        main.switchScene("game-screen.fxml");
    }

    @FXML
    private void handleRetryBtn() throws IOException {
        main.switchScene("game-screen.fxml");
    }

    @FXML
    private void handleHomeBtn() throws IOException {
        main.switchScene("start-screen.fxml");
    }

    @FXML
    private void handleQuitBtn() {
        System.exit(0);
    }
}
