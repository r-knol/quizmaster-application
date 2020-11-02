package controller;

import database.mysql.CourseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import view.Main;

import java.util.List;

public class StudentSignInOutController {

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView <Course> signedInCourseList;

    public void setup() {
        CourseDAO courseDAO = new CourseDAO( Main.getDBaccess() );
        List<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            signedOutCourseList.getItems().add(course);
        }
        signedOutCourseList.getSelectionModel().getSelectedItems();
    }

        public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn() {

    }

    public void doSignOut() {}

    public void setupCode() {
        CourseDAO courseDAO = new CourseDAO( Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAllByStudentID(Main.getUser().getGebruikerID());
        for (Course course : allCourses) {
            signedOutCourseList.getItems().add(course);

        }
        signedOutCourseList.getSelectionModel().getSelectedItem();
        CourseDAO courseDAO1 = new CourseDAO(Main.getDBaccess());
        List<Course> allCoursesSignedIn = courseDAO.getAllByStudentID(Main.getUser().getGebruikerID());
        for (Course course : allCourses) {
            signedInCourseList.getItems().add( course );
        }
    }

}
