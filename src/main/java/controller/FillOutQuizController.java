package controller;

import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.List;

/** Author Richard Knol, Wendy Ellens
 */

public class FillOutQuizController {

    List<Question> alleVragen;
    int huidigVraagnummer = 0;

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;

    public void setup(Quiz quiz) {

        // Eén vraag laten zien, de eerste uit de lijst voor de desbetreffende quiz
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess()); // instantie van questiondao
        alleVragen = questionDAO.getAllByQuizId(quiz.getQuizID()); // lijst maken van alle vragen o.b.v. quizID
        // Nu wil ik de eerste vraag van dit lijstje tonen in questionArea (TextArea), met de mogelijke antwoorden
        questionArea.setText(String.valueOf(alleVragen.get(huidigVraagnummer)));
        // Huidige vraag laten zien
        titleLabel.setText("Vraag " + ++huidigVraagnummer);






        // Vragen uit de database halen die is gelinkt aan de quiz
        //QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess()); // todo verplaatst
//        huidigVraagnummer++; // todo Nieuw
//        if (huidigVraagnummer == 1) { // todo Nieuw
//            this.quiz = quiz; // todo Nieuw
//            vragen = questionDAO.getAllByQuizId(quiz.getQuizID()); // todo verplaatst en declaratie weggelaten vanwege nieuw attribuut
//        } // todo Nieuw
//
//        // todo: titlelabel werkt niet
//        // titleLabel.setText(String.valueOf(question.getVraagID());
//        titleLabel.setText("Vraag " + huidigVraagnummer); // todo Nieuw
//
//        huidigeVraag = vragen.get(huidigVraagnummer); // todo Nieuw
//
//        // todo: hij pakt alleen de laatste vraag, niet de eerste
////        for (Question v : vragen) { // todo uitgecommentarieerd
////            questionArea.setText(String.valueOf(v)); // todo vervangen door onderstaande
//        questionArea.setText(huidigeVraag.toString()); // todo nieuw
//        }


    }

    public void doRegisterA() {}

    public void doRegisterB() {}

    public void doRegisterC() {}

    public void doRegisterD() {}

    public void doNextQuestion() {
        // huidige vraagnummer met 1 ophogen
        huidigVraagnummer++;
        // controle of er wel een volgende vraag is
        if (huidigVraagnummer > alleVragen.size()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Er is geen volgende vraag");
            alert.show();
        } else { // door met de volgende vraag
            questionArea.setText(String.valueOf(alleVragen.get(huidigVraagnummer)));
            titleLabel.setText("Vraag " + huidigVraagnummer);
        }

    }

    public void doPreviousQuestion() {


    }

    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }
}
