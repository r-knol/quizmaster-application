package controller;

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.List;

/**
 * @author Olaf van der Kaaij
 */

public class CreateUpdateQuestionController {

    private Question question;
    private Quiz quiz;
    private QuestionDAO questionDAO;
    private DBAccess dbAccess;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField vraagIDTextfield;
    @FXML
    private MenuButton quizTaskMenuButton;
    @FXML
    private TextField quizVraagTextField;
    @FXML
    private TextField juistAntwoordTextField;
    @FXML
    private TextField foutAntwoord1TextField;
    @FXML
    private TextField foutAntwoord2TextField;
    @FXML
    private TextField foutAntwoord3TextField;
    @FXML
    private Button submitButton;

    private CreateUpdateQuestionController() {
        this.dbAccess = Main.getDBaccess();
        this.questionDAO = new QuestionDAO(dbAccess);
    }

    public void setup(Question question) {
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        List<Quiz> allQuizzes = quizDAO.getAll();
        MenuItem item = new MenuItem("Kies een quiz");
        for (Quiz quiz : allQuizzes) {
            item = new MenuItem(quiz.getQuizNaam());
            item.setOnAction(event -> {
                this.quiz = quiz;
                quizTaskMenuButton.setText(quiz.getQuizNaam());
            });
            quizTaskMenuButton.getItems().add(item);
        }
        quizTaskMenuButton.setText("Kies een quiz");
        if (question == null) {
            titleLabel.setText("Nieuwe vraag");
            vraagIDTextfield.setText("");
            quizTaskMenuButton.setText(quiz.getQuizNaam());
            quizVraagTextField.setText("");
            juistAntwoordTextField.setText("");
            foutAntwoord1TextField.setText("");
            foutAntwoord2TextField.setText("");
            foutAntwoord3TextField.setText("");
            submitButton.setText("Maak");
        } else {
            this.question = question;
            vraagIDTextfield.setText(String.valueOf(question.getVraagID()));
            quizTaskMenuButton.setText(String.valueOf(question.getQuiz().getQuizNaam()));
            quizVraagTextField.setText(String.valueOf(question.getQuizVraag()));
            juistAntwoordTextField.setText(String.valueOf(question.getJuistAntwoord()));
            foutAntwoord1TextField.setText(String.valueOf(question.getFoutAntwoord1()));
            foutAntwoord2TextField.setText(String.valueOf(question.getFoutAntwoord2()));
            foutAntwoord3TextField.setText(String.valueOf(question.getFoutAntwoord3()));
            submitButton.setText("wijzig");
        }
    }

    public void doCreateUpdateQuestion() {
        if (question == null) {
            question = new Question(Integer.parseInt(vraagIDTextfield.getText()), quiz, quizVraagTextField.getText(), juistAntwoordTextField.getText(),
                    foutAntwoord1TextField.getText(), foutAntwoord2TextField.getText(), foutAntwoord3TextField.getText());
            questionDAO.storeOne(question);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Quiz aangemaakt");
            aangemaakt.show();
        } else {
            question.setVraagID(Integer.parseInt(vraagIDTextfield.getText()));
            question.setQuizVraag(quizVraagTextField.getText());
            question.setJuistAntwoord(juistAntwoordTextField.getText());
            question.setFoutAntwoord1(foutAntwoord1TextField.getText());
            question.setFoutAntwoord2(foutAntwoord2TextField.getText());
            question.setFoutAntwoord3(foutAntwoord3TextField.getText());
            questionDAO.updateOne(question);
            Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
            gewijzigd.setContentText("Quiz gewijzigd");
            gewijzigd.show();
        }
    }

    @FXML
    public void doMenu() {
        Main.getSceneManager().showManageQuestionsScene();
    }
}