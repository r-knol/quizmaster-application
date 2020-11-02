package controller;

/**
 * @author Olaf van der Kaaij
 */

import database.mysql.CourseDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.List;

public class SelectQuizForStudentController extends AbstractController {

    @FXML
    ListView<Quiz> quizList;

    // Op basis van de ingeschreven cursus een quiz selecteren.
    public void setup() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAllByStudentID(Main.getUser().getGebruikerID());
        for(Course c : allCourses) {
            List<Quiz> allQuizzes = quizDAO.getAllByCourseId(c.getCursusID());
            for (Quiz quiz : allQuizzes) {
                quizList.getItems().add(quiz);
            }
        }
        quizList.getSelectionModel().selectFirst();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doQuiz() {
        Main.getSceneManager().showFillOutQuiz(null);
    }
}
