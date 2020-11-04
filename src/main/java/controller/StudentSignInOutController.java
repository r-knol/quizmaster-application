package controller;

import database.mysql.CourseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import view.Main;
import java.util.List;

public class StudentSignInOutController extends  AbstractController{

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView <Course> signedInCourseList;

    public void setup() {
        CourseDAO courseDAO = new CourseDAO( Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            signedOutCourseList.getItems().add(course);
        }
        signedOutCourseList.getSelectionModel().getSelectedItems();
        List<Course> allCoursesByID = courseDAO.getAllByStudentID(Main.getUser().getGebruikerID());
        System.out.println(Main.getUser().getGebruikerID());
        for(Course c : allCoursesByID) {
            signedInCourseList.getItems().add(c);
            //System.out.println(c);
            for (int index = 0; index < signedOutCourseList.getItems().size(); index++) {
                  Course oldCourse = signedOutCourseList.getItems().get(index);
                  if (oldCourse.getCursusNaam().equals(c.getCursusNaam())) {
                      signedOutCourseList.getItems().remove(index);
                      break;
                  }
            }
        }
        signedInCourseList.getSelectionModel().getSelectedItems();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn()  {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        Course course = signedOutCourseList.getSelectionModel().getSelectedItem();
        courseDAO.storeOneInCourseRegistration(course);
        //System.out.println(course);
        showInformationAlert( String.format( "Je bent ingeschreven voor cursus: ", course.getCursusID() + " " + course.getCursusNaam() ) );
        signedOutCourseList.getItems().clear();
        signedInCourseList.getItems().clear();
        setup();
    }

    public void doSignOut() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        Course course = signedInCourseList.getSelectionModel().getSelectedItem();
        courseDAO.deleteOneFromCourseRegistration(course);
        System.out.println(course);
        showInformationAlert( String.format( "Je bent uitgeschreven voor cursus: ", course.getCursusID() + " " + course.getCursusNaam() ) );
        signedOutCourseList.getItems().clear();
        signedInCourseList.getItems().clear();
        setup();
    }

}
