package controller;

import database.mysql.CourseDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.List;

public class SelectQuizForStudentController extends AbstractController {

    private Course course;

    @FXML
    ListView<Quiz> quizList;

    public void setup() {

        setupCode();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doQuiz() {
        Main.getSceneManager().showFillOutQuiz(null);
    }

    public void setupCode() {
       QuizDAO quizDAO = new QuizDAO( Main.getDBaccess());

        List<Quiz> allQuizzes = quizDAO.getAllByCourseId(course.getCursusID());
        for (Quiz quiz : allQuizzes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();
    }
}
