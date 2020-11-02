package controller;

import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.ArrayList;
import java.util.List;

/** Author Richard Knol, Wendy Ellens
 */

public class FillOutQuizController {

    Quiz quiz; // todo Nieuw
    Question huidigeVraag; // todo naam gewijzigd
    List<Question> vragen; // todo Nieuw
    int huidigVraagnummer = 0; // todo Nieuw

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;

    public void setup(Quiz quiz) {

        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess()); // todo verplaatst
        huidigVraagnummer++; // todo Nieuw
        if (huidigVraagnummer == 1) { // todo Nieuw
            this.quiz = quiz; // todo Nieuw
            vragen = questionDAO.getAllByQuizId(quiz.getQuizID()); // todo verplaatst en declaratie weggelaten vanwege nieuw attribuut
        } // todo Nieuw

        // todo: titlelabel werkt niet
        // titleLabel.setText(String.valueOf(questionDAO.getOneById(question.getVraagID())));
        titleLabel.setText("Vraag " + huidigVraagnummer); // todo Nieuw

        huidigeVraag = vragen.get(huidigVraagnummer); // todo Nieuw

        // todo: hij pakt alleen de laatste vraag, niet de eerste
//        for (Question v : vragen) { // todo uitgecommentarieerd
//            questionArea.setText(String.valueOf(v)); // todo vervangen door onderstaande
        questionArea.setText(huidigeVraag.toString()); // todo nieuw
//        } // todo uitgecommentarieerd

        // todo: antwoord linken aan de buttons doRegister methods


    }

    public void doRegisterA() {}

    public void doRegisterB() {}

    public void doRegisterC() {}

    public void doRegisterD() {}

    // todo: zorgen dat ie de volgende vraag van dezelfde quizID pakt
    public void doNextQuestion() {
        // todo weer naar FillOutQuiz scherm gaan. dan wordt setup() aangeroepen met volgende vraag
    }

    public void doPreviousQuestion() {}

    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }
}
