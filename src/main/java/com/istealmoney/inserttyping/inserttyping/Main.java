package com.istealmoney.inserttyping.inserttyping;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Scene scene;
    private static Stage stage;
    public static boolean isLightMode = true;
    public String styleSheet = "light-mode.css";
    public static String lastScene, currentScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        swithcScene("start-screen.fxml");
        currentScene = "start-screen.fxml";
    }

    public static void main(String[] args) {
        launch();
    }

    public void swithcScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent guiRoot = loader.load();
        //guiRoot.getStylesheets().add(getClass().getResource(themeSwitcher).toExternalForm());
        lastScene = currentScene;
        try {
            scene = new Scene(guiRoot, 800, 600);
            if (fxml.equals("start-screen.fxml")) {
                currentScene = "start-screen";
                stage.setTitle("InsertTyping | start");
            } else if (fxml.equals("game-screen.fxml")) {
                currentScene = "game-screen.fxml";
                stage.setTitle("InsertTyping | game");
            } else if (fxml.equals("pause-screen")) {
                currentScene = "pause-screen";
                stage.setTitle("InsertTyping | pause");
            } else if (fxml.equals("settings-menu.fxml")) {
                currentScene = "settings-menu.fxml";
                stage.setTitle("InsertTyping | settings");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file: " + fxml);
        }
        stage.setResizable(false);
        switchTheme();
        stage.setScene(scene);
        stage.show();
    }

    public void switchTheme() {
        isLightMode = !isLightMode; // toggles other mode
        if (isLightMode) { //switch to dark mode
            styleSheet = "dark-mode.css";
        } else {
            styleSheet = "light-mode.css";
        }
        reloadTheme(styleSheet);
    }

    public void reloadTheme(String styleSheet) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
    }
}
