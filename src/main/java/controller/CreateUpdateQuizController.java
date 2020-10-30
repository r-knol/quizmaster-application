package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
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

public class CreateUpdateQuizController {

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
    private TextField succesDefinitieTextField;
    @FXML
    private Button submitButton;

    public void setup(Quiz quiz) {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAll();
        MenuItem item = new MenuItem("Kies een cursus");
        for (Course course : allCourses) {
            item = new MenuItem(course.getCursusNaam());
            item.setOnAction(event -> {
                this.course = course;
                cursusTaskMenuButton.setText(course.getCursusNaam());
            });
            cursusTaskMenuButton.getItems().add(item);
        }
        cursusTaskMenuButton.setText("Kies een cursus");
        if (quiz == null) {
            titleLabel.setText("Nieuwe quiz");
            quizIDTextfield.setText("");
            quizNaamTextField.setText("");
            succesDefinitieTextField.setText("");
            submitButton.setText("Nieuw");
        } else {
            this.quiz = quiz;
            quizIDTextfield.setText(String.valueOf(quiz.getQuizID()));
            cursusTaskMenuButton.setText(String.valueOf(quiz.getCourse().getCursusNaam()));
            quizNaamTextField.setText(String.valueOf(quiz.getQuizNaam()));
            succesDefinitieTextField.setText(String.valueOf(quiz.getSuccesDefinitie()));
            submitButton.setText("Wijzig");
        }
    }

    public void doCreateUpdateQuiz() {
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        // Aanmaken van een quiz
        if (quiz == null) {
            quiz = new Quiz(course, quizNaamTextField.getText(), Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.storeOne(quiz);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Quiz aangemaakt");
            aangemaakt.show();
        // Quiz wijzigen
        } else {
            quiz.setQuizNaam(quizNaamTextField.getText());
            quiz.setSuccesDefinitie(Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.updateOne(quiz);
            Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
            gewijzigd.setContentText("Quiz gewijzigd");
            gewijzigd.show();
        }

    }

    @FXML
    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }
}