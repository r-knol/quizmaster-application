package controller;

/** Author Richard Knol, Olaf van der Kaaij
 */

import database.couchdb.CouchDBaccess;
import database.couchdb.QuizResultCouchDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.Main;
import java.util.List;

public class StudentFeedbackController {

    private Quiz quiz;
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

        // Instantie quizResult vullen met wat er opgehaald wordt uit get van de DAO
        List<QuizResult> quizResult = quizResultCouchDBDAO.getQuizResult(Main.getUser(), quiz);

        // Labeltje met juiste quiznaam
        feedbackLabel.setText(quiz.getQuizNaam());

        // Tekstveld waar resultaat in komt te staan
        feedbackList.getItems().addAll(quizResult);
        feedbackList.getSelectionModel();
    }

    public void doMenu() {
        Main.getSceneManager().showFillOutQuiz(quiz);
    }
}

