package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

/**
 * @author Olaf van der Kaaij
 */

public class ManageQuizzesController {
    private QuizDAO quizDAO;
    private DBAccess dbAccess;

    @FXML
    ListView<Quiz> quizList;

    @FXML
    TextField warningText;

    public void setup() {
        this.quizDAO = new QuizDAO(dbAccess);
        ArrayList<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz quiz : allQuizzes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();
    }

    public void doMenu(ActionEvent actionEvent){
        dbAccess.closeConnection();
        System.out.println("Connection closed");
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuiz(ActionEvent event){

    }

    public void doUpdateQuiz(ActionEvent event){
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        if (quiz == null) {
            warningText.setVisible(true);
            warningText.setText("Je moet eerst een quiz kiezen");
        } else {
            Main.getSceneManager().showCreateUpdateQuizScene(quiz);
        }
    }

    public void doDeleteQuiz(){}
}