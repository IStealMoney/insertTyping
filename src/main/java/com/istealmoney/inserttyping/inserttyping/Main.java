package com.istealmoney.inserttyping.inserttyping;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.SettingsMenuController.themeSwitcher;

public class Main extends Application {
    private static Scene scene;
    private static Stage stage;
    public static String lastScene, currentScene;
    private static Main instance;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        stage = primaryStage;
        currentScene = "start-screen.fxml";
        switchScene(currentScene);
    }

    public static void main(String[] args) {
        launch();
    }

    public void switchScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent guiRoot = loader.load();
        guiRoot.getStylesheets().add(getClass().getResource(themeSwitcher).toExternalForm());
        lastScene = currentScene;
        try {
            scene = new Scene(guiRoot, 800, 600);
            if (fxml.equals("start-screen.fxml")) {
                currentScene = "start-screen.fxml";
                stage.setTitle("InsertTyping | start");
            } else if (fxml.equals("game-screen.fxml")) {
                currentScene = "game-screen.fxml";
                stage.setTitle("InsertTyping | game");
            } else if (fxml.equals("pause-screen")) {
                currentScene = "pause-screen.fxml";
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
        stage.setScene(scene);
        stage.show();
    }

    public void updateTheme(String themeSwitcher) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(themeSwitcher).toExternalForm());
    }

    public static Main getInstance() {
        return instance;
    }
}
