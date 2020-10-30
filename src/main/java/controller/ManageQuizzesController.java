package controller;

import database.mysql.DBAccess;
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

public class ManageQuizzesController {
    private QuizDAO quizDAO;
    private DBAccess dbAccess;

    @FXML
    ListView<Quiz> quizList;

    public ManageQuizzesController() {
        super();
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
    }

    public void setup() {
        this.quizDAO = new QuizDAO(dbAccess);
        List<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz quiz : allQuizzes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Quiz verwijderd");
        alert.show();
        quizDAO.deleteOne(quiz);
        quizList.getItems().clear();
        List<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz q : allQuizzes) {
            quizList.getItems().add(q);
        }
        quizList.getSelectionModel().selectFirst();
    }
}