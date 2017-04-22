package ru.inbox.savinov_vu.util;

import javafx.scene.control.Alert;

public interface DialogManager {
     static void showInfoDialog(String title, String text) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle(title);
         alert.setContentText(text);
         alert.setHeaderText("");
         alert.showAndWait();
    }

    static void showErrorDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }

}
