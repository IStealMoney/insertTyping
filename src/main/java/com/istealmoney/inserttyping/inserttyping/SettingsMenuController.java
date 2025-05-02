package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;

import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.Main.lastScene;

public class SettingsMenuController {

    @FXML
    public void handleGoBackBtn() throws IOException {
        Main main = new Main();
        main.swithcScene(lastScene);
    }
}
