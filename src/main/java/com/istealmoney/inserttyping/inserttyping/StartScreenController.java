package com.istealmoney.inserttyping.inserttyping;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class StartScreenController {
    private String insertedTxt;
    @FXML private TextArea insertionField = new TextArea();

    @FXML private void initialize() {
        insertionField.setEditable(true);
        insertionField.setPromptText("Type your text here...");
    }

    @FXML public void handleThemeButton() {
        Main main = new Main();
        main.switchTheme();
    }

    @FXML public void handleStartTyping() throws IOException {
        insertedTxt = getInsertedText();
        if (makeTxtRdy(insertedTxt)) { // check length ... make txt playable :)
            Main main = new Main();
            main.swithcScene("game-screen.fxml");
        } else {
            System.out.println("txt is not ready yet!");
        }
    }

    @FXML public void handleClearBtn() {
        insertionField.clear();
    }

    public boolean makeTxtRdy(String insertedTxt) {
        char[] insertedChar = new char[insertedTxt.length()];
        if (!insertedTxt.isEmpty() && insertedTxt.length() < 2500) { // some checks
            for (int i = 0; i < insertedTxt.length()-1; i++) {    // transform String to a char array
                insertedChar[i] = insertedTxt.charAt(i);
            }
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
