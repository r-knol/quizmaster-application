package controller;

import database.mysql.CourseDAO;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.User;
import view.Main;

import java.util.List;

/**
 * @author Wendy Ellens
 */

public class CreateUpdateCourseController {

    private Course course;
    private User coordinator;

    @FXML
    Label titleLabel;
    @FXML
    private TextField cursusID;
    @FXML
    private TextField cursusnaam;
    @FXML
    private MenuButton coordinatorTaskMenuButton;
    @FXML
    private Button submitButton;

    public void setup(Course course) {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        List<User> allUsers = userDAO.getAllByRole("coordinator");
        for (User user : allUsers) {
            MenuItem item = new MenuItem(user.getGebruikersnaam());
            item.setOnAction(event -> {
                coordinator = user;
                coordinatorTaskMenuButton.setText(coordinator.getGebruikersnaam());
            });
            coordinatorTaskMenuButton.getItems().add(item);
        }
        // TODO coordinatorTaskMenuButton.setText("Selecteer de coördinator");
        if (course == null) {
            titleLabel.setText("Nieuwe cursus");
            cursusID.setText("");
            cursusnaam.setText("");
            submitButton.setText("Nieuw");
        }
        // Gegevens van te wijzigen cursus tonen
        else {
            this.course = course;
            titleLabel.setText("Wijzig cursus");
            cursusID.setText(String.valueOf(course.getCursusID()));
            cursusnaam.setText(course.getCursusNaam());
            coordinatorTaskMenuButton.setText(coordinator.getGebruikersnaam());
            submitButton.setText("Wijzig");
        }
    }

    // Huidige cursus opslaan en huidige data uit database weergeven als cursus al bestaat
    public void doCreateUpdateCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
//        int gegevenCoordinatorID = Integer.parseInt(coordinatorID.getText());
        String gegevenCursusnaam = cursusnaam.getText();
        // Controleren of het opgegeven ID bij een coordinator hoort, anders foutmelding geven
//        if (userDAO.getOneById(gegevenCoordinatorID).getRol().equals("coordinator")) {
            // Nieuwe cursus aanmaken
            if (course == null) {
//                course = new Course(gegevenCursusnaam, userDAO.getOneById(gegevenCoordinatorID));
                course = new Course(cursusnaam.getText(), coordinator);
                courseDAO.storeOne(course);
                Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
                aangemaakt.setContentText(String.format("Cursus %s aangemaakt. \nHet cursusnummer is: %s.", course.getCursusNaam(), course.getCursusID()));
//                "Cursus " + course.getCursusNaam() + " is aangemaakt." +
//                        "\nHet cursusnummer is: " + course.getCursusID() + '.');
                aangemaakt.show();
                doMenu();
            }
            // Wijzigen van een bestaande cursus
            else {
                course.setCursusNaam(gegevenCursusnaam);
                // TODO is coordinator zo ook te wijzigen?
//                course.setCoordinator(userDAO.getOneById(gegevenCoordinatorID));
                courseDAO.updateOne(course);
                Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
                gewijzigd.setContentText("Cursus gewijzigd");
                gewijzigd.show();
                doMenu();
            }
//        }
//        else {
//            Alert foutmelding = new Alert(Alert.AlertType.WARNING);
//            foutmelding.setContentText("Gebruiker met dit ID is geen coordinator. " +
//                    "Geef het ID van de vakcoordinator.");
//            foutmelding.show();
//        }
    }

    public void doMenu() {
        // Terug naar manageCourses scherm
        Main.getSceneManager().showManageCoursesScene();
    }
}
