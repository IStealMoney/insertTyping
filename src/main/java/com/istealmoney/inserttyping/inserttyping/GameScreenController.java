package com.istealmoney.inserttyping.inserttyping;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameScreenController {
    private final GameData gameData = GameData.getInstance();
    private char keyInpChar;
    private static GameScreenController instance;
    private boolean showTMist, showProBar;
    char[] displayedChar = new char[40];
    private char[] startInsertedChar;

    @FXML
    private Pane gamePane = new Pane();

    @FXML
    private javafx.scene.control.Button settingsBtn;

    @FXML
    private javafx.scene.control.Label firstLabel = new javafx.scene.control.Label();

    @FXML
    private final javafx.scene.control.Label secondLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label startLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label tMistLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label userInputLabel = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.ProgressBar proBar = new javafx.scene.control.ProgressBar();

    public GameScreenController() {
        instance = this;
    }

    @FXML
    public void handleSettingsBtn() throws IOException {
        Main main = Main.getInstance();
        main.switchScene("settings-menu.fxml");
    }

    @FXML
    private void initialize() {
        firstLabel.setVisible(false);
        userInputLabel.setVisible(false);
        proBar.setVisible(false);
        gameData.setGameIsRunning(false);
        if (gameData.getGameJustOpened()) {
            gameData.setGameJustOpened(false);
            gameData.setTextNeedsToUpdate(true);
            gameData.setGameJustOpened(false);
            startLabel.setText("Press space to start!");
        } else {
            startLabel.setText("Press space to continue!");
        }
        settingsBtn.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE && gameData.getGameIsRunning()) {
                userInputLabel.setText(String.valueOf(keyInpChar));
                userInputLabel.setVisible(true);
                keyInpChar = ' ';
                isRightInput(keyInpChar);
                startLabel.setVisible(false);
                proBar.setVisible(true);
                tMistLabel.setVisible(true);
                firstLabel.setVisible(true);
                showConfStats();
                event.consume();
            } else if (event.getCode() == KeyCode.SPACE && !gameData.getGameIsRunning()) { // start game
                // handles space here bc KeyEvent ignores every input
                // if 'settingsBtn.setFocusTraversable(false)'
                startLabel.setVisible(false);
                firstLabel.setVisible(true);
                userInputLabel.setVisible(true);
                tMistLabel.setVisible(true);
                gameData.setGameIsRunning(true);
                gameData.resetDisplayedCharCounter();
                gameData.resetProgressI();
                showConfStats();
                event.consume();
            }
        });
        startInsertedChar = makeTxtReady();
        if (gameData.getTextNeedsToUpdate()) {
            gameData.setTextNeedsToUpdate(false);
            updateTextLabel(startInsertedChar);
        }
        gamePane.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (gameData.getGameIsRunning()) {
            String keyInpStr = keyEvent.getText();
            if (keyInpStr.isEmpty()) {
                return;
            }
            keyInpChar = keyInpStr.charAt(0);
            userInputLabel.setText(String.valueOf(keyInpChar));
            if (keyEvent.getCode() == KeyCode.SPACE) {  // space is handled in initialize()
                keyEvent.consume();
            }
            try {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    gameData.setGameIsRunning(false);
                    Main main = Main.getInstance();
                    main.switchScene("pause-screen.fxml");
                }
                if (isRightInput(keyInpChar)) {
                    switch (gameData.getCurrentTheme()) {
                        case "dark-mode.css":
                            firstLabel.setStyle("-fx-text-fill: #ffffff");
                            break;
                        case "light-mode.css":
                            firstLabel.setStyle("-fx-text-fill: #000000");
                            break;
                        default:
                    }
                    if (gameData.getTextNeedsToUpdate()) {
                        gameData.setTextNeedsToUpdate(false);
                        updateTextLabel(startInsertedChar);
                    }
                    checkGameFinished();
                } else {
                    firstLabel.setStyle("-fx-text-fill: #ff0000");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private char[] makeTxtReady() {
        startInsertedChar = new char[gameData.getInsertedText().length()];
        for (int i = 0; i < gameData.getInsertedText().length(); i++) {    // transform String to a char array
            startInsertedChar[i] = gameData.getInsertedText().charAt(i);
        }
        gameData.setStartInsertedChar(startInsertedChar);
        return startInsertedChar;
    }

    private boolean isRightInput(char keyInpChar) {
        System.out.println("DispCharCount1:" + gameData.getDisplayedCharCounter());
        System.out.println("ProgressI: " + gameData.getProgressI());
        if (keyInpChar == startInsertedChar[gameData.getProgressI()]) {
            gameData.setProgressI(1); //+=1
            gameData.setDisplayedCharCounter(1);
            gameData.setProgressPB(gameData.getDisplayedCharCounter() * ((double) 1 / gameData.getInsertedText().length()));
            proBar.setProgress(gameData.getProgressPB());
            gameData.setTMistLocked(false);
            if (gameData.getProgressI() == 40 || gameData.getDisplayedCharCounter() == gameData.getInsertedText().length()) { // end of array
                System.out.println("lol");
                if(gameData.getDisplayedCharCounter() != gameData.getInsertedText().length()) { // not end of game
                    gameData.setTextNeedsToUpdate(true);
                    updateTextLabel(startInsertedChar);
                    gameData.setTextNeedsToUpdate(false);
                }
                gameData.resetProgressI();
            } else {
                gameData.setTextNeedsToUpdate(false);
            }
            return true;
        } else {
            if (!gameData.getTMistLocked()) {
                gameData.setTMists(1); // +=1 in GSC
                System.out.println(gameData.getTMists());
            }
            gameData.setTMistLocked(true);
            showConfStats();
        }
        return false;
    }

    private void checkGameFinished() throws IOException {
        if (gameData.getDisplayedCharCounter() == gameData.getInsertedText().length()) { // user finished typing text
            gameData.setGameIsRunning(false);
            Main main = Main.getInstance();
            main.switchScene("game-finished-screen.fxml");
        }
    }

    private void updateTextLabel(char[] startInsertedChar) {
        int counter = gameData.getDisplayedCharCounter();   // counts from displayedCharCounter independently
        System.out.println("displayedCharCounter (updateTxtLabel):" + gameData.getDisplayedCharCounter());
        for (int i = 0; i < gameData.getInsertedText().length() && i < 40; i++) {     // fill array for displaying purposes, max 40 chars
            displayedChar[i] = startInsertedChar[counter];
            counter++;
            System.out.println(counter);
            if (counter > gameData.getInsertedText().length()+1) {
                System.out.println("counter > textlength");
                gameData.setGameIsRunning(false);
                gameData.resetDisplayedCharCounter();
            }
        }
        firstLabel.setText(String.valueOf(displayedChar));
    }

    private void showConfStats() {  // things that can be modified in SettingsMenuController.java
        SettingsMenuController sMenCon = SettingsMenuController.getInstance();
        if (sMenCon != null) {
            showTMist = sMenCon.getTMistState(); // data from SMC
            showProBar = sMenCon.getProBarState();
        } else {
            showTMist = true;
            showProBar = true;
        }
        if (showTMist) {    // show typing mistakes
            tMistLabel.setText("Typing mistakes: " + gameData.getTMists());
        } else {
            tMistLabel.setVisible(false);
        }
        if (showProBar) {   // show progress bar
            proBar.setProgress(gameData.getProgressPB());
            proBar.setVisible(true);
        } else {
            proBar.setVisible(false);
        }
    }

    public static GameScreenController getInstance() {
        return instance;
    }
}
