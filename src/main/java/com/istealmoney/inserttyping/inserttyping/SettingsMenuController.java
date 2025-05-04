package com.istealmoney.inserttyping.inserttyping;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.io.IOException;

import static com.istealmoney.inserttyping.inserttyping.Main.lastScene;

public class SettingsMenuController {
    public static String[] comboOpt = {"Dark theme", "Light theme"};
    public static String themeSwitcher = "dark-mode.css";
    private static boolean showTMist = true;
    private static boolean showProBar = true;
    private static SettingsMenuController instance;

    @FXML
    private ComboBox<String> comboTheme;

    @FXML
    private CheckBox tMistCB, proBarCB;

    public SettingsMenuController() {
        instance = this;
    }

    @FXML
    public void initialize() {
        // ComboBox
        comboTheme.setItems(FXCollections.observableArrayList(comboOpt));
        comboTheme.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                switch (newValue) {
                    case "Light theme":
                        themeSwitcher = "light-mode.css";
                        break;
                    default:
                        themeSwitcher = "dark-mode.css";
                }
            }
        });
        // CheckBox
        tMistCB.setSelected(showTMist);
        tMistCB.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                showTMist = tMistCB.isSelected();
            }
        });
        proBarCB.setSelected(showProBar);
        proBarCB.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                showProBar = proBarCB.isSelected();
            }
        });
    }

    @FXML
    private void handleApplyBtn() throws IOException {
        Main main = Main.getInstance();
        main.updateTheme(themeSwitcher);
        main.switchScene(lastScene);
    }

    @FXML
    private void handleGoBackBtn() throws IOException {
        Main main = Main.getInstance();
        main.switchScene(lastScene);
    }

    public static SettingsMenuController getInstance() {
        return instance;
    }

    public boolean getTMistState() {
        return showTMist;
    }

    public boolean getProBarState() {
        return showProBar;
    }
}
