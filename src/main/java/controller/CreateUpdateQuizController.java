package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Quiz;
import model.User;
import view.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateUpdateQuizController {

    private QuizDAO quizDAO;
    private DBAccess dbAccess;
    private Quiz quiz;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField quizIDTextfield;

    @FXML
    private MenuButton cursusTaskMenuButton;

    @FXML
    private TextField quizNaamTextField;

    @FXML
    private TextField succesDefinitieTextField;

    @FXML
    private Button submitButton;


    public CreateUpdateQuizController () {
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
    }

    //
    public void setup(Quiz quiz) {
        if (quiz == null) {
            this.quiz = quiz;
            titleLabel.setText("Nieuwe quiz");
            quizIDTextfield.setText("");


            //for (Course course : ) {

            MenuItem item1 = new MenuItem("In- en uitschrijven cursus");
            item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
            cursusTaskMenuButton.getItems().add(item1);
            //}

            quizNaamTextField.setText("");
            succesDefinitieTextField.setText("");
            submitButton.setText("Nieuw");
        } else {
            this.quiz = quiz;
            quizIDTextfield.setText(String.valueOf(quiz.getQuizID()));
            cursusTaskMenuButton.setText(String.valueOf(quiz.getCourse().getCursusNaam()));
            quizNaamTextField.setText(String.valueOf(quiz.getQuizNaam()));
            succesDefinitieTextField.setText(String.valueOf(quiz.getSuccesDefinitie()));
            submitButton.setText("Wijzig");
        }
    }

   /* @FXML
    public void doStore(ActionEvent actionEvent) {
        doCreateUpdateQuiz();
        if (quiz != null) {
            if (quizIDTextfield.getText().equals("quizID")) {
                quizDAO.storeOne(quiz);
                quizIDTextfield.setText(String.valueOf(quiz.getQuizID()));
                Alert opgeslagen = new Alert((Alert.AlertType.INFORMATION));
                opgeslagen.setContentText("Quiz opgeslagen");
                opgeslagen.show();
            } else {
                int quizID = Integer.parseInt(quizIDTextfield.getText());
                quiz.setQuizID(quizID);
                quizDAO.updateOne(quiz);
                Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
                gewijzigd.setContentText("Quiz gewijzigd");
                gewijzigd.show();
            }
        }
    }*/



    public void doCreateUpdateQuiz () {
        // wijzigen van een bestaande quiz met updateOne()
        if (quiz != null) {
            //quiz.setQuizID(Integer.parseInt(quizIDTextfield.getText()));
            //quiz.setCursusID(Integer.parseInt(cursusIDTextField.getText()));
            quiz.setQuizNaam(quizNaamTextField.getText());
            quiz.setSuccesDefinitie(Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.updateOne(quiz);
            Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
            gewijzigd.setContentText("Quiz gewijzigd");
            gewijzigd.show();
        // quiz aanmaken
        } else {
            quiz = new Quiz(quizNaamTextField.getText(), Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.storeOne(quiz);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Quiz aangemaakt");
            aangemaakt.show();
        }

        }



    @FXML
    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }
}