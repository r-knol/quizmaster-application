package controller;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Question;
import model.Quiz;
import view.Main;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;

    public void setup() {
        // De volgende regel past de tekst uit de view (fxml) aan.
        welcomeLabel.setText("Welkom Student, maak een keuze uit het menu:");

        MenuItem item1 = new MenuItem("In- en uitschrijven cursus.");
        //item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
        taskMenuButton.getItems().add(item1);

        MenuItem item2 = new MenuItem("Quiz selecteren.");
        //item2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());
        taskMenuButton.getItems().add(item2);

        MenuItem item3 = new MenuItem("Quiz invullen.");
        //item3.setOnAction(event -> Main.getSceneManager().showFillOutQuiz());
        taskMenuButton.getItems().add(item3);
    }

    public void doLogout(ActionEvent event) {
        System.exit(0);
    }
}
