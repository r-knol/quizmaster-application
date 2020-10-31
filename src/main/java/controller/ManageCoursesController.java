package controller;

import database.mysql.CourseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Course;
import view.Main;

import java.util.List;

/**
 * @author Wendy Ellens
 */

public class ManageCoursesController {

    @FXML
    ListView<Course> courseList;

    public void setup() {
        setupCode();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateCourse() {
        Main.getSceneManager().showCreateUpdateCourseScene(null); // geeft lege cursus mee
    }

    public void doUpdateCourse() {
        Course course = courseList.getSelectionModel().getSelectedItem(); // geeft te wijzigen cursus
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    public void doDeleteCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        Course course = courseList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Cursus verwijderd");
        alert.show();
        courseDAO.deleteOne(course);
        courseList.getItems().clear();
        setupCode();
    }

    public void setupCode() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        }
        courseList.getSelectionModel().selectFirst(); // selecteert de eerste cursus op de lijst
    }
}
