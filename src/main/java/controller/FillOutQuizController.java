package controller;

import database.couchdb.CouchDBaccess;
import database.couchdb.QuizResultCouchDBDAO;
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
    List<String> antwoordenHuidigeVraag;

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
        antwoordenHuidigeVraag = alleVragen.get(huidigVraagnummer).shuffleAntwoorden();
        questionArea.setText(alleVragen.get(huidigVraagnummer).zetAntwoordenInString(antwoordenHuidigeVraag));
        // Titel veranderen met juiste vraagnummer
        titleLabel.setText("Vraag " + (huidigVraagnummer + 1));

        quizResult = new QuizResult(Main.getUser(), quiz, LocalDateTime.now());
    }

    public void doRegisterA() {
        // Sla antwoord op en ga naar volgende vraag
        if (questionAnswerPairs.size() <= huidigVraagnummer) { // nieuw antwoord toevoegen
            questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                antwoordenHuidigeVraag.get(0)));
        }
        else { // eerder gegeven antwoord overschrijven
            questionAnswerPairs.set(huidigVraagnummer, new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(0)));
        }
        doNextQuestion();
    }

    public void doRegisterB() {
        // Sla antwoord op en ga naar volgende vraag
        if (questionAnswerPairs.size() <= huidigVraagnummer) { // nieuw antwoord toevoegen
            questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(1)));
        }
        else { // eerder gegeven antwoord overschrijven
            questionAnswerPairs.set(huidigVraagnummer, new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(1)));
        }
        doNextQuestion();
    }

    public void doRegisterC() {
        // Sla antwoord op en ga naar volgende vraag
        if (questionAnswerPairs.size() <= huidigVraagnummer) { // nieuw antwoord toevoegen
            questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(2)));
        }
        else { // eerder gegeven antwoord overschrijven
            questionAnswerPairs.set(huidigVraagnummer, new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(2)));
        }
        doNextQuestion();
    }

    public void doRegisterD() {
        // Sla antwoord op en ga naar volgende vraag
        if (questionAnswerPairs.size() <= huidigVraagnummer) { // nieuw antwoord toevoegen
            questionAnswerPairs.add(new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(3)));
        }
        else { // eerder gegeven antwoord overschrijven
            questionAnswerPairs.set(huidigVraagnummer, new QuestionAnswerPair(alleVragen.get(huidigVraagnummer),
                    antwoordenHuidigeVraag.get(3)));
        }
       doNextQuestion();
    }

    public void doNextQuestion() {
        // controle of er wel een volgende vraag is
        huidigVraagnummer++;
        if (huidigVraagnummer >= alleVragen.size()) {
            quizResult.setVraagAntwoordParen(questionAnswerPairs);
            quizResult.setBehaald();

            // quizResult in DB met CouchDBQuizResultDAO
            CouchDBaccess db = new CouchDBaccess();
            db.openConnection();
            System.out.println("dit is het quizresultaat, wegschrijven: " + quizResult);
            new QuizResultCouchDBDAO(db).saveQuizResult(quizResult);

            Main.getSceneManager().showStudentFeedback(quiz);
        } else { // door met de volgende vraag
            antwoordenHuidigeVraag = alleVragen.get(huidigVraagnummer).shuffleAntwoorden();
            questionArea.setText(alleVragen.get(huidigVraagnummer).zetAntwoordenInString(antwoordenHuidigeVraag));
            titleLabel.setText("Vraag " + (huidigVraagnummer + 1));
        }
    }

    public void doPreviousQuestion() {
        // todo niet verder terug dan huidigVraagnummer = 0
        huidigVraagnummer--;
        antwoordenHuidigeVraag = alleVragen.get(huidigVraagnummer).shuffleAntwoorden();
        questionArea.setText(alleVragen.get(huidigVraagnummer).zetAntwoordenInString(antwoordenHuidigeVraag));
        titleLabel.setText("Vraag " + (huidigVraagnummer + 1));
    }

    public void doMenu() {
        Main.getSceneManager().showSelectQuizForStudent();
    }
}
