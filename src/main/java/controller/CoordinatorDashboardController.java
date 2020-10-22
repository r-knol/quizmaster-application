package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Course;
import model.Question;
import model.Quiz;
import model.User;
import view.Main;

public class CoordinatorDashboardController {

    private Label welcomeLabel;

    @FXML
    private ListView<Course> courseList;
    @FXML
    private ListView<Quiz> quizList;
    @FXML
    private ListView<Question> questionList;

    public void setup() {

        // welcomeLabel.setText("Welkom " + User.getGebruikersNaam() + ", selecteer eerst een cursus.");

        courseList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Course>() {
                    @Override
                    public void changed(ObservableValue<? extends Course> observableValue, Course oldCourse, Course newCourse) {
                        System.out.println("Geselecteerde cursus: " + observableValue + ", " + oldCourse + ", " + newCourse);
                    }
                });

        quizList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Quiz>() {
                    @Override
                    public void changed(ObservableValue<? extends Quiz> observableValue, Quiz oldQuiz, Quiz newQuiz) {
                        System.out.println("Geselecteerde quiz: " + observableValue + ", " + oldQuiz + ", " + newQuiz);
                    }
                });
    }

    public void doNewQuiz() { }

    public void doEditQuiz() { }

    public void doNewQuestion() { }

    public void doEditQuestion() { }

    public void doMenu() { }
}