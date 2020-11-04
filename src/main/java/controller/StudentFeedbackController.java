package controller;

import database.couchdb.CouchDBaccess;
import database.couchdb.QuizResultCouchDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import model.User;
import view.Main;

import java.util.List;

public class StudentFeedbackController {

    private Quiz quiz;
    private QuizResult quizResult;
    private CouchDBaccess db;

    @FXML
    private Label feedbackLabel;
    @FXML
    private ListView<QuizResult> feedbackList;

    public void setup(Quiz quiz) {
        this.quiz = quiz;
        this.db = new CouchDBaccess();
        db.openConnection();

        QuizResultCouchDBDAO quizResultCouchDBDAO = new QuizResultCouchDBDAO(db);

        quizResult = quizResultCouchDBDAO.getQuizResult(Main.getUser(), quiz);
        //quizResultCouchDBDAO.getQuizResults(quizResult);

        feedbackLabel.setText(quiz.getQuizNaam());

        feedbackList.getItems().add(quizResult);
        feedbackList.getSelectionModel();


    }

    public void doMenu() {
        Main.getSceneManager().showFillOutQuiz(quiz);
    }
}

