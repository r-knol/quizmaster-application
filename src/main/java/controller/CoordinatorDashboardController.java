package controller;

import database.mysql.CourseDAO;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import model.Course;
import model.Question;
import model.Quiz;
import model.User;
import view.Main;

import java.util.Collection;
import java.util.List;

public class CoordinatorDashboardController extends AbstractController{

    @FXML
    private Label welcomeLabel;
    @FXML
    private ListView<Course> courseList;
    @FXML
    private ListView<Quiz> quizList;
    @FXML
    private ListView<Question> questionList;

    public void setup() {

        welcomeLabel.setText("Welkom " + Main.getUser().getVoornaam() + ", selecteer eerst een cursus.");

        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        List<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        }
        courseList.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldCourse, newCourse) -> {
                    quizList.getItems().clear();
                    Course course = courseList.getSelectionModel().getSelectedItem();
                    QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
                    List<Quiz> allQuizzesById = quizDAO.getAllByCourseId(course.getCursusID());
                    for (Quiz quiz : allQuizzesById) {
                        System.out.println(quiz);
                        quizList.getItems().add(quiz);
                    }
                    quizList.getSelectionModel().selectedItemProperty().addListener(
                            (observableValue2,  oldQuiz, newQuiz) -> {
                                    questionList.getItems().clear();
                                    Quiz quiz = quizList.getSelectionModel().getSelectedItem();
                                    QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess());
                                    List<Question> allQuestionsById = questionDAO.getAllByQuizId(quiz.getQuizID());
                                    for (Question question : allQuestionsById) {
                                        System.out.println(question);
                                        questionList.getItems().add(question);
                                    }
                            });

                });
    }

    public void doNewQuiz() {
        Main.getSceneManager().showCreateUpdateQuizScene(null);
    }

    public void doEditQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuizScene(quiz);
    }

    public void doNewQuestion() {
        Main.getSceneManager().showCreateUpdateQuestionScene(null);
    }

    public void doEditQuestion() {
        Question question = questionList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuestionScene(question);
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }
}
