package controller;

import database.mysql.CourseDAO;
import database.mysql.CourseRegistrationDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import model.CourseRegistration;
import model.Quiz;
import view.Main;

import java.util.List;

public class StudentSignInOutController extends  AbstractController{

    private Course course;
    private CourseRegistration courseRegistration;

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView <Course> signedInCourseList;

    public void setup() {
        CourseRegistrationDAO courseRegistrationDAO = new CourseRegistrationDAO(Main.getDBaccess());
        CourseDAO courseDAO = new CourseDAO( Main.getDBaccess() );
        List<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            //for (int index = 0; index < allCourses.size(); index++) {
                signedOutCourseList.getItems().add(course);
            //}

        }
        signedOutCourseList.getSelectionModel().getSelectedItems();
        List<Course> allCoursesByID = courseRegistrationDAO.getAllByUserId(Main.getUser());
        for(Course c : allCoursesByID) {
            signedInCourseList.getItems().add(c);
            for (int index = 0; index < allCoursesByID.size(); index++) {
                signedOutCourseList.getItems().remove( index );
            }
        }
        signedInCourseList.getSelectionModel().getSelectedItems();
        signedOutCourseList.getSelectionModel().selectFirst();
    }


    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn()  {
        CourseRegistrationDAO courseRegistrationDAO = new CourseRegistrationDAO(Main.getDBaccess());
        CourseDAO courseDAO = new CourseDAO( Main.getDBaccess() );
        courseRegistration  = new CourseRegistration();
        courseRegistrationDAO.storeOne(courseRegistration);
        showInformationAlert( String.format( "Je bent ingeschreven voor cursus: ", course.getCursusID() + " " + course.getCursusNaam() ) );
    }

    public void doSignOut() {

    }

}
