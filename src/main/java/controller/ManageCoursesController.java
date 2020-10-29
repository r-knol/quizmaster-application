package controller;

import database.mysql.CourseDAO;
import javafx.fxml.FXML;
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

    // Alle courses in ListView userList laten zien
    public void setup() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAllByCoordinatorID(Main.getUser().getGebruikerID());
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        }
        courseList.getSelectionModel().selectFirst(); // selecteert de eerste cursus op de lijst
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateCourse() {
        Main.getSceneManager().showCreateUpdateCourseScene(null); // pakt lege cursus
    }

    public void doUpdateCourse() {
        Course course = courseList.getSelectionModel().getSelectedItem(); // pakt geselecteerde item
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    public void doDeleteCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        Course course = courseList.getSelectionModel().getSelectedItem();
        // TODO waarschuwing: weet je het zeker? Zo ja:
        courseDAO.deleteOne(course);
    }
}
