package controller;

/**
 * @author Olaf van der Kaaij
 */

import database.mysql.CourseDAO;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;
import java.util.List;

public class CoordinatorDashboardController extends AbstractController{

    private Quiz quiz;

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

        // Haalt eerst alle cursussen op.
        CourseDAO courseDAO = new CourseDAO(Main.getDBaccess());
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess());

        List<Course> allCourses = courseDAO.getAllByCoordinatorID(Main.getUser().getGebruikerID());
        for (Course course : allCourses) courseList.getItems().add( course );
        // Haalt op basis van de cursus alle bijbehorende quizzes op, bij nieuwe keuze wordt de lijst leegemaakt.
        courseList.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldCourse, newCourse) -> {
                    if (oldCourse != null) {
                        quizList.getItems().clear();
                        questionList.getItems().clear();
                    }
                    Course course = courseList.getSelectionModel().getSelectedItem();
                    List<Quiz> allQuizzesById = quizDAO.getAllByCourseId(course.getCursusID());
                    for (Quiz quiz : allQuizzesById) quizList.getItems().add( quiz );
                });
        // Haalt op basis va nde quiz alle bijbehorende vragen op, bij nieuwe keuze wordt de lijst leeggemaakt.
        quizList.getSelectionModel().selectedItemProperty().addListener(
                (observableValue2,  oldQuiz, newQuiz) -> {
                    if (oldQuiz != null)
                        questionList.getItems().clear();
                    quiz = quizList.getSelectionModel().getSelectedItem();
                    if (quiz != null) {
                        List <Question> allQuestionsById = questionDAO.getAllByQuizId(quiz.getQuizID());
                        for (Question question : allQuestionsById) questionList.getItems().add( question );
                    } else {
                        questionList.getSelectionModel().clearSelection();
                    }
                });
    }

    // TODO : Afhankelijk van scherm van herkomst terug naar datzelfde scherm?
    public void doNewQuiz() {
        Main.getSceneManager().showCreateUpdateQuizScene(null);
    }

    public void doEditQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuizScene(quiz);
    }

    public void doDeleteQuiz() {
        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        showInformationAlert("Quiz verwijderd");
        quizDAO.deleteOne(quiz);
    }

    public void doNewQuestion() {
        Main.getSceneManager().showCreateUpdateQuestionScene(null);
    }

    public void doEditQuestion() {
        Question question = questionList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuestionScene(question);
    }

    public void doDeleteQuestion () {
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess());
        Question question = questionList.getSelectionModel().getSelectedItem();
        showInformationAlert("Vraag verwijderd");
        questionDAO.deleteOne(question);
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }
}
