package controller;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.Question;
import model.Quiz;
import view.Main;

/*
    @author Olaf van der Kaaij
 */

public class WelcomeController {

    private DBAccess dBaccess;

    public WelcomeController() {
        this.dBaccess = Main.getDBaccess();
    }

    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;

    public void setup() {
        // De volgende regel past de tekst uit de view (fxml) aan gebruiker die is ingelogd aan.
        welcomeLabel.setText("Welkom " + Main.getUser().getNaam() + ",\nje bent ingelogd als " + Main.getUser().getRolNaam());

        // Per ingelogde gebruiker krijg je een welkomscherm die hoort bij de rol van de gebruiker.
        switch (Main.getUser().getRolNaam()) {

            case "student" :

                MenuItem item1 = new MenuItem("In- en uitschrijven cursus");
                item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
                taskMenuButton.getItems().add(item1);

                MenuItem item2 = new MenuItem("Quiz selecteren");
                item2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());
                taskMenuButton.getItems().add(item2);
                break;

            case "coordinator" :

                MenuItem item3 = new MenuItem("Ga naar Dashboard");
                item3.setOnAction(event -> Main.getSceneManager().showCoordinatorDashboard());
                taskMenuButton.getItems().add(item3);

                MenuItem item4 = new MenuItem("Ga naar Quizbeheer");
                item4.setOnAction(event -> Main.getSceneManager().showManageQuizScene());
                taskMenuButton.getItems().add(item4);

                MenuItem item5 = new MenuItem("Ga naar Vragenbeheer.");
                item5.setOnAction(event -> Main.getSceneManager().showManageQuestionsScene());
                taskMenuButton.getItems().add(item5);
                break;

            case "administrator" :

                MenuItem item6 = new MenuItem("Ga naar Cursusbeheer");
                item6.setOnAction(event -> Main.getSceneManager().showManageCoursesScene());
                taskMenuButton.getItems().add(item6);
                break;

            case "technisch beheerder" :

                MenuItem item7 = new MenuItem("Ga naar Gebruikersbeheer");
                item7.setOnAction(event -> Main.getSceneManager().showManageUserScene());
                taskMenuButton.getItems().add(item7);
                break;
        }

        /*if (Main.getUser().getRolNaam().equals("student")) {
            MenuItem item1 = new MenuItem("In- en uitschrijven cursus.");
            item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
            taskMenuButton.getItems().add(item1);
            MenuItem item2 = new MenuItem("Quiz selecteren.");
            item2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());
            taskMenuButton.getItems().add(item2);
            MenuItem item3 = new MenuItem("Quiz invullen.");
            item3.setOnAction(event -> Main.getSceneManager().showFillOutQuiz(quiz));
            taskMenuButton.getItems().add(item3);
        } else if (Main.getUser().getRolNaam().equals("coordinator")) {
            MenuItem item = new MenuItem("Ga door naar Dashboard.");
            item.setOnAction(event -> Main.getSceneManager().showCoordinatorDashboard());
            taskMenuButton.getItems().add(item);
        } else if (Main.getUser().getRolNaam().equals("administrator")) {
            MenuItem item = new MenuItem("Ga door naar Cursusbeheer");
            item.setOnAction(event -> Main.getSceneManager().showManageCoursesScene());
            taskMenuButton.getItems().add(item);
        } else {
            MenuItem item = new MenuItem("Ga naar Gebruikersbeheer.");
            item.setOnAction(event -> Main.getSceneManager().showManageUserScene());
            taskMenuButton.getItems().add(item);
        }*/
    }
    // uitloggen
    public void doLogout(ActionEvent event) {
        dBaccess.closeConnection();
        Main.getSceneManager().showLoginScene();
    }
}
