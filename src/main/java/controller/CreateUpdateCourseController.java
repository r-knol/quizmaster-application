package controller;

import database.mysql.CourseDAO;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import model.User;
import view.Main;

/**
 * @author Wendy Ellens
 */

public class CreateUpdateCourseController {

    private Course course;

    @FXML
    Label titleLabel;
    @FXML
    private TextField cursusID;
    @FXML
    private TextField cursusnaam;
    @FXML
    private TextField coordinatorID;
    @FXML
    private Button submitButton;

    // Huidige gebruiker aanmaken (this.user) en huidige data uit database weergeven als gebruiker al bestaat
    public void setup(Course course) {
        this.course = course;
        setupCode();
    }

    public void doCreateUpdateCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        int givenCoordinatorID = Integer.parseInt(coordinatorID.getText());
        // Controleren of het opgegeven ID bij een coordinator hoort, anders foutmelding geven
        if (userDAO.getOneById(givenCoordinatorID).getRol().equals("coordinator")) {
            if (course == null) { // Nieuwe cursus aanmaken
                course = new Course(cursusnaam.getText(), givenCoordinatorID);
                courseDAO.storeOne(course);
                Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
                aangemaakt.setContentText("Cursus aangemaakt");
                aangemaakt.show();
                setupCode(); // Gegenereerde ID tonen
            } else { // Wijzigen van een bestaande cursus
                course.setCursusNaam(cursusnaam.getText());
                course.setCoordinatorID(givenCoordinatorID);
                courseDAO.updateOne(course);
                Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
                gewijzigd.setContentText("Cursus gewijzigd");
                gewijzigd.show();
            }
        }
        else {
            Alert foutmelding = new Alert(Alert.AlertType.WARNING);
            foutmelding.setContentText("Gebruiker met dit ID is geen coordinator. " +
                    "Geef het ID van de vakcoordinator.");
            foutmelding.show();
        }
    }

    public void doMenu() {
        // Terug naar manageCourses scherm
        Main.getSceneManager().showManageCoursesScene();
    }

    public void setupCode() {
        if (course == null) {
            titleLabel.setText("Nieuwe cursus");
            cursusID.setText("");
            cursusnaam.setText("");
            coordinatorID.setText("");
            submitButton.setText("Nieuw");
        } else {
            titleLabel.setText("Wijzig cursus");
            cursusID.setText(String.valueOf(course.getCursusID()));
            cursusnaam.setText(course.getCursusNaam());
            coordinatorID.setText(String.valueOf(course.getCoordinatorID()));
            submitButton.setText("Wijzig");
        }
    }
}
