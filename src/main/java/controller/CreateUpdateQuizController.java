package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Quiz;
import model.User;
import view.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateUpdateQuizController {

    private Quiz quiz;
    private Course course;

    private QuizDAO quizDAO;
    private DBAccess dbAccess;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField quizIDTextfield;

    @FXML
    private TextField cursusIDTextField;

   /* @FXML
    private MenuButton cursusTaskMenuButton;*/

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
            cursusIDTextField.setText("");

            //for (Course course : ) {

            /*MenuItem item1 = new MenuItem("In- en uitschrijven cursus");
            item1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
            cursusTaskMenuButton.getItems().add(item1);*/
            //}

            quizNaamTextField.setText("");
            succesDefinitieTextField.setText("");
            submitButton.setText("Nieuw");
        } else {
            this.quiz = quiz;
            quizIDTextfield.setText(String.valueOf(quiz.getQuizID()));
            cursusIDTextField.setText(String.valueOf(quiz.getCursusID()));
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
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        QuizDAO quizeDAO = new QuizDAO(Main.getDBaccess());
        int givenCursusID = Integer.parseInt(cursusIDTextField.getText());
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
            quiz = new Quiz(givenCursusID, quizNaamTextField.getText(), Integer.parseInt(succesDefinitieTextField.getText()));
            quizDAO.storeOne(quiz);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Quiz aangemaakt");
            aangemaakt.show();
        }

        }

        /*public void doCreateUpdateCourse() {
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        int givenCoordinatorID = Integer.parseInt(coordinatorID.getText());
        // Controleren of het opgegeven ID bij een coordinator hoort, anders foutmelding geven
        if (userDAO.getOneById(givenCoordinatorID).getRol().equals("coordinator")) {
            if (course == null) { // Nieuwe cursus aanmaken
                course = new Course(cursusnaam.getText(), givenCoordinatorID);
                course = new Course(cursusnaam.getText(), Integer.parseInt(coordinatorID.getText()));
                courseDAO.storeOne(course);
                Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
                aangemaakt.setContentText("Cursus aangemaakt");
                aangemaakt.show();
                setupCode(); // Gegenereerde ID tonen
            } else { // Wijzigen van een bestaande cursus
                course.setCursusNaam(cursusnaam.getText());
                course.setCoordinatorID(Integer.parseInt(coordinatorID.getText()));
                courseDAO.updateOne(course);
                Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
                gewijzigd.setContentText("Cursus gewijzigd");
                gewijzigd.show();
            }
        }
        else{
            Alert foutmelding = new Alert(Alert.AlertType.WARNING);
            foutmelding.setContentText("Gebruiker met dit ID is geen coordinator. " +
                    "Geef het ID van de vakcoordinator.");
            foutmelding.show();
        }
    }*/



    @FXML
    public void doMenu() {
        Main.getSceneManager().showManageQuizScene();
    }
}