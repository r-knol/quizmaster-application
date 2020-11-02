package controller;

import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

/** Author Richard Knol, Wendy Ellens
 */

public class FillOutQuizController {

    Question question;

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;

    public void setup(Quiz quiz) {

        // todo: titlelabel werkt niet
        // titleLabel.setText(String.valueOf(questionDAO.getOneById(question.getVraagID())));

        // todo: hij pakt alleen de laatste vraag, niet de eerste
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess());
        ArrayList<Question> vragen = questionDAO.getAllByQuizId(quiz.getQuizID());
        for (Question v : vragen) {
            questionArea.setText(String.valueOf(v));
        }

        // todo: antwoord linken aan de buttons doRegister methods


    }

    public void doRegisterA() {}

    public void doRegisterB() {}

    public void doRegisterC() {}

    public void doRegisterD() {}

    // todo: zorgen dat ie de volgende vraag van dezelfde quizID pakt
    public void doNextQuestion() {

    }

    public void doPreviousQuestion() {}

    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }
}
