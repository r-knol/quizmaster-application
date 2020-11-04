package controller;

import database.couchdb.CouchDBaccess;
import database.couchdb.QuizResultCouchDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.Main;

public class StudentFeedbackController {

    private Quiz quiz;
    private QuizResult quizResult;
    private CouchDBaccess db;

    @FXML
    private Label feedbackLabel;
    @FXML
    private ListView<QuizResult> feedbackList;

    public void setup(Quiz quiz) {
        this.quiz =quiz;
        this.quizResult = quizResult;
        QuizResultCouchDBDAO quizResultCouchDBDAO = new QuizResultCouchDBDAO(db);


    }

    public void doMenu() {
        // Main.getSceneManager().showFillOutQuiz();
    }
}

