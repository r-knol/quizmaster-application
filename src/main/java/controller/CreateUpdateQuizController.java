package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Quiz;
import view.Main;

public class CreateUpdateQuizController {

    private QuizDAO quizDAO;
    private DBAccess dbAccess;
    private Quiz quiz;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField quizIDTextfield;

    @FXML
    private TextField cursusIDTextField;

    @FXML
    private TextField succesDefinitieTextField;


    public CreateUpdateQuizController () {
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
    }

    @FXML
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
                quizDAO.updateQuiz(quiz);
                Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
                gewijzigd.setContentText("Quiz gewijzigd");
                gewijzigd.show();
            }
        }
    }

    public void setup(Quiz quiz) {
        titleLabel.setText("Wijzig quiz");
        quizIDTextfield.setText(String.valueOf(quiz.getQuizID()));
        cursusIDTextField.setText(String.valueOf(quiz.getCursusID()));
        succesDefinitieTextField.setText(String.valueOf(quiz.getSuccesDefinitie()));
    }

    public void doMenu(ActionEvent actionEvent) {
        dbAccess.closeConnection();
        System.out.println("Connection closed");
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateQuiz() {
        StringBuilder warningText = new StringBuilder();
        boolean correcteInvoer = true;
        String id1 = quizIDTextfield.getText();
        String id2 = cursusIDTextField.getText();
        String sDefinitie = succesDefinitieTextField.getText();

        if (id1.isEmpty()) {
            warningText.append("Je moet een id voor de quiz invoeren\n");
            correcteInvoer = false;
        }
        if (!correcteInvoer) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            foutmelding.setContentText(warningText.toString());
            foutmelding.show();
            quiz = null;
        } else {
            int quizID = Integer.parseInt(id1);
            int cursusID = Integer.parseInt(id2);
            int succesdefinitie = Integer.parseInt(sDefinitie);
            quiz = new Quiz (quizID, cursusID, succesdefinitie);
        }
    }
}