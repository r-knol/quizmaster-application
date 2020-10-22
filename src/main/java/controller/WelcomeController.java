package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.User;
import view.Main;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;

    public void setup(User user) {
        welcomeLabel.setText("Welkom " + user.getRolNaam() + ", kies een taak uit het menu.");

        MenuItem item = new MenuItem("Door naar dashboard");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getSceneManager().showCoordinatorDashboard();
            }
        });
        taskMenuButton.getItems().add(item);
    }

    public void doLogout(ActionEvent exit) {
        System.exit(0);
    }
}
