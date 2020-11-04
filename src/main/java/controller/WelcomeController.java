package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import view.Main;

/**
 * @author Olaf van der Kaaij
 */

public class WelcomeController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;

    public void setup() {
        // Welkomsttekst aanpassen aan de gebruiker die is ingelogd
        welcomeLabel.setText("Welkom " + Main.getUser().getVoornaam() + ", je bent ingelogd als " +
                Main.getUser().getRol());

        // Taken tonen die horen bij de rol van de ingelogde gebruiker
        switch (Main.getUser().getRol()) {

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

                /*MenuItem item4 = new MenuItem("Ga naar Quizbeheer");
                item4.setOnAction(event -> Main.getSceneManager().showManageQuizScene());
                taskMenuButton.getItems().add(item4);

                MenuItem item5 = new MenuItem("Ga naar Vragenbeheer");
                item5.setOnAction(event -> Main.getSceneManager().showManageQuestionsScene());
                taskMenuButton.getItems().add(item5);*/
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
    }

    public void doLogout(ActionEvent event) {
        Main.getSceneManager().showLoginScene();
    }

}
