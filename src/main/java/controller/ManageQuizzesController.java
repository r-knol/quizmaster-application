package controller;

import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Quiz;
import view.Main;

import java.util.List;

/**
 * @author Olaf van der Kaaij
 */

public class ManageQuizzesController extends AbstractController {

    @FXML
    ListView<Quiz> quizList;

    public void setup() {
        setupCode();
    }

    public void doMenu(){
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuiz(){
        Main.getSceneManager().showCreateUpdateQuizScene(null);
    }

    public void doUpdateQuiz(){
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuizScene(quiz);
    }

    public void doDeleteQuiz(){
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        showInformationAlert("Quiz verwijderd");
        quizDAO.deleteOne(quiz);
        // Lijst met quizzes verversen
        quizList.getItems().clear();
        setupCode();
    }

    public void setupCode() {
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        List<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz quiz : allQuizzes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();
    }
}