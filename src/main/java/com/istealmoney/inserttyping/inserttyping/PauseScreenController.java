package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;

import java.io.IOException;

public class PauseScreenController {
    Main main = Main.getInstance();
    private final GameData gameData = GameData.getInstance();

    @FXML
    private void handleContinueBtn() throws IOException { //save data?
        main.switchScene("game-screen.fxml");
    }

    @FXML
    private void handleRetryBtn() throws IOException {
        gameData.setProgressI(0);
        gameData.setDisplayedCharCounter(0);
        gameData.setProgressPB(0);
        gameData.setTMists(0);

        main.switchScene("game-screen.fxml");
    }

    @FXML
    private void handleHomeBtn() throws IOException {
        gameData.setProgressI(0);
        gameData.setProgressPB(0);
        gameData.setDisplayedCharCounter(0);
        gameData.setTMists(0);
        gameData.setGameJustOpened(true);
        gameData.setInsertedText("");
        gameData.setKeyInpChar(' ');
        main.switchScene("start-screen.fxml");
    }

    @FXML
    private void handleExitBtn() {
        System.exit(0);
    }
}
