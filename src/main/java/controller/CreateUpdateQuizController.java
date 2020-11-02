package controller;

import database.mysql.CourseDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.List;

/**
 * @author Olaf van der Kaaij
 */

public class CreateUpdateQuizController extends AbstractController {

    private Quiz quiz;
    private Course course;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField quizIDTextfield;
    @FXML
    private MenuButton cursusTaskMenuButton;
    @FXML
    private TextField quizNaamTextField;
    @FXML
    private TextField aantalVragenTextField;
    @FXML
    private TextField succesDefinitieTextField;
    @FXML
    private Button submitButton;

    public void setup(Quiz quiz) {
        // Dropdownmenu maken met alle cursussen waarvan de ingelogde gebruiker coördinator is
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        // Alleen cursussen bij ingelogde coordinator
        List<Course> allCourses = courseDAO.getAllByCoordinatorID(Main.getUser().getGebruikerID());
        for (Course course : allCourses) {
            MenuItem item = new MenuItem(course.getCursusNaam() + " (" + course.getCursusID() + ')');
            item.setOnAction(event -> {
                this.course = course;
                cursusTaskMenuButton.setText(course.getCursusNaam() + " (" + course.getCursusID() + ')');
            });
            cursusTaskMenuButton.getItems().add(item);
        }
        // Scherm voor het aanmaken van een nieuwe quiz
        if (quiz == null) {
            titleLabel.setText("Nieuwe quiz");
            submitButton.setText("Maak");
        }
        // Scherm voor het wijzigen van een bestaande quiz
        else {
            this.quiz = quiz;
            this.course = quiz.getCourse();
            quizIDTextfield.setText(String.valueOf(quiz.getQuizID()));
            cursusTaskMenuButton.setText(quiz.getCourse().getCursusNaam());
            quizNaamTextField.setText(quiz.getQuizNaam());
            aantalVragenTextField.setText(String.valueOf(quiz.getAantalVragen()));
            succesDefinitieTextField.setText(String.valueOf(quiz.getSuccesDefinitie()));
            submitButton.setText("Wijzig");
        }
    }

    public void doCreateUpdateQuiz() {
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        // Nieuwe quiz opslaan in de database
        if (quiz == null) {
            quiz = new Quiz(course, quizNaamTextField.getText(), Integer.parseInt(aantalVragenTextField.getText()),Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.storeOne(quiz);
            showInformationAlert(String.format("Quiz %s aangemaakt \nHet quiznummer is %s", quiz.getQuizNaam(), quiz.getQuizID()));
            doMenu();
        }
        // Wijzigen van een bestaande quiz in de database
        else {
            quiz.setCourse(course);
            quiz.setQuizNaam(quizNaamTextField.getText());
            quiz.setAantalVragen(Integer.parseInt(aantalVragenTextField.getText()));
            quiz.setSuccesDefinitie(Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.updateOne(quiz);
            showInformationAlert("Quiz gewijzigd");
            doMenu();
        }
    }

    @FXML
    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }
}