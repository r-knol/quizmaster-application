package controller;

import database.mysql.CourseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
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
        if (course == null) {
            titleLabel.setText("Nieuwe cursus");
            cursusID.setText("");
            cursusnaam.setText("");
            coordinatorID.setText("");
            submitButton.setText("Nieuw");
        } else {
            cursusID.setText(String.valueOf(course.getCursusID()));
            cursusnaam.setText(course.getCursusNaam());
            coordinatorID.setText(String.valueOf(course.getCoordinatorID()));
            submitButton.setText("Wijzig");
        }
    }

    public void doCreateUpdateCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        if (course == null) { // Nieuwe cursus aanmaken
            course = new Course(cursusnaam.getText(), Integer.parseInt(coordinatorID.getText()));
            courseDAO.storeOne(course);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Cursus aangemaakt");
            aangemaakt.show();
            course = null;
        }
        else { // Wijzigen van een bestaande cursus
            course.setCursusNaam(cursusnaam.getText());
            course.setCoordinatorID(Integer.parseInt(coordinatorID.getText()));
            courseDAO.updateOne(course);
            Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
            gewijzigd.setContentText("Cursus gewijzigd");
            gewijzigd.show();
        }
    }

    public void doMenu() {
        // Terug naar manageCourses scherm
        Main.getSceneManager().showManageCoursesScene();
    }
}
