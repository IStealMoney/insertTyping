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
    public static String currentScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        swithcScene("start-screen.fxml");
    }

    public static void main(String[] args) {
        launch();
    }

    public void swithcScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent guiRoot = loader.load();
        //guiRoot.getStylesheets().add(getClass().getResource(themeSwitcher).toExternalForm());
        try {
            if (fxml.equals("start-screen.fxml")) {
                scene = new Scene(guiRoot, 800, 600);
                currentScene = "start-screen";
            } else if (fxml.equals("game-screen.fxml")) {
                scene = new Scene(guiRoot, 800, 600);
                currentScene = "game-screen.fxml";

            } else if (fxml.equals("pause-screen")) {
                scene = new Scene(guiRoot, 800, 600);
                currentScene = "pause-screen";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file: " + fxml);
        }
        stage.setTitle("InsertTyping!");
        stage.setResizable(false);
        stage.setScene(scene);
        switchTheme();
        stage.show();
    }

    public void switchTheme() {
        isLightMode = !isLightMode; // toggles other mode
        if (isLightMode) { //switch to dark mode
            styleSheet = "dark-mode.css";
        } else {
            styleSheet = "light-mode.css";
        }
        reloadTheme();
    }

    public void reloadTheme() {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
    }
}
