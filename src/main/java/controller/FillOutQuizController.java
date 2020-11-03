package controller;

import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Question;
import model.QuestionAnswerPair;
import model.Quiz;
import model.QuizResult;
import view.Main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Richard Knol, Wendy Ellens
 */

public class FillOutQuizController {

    List<Question> alleVragen;
    int huidigVraagnummer = 0;
    QuizResult quizResult;
    ArrayList<QuestionAnswerPair> questionAnswerPairs = new ArrayList<QuestionAnswerPair>();
    Quiz quiz;

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;

    public void setup(Quiz quiz) {
        this.quiz = quiz;
        // Eén vraag laten zien, de eerste uit de lijst voor de desbetreffende quiz
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess()); // instantie van questiondao
        alleVragen = questionDAO.getAllByQuizId(quiz.getQuizID()); // lijst maken van alle vragen o.b.v. quizID
        // Nu wil ik de eerste vraag van dit lijstje tonen in questionArea (TextArea), met de mogelijke antwoorden
        questionArea.setText(String.valueOf(alleVragen.get(huidigVraagnummer++)));
        // Titel veranderen met juiste vraagnummer
        titleLabel.setText("Vraag " + (huidigVraagnummer + 1));

        quizResult = new QuizResult(Main.getUser(), quiz, LocalDateTime.now());
    }

    public void doRegisterA() {
        // Sla antwoord op en ga naar volgende vraag
        questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer -1),
                alleVragen.get(huidigVraagnummer).shuffleAntwoorden().get(0)));
        doNextQuestion();
    }

    public void doRegisterB() {
        // Sla antwoord op en ga naar volgende vraag
        questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer -1),
                alleVragen.get(huidigVraagnummer).shuffleAntwoorden().get(1)));
        doNextQuestion();
    }

    public void doRegisterC() {
        // Sla antwoord op en ga naar volgende vraag
        questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer -1),
                alleVragen.get(huidigVraagnummer).shuffleAntwoorden().get(2)));
        doNextQuestion();
    }

    public void doRegisterD() {
        // Sla antwoord op en ga naar volgende vraag
        questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer -1),
                alleVragen.get(huidigVraagnummer).shuffleAntwoorden().get(3)));
        doNextQuestion();
    }

    public void doNextQuestion() {
        // controle of er wel een volgende vraag is
        if (huidigVraagnummer >= alleVragen.size()) {
            quizResult.setVraagAntwoordParen(questionAnswerPairs);
            quizResult.setBehaald();
            Main.getSceneManager().showStudentFeedback(quiz);
        } else { // door met de volgende vraag
            questionArea.setText(String.valueOf(alleVragen.get(huidigVraagnummer++)));
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
