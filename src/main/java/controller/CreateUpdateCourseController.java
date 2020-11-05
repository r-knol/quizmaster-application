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
 * Regelt de functionaliteiten in het createUpdateCourse scherm
 */

public class CreateUpdateCourseController extends AbstractController {

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

        // Dropdownmenu maken met alle coördinatoren
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

        // Scherm voor het aanmaken van een nieuwe cursus
        if (course == null) {
            titleLabel.setText("Nieuwe cursus");
            submitButton.setText("Maak");
        }

        // Scherm voor het wijzigen van een bestaande cursus
        else {
            this.course = course;
            this.coordinator = course.getUser();
            cursusID.setText(String.valueOf(course.getCursusID()));
            cursusnaam.setText(course.getCursusNaam());
            coordinatorTaskMenuButton.setText(course.getUser().getGebruikersnaam());
            submitButton.setText("Wijzig");
        }
    }

    public void doCreateUpdateCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());

        // Nieuwe cursus opslaan in de database
        if (course == null) {
            course = new Course(cursusnaam.getText(), coordinator);
            courseDAO.storeOne(course);
            showInformationAlert(String.format("Cursus %s aangemaakt", course.getCursusID()));
            doMenu();
        }

        // Wijzigen van een bestaande cursus in de database
        else {
            course.setCursusNaam(cursusnaam.getText());
            course.setUser(coordinator);
            courseDAO.updateOne(course);
            showInformationAlert("Cursus gewijzigd");
            doMenu();
        }
    }

    public void doMenu() {
        Main.getSceneManager().showManageCoursesScene();
    }
}
