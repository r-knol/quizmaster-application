package controller;

import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
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
        // Titel veranderen met juiste vraagnummer
        titleLabel.setText("Vraag " + (huidigVraagnummer + 1));
    }

    public void doRegisterA() {
        // naar volgende vraag
    }

    public void doRegisterB() {}

    public void doRegisterC() {}

    public void doRegisterD() {}

    public void doNextQuestion() {
        // controle of er wel een volgende vraag is
        if (huidigVraagnummer > alleVragen.size()) {
            // todo: naar feedbackStudent scherm
        } else { // door met de volgende vraag
            questionArea.setText(String.valueOf(alleVragen.get(++huidigVraagnummer)));
            titleLabel.setText("Vraag " + (huidigVraagnummer + 1));
        }
    }

    public void doPreviousQuestion() {
        questionArea.setText(String.valueOf(alleVragen.get(--huidigVraagnummer)));
        titleLabel.setText("Vraag " + (huidigVraagnummer + 1));
    }

    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }

}
