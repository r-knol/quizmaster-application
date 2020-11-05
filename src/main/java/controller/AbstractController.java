package controller;

import javafx.application.Application;
import javafx.scene.control.Alert;

/**
 * @author Wendy Ellens
 * Centraliseert gedeelde controllermethodes
 */

public abstract class AbstractController {
    protected void showInformationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.show();
    }
}
