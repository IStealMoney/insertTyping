package com.istealmoney.inserttyping.inserttyping;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.Main.lastScene;

public class SettingsMenuController {
    public static String[] comboOpt = {"Dark theme", "Light theme"};
    public static String themeSwitcher = "dark-mode.css";

    @FXML
    private ComboBox<String> comboTheme;

    @FXML
    public void initialize() {
        comboTheme.setItems(FXCollections.observableArrayList(comboOpt));
        comboTheme.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                switch (newValue) {
                    case "Dark theme":
                        themeSwitcher = "dark-mode.css";
                        break;
                    case "Light theme":
                        themeSwitcher = "light-mode.css";
                        break;
                    default:
                        themeSwitcher = "dark-mode.css";
                }
            }
        });
    }

    @FXML
    public void handleApplyBtn() throws IOException {
        Main main = Main.getInstance();
        main.updateTheme(themeSwitcher);
        main.switchScene(lastScene);
    }

    @FXML
    public void handleGoBackBtn() throws IOException {
        Main main = Main.getInstance();
        main.switchScene(lastScene);
    }
}
