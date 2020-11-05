package controller;

import database.mysql.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Question;
import view.Main;
import java.util.List;

/**
 * @author Olaf van der Kaaij
 */

public class ManageQuestionsController extends AbstractController {

    @FXML
    ListView<Question> questionList;

    public void setup() {
        setupCode();
    }

    public void doMenu(){
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuestion(){
        Main.getSceneManager().showCreateUpdateQuestionScene(null);
    }

    public void doUpdateQuestion(){
        Question question = questionList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuestionScene(question);
    }

    public void doDeleteQuestion(){
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess());
        Question question = questionList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Vraag verwijderd");
        alert.show();
        questionDAO.deleteOne(question);
        questionList.getItems().clear();
        List<Question> allQuestions = questionDAO.getAll();
        for (Question q : allQuestions) {
            questionList.getItems().add(q);
        }
        questionList.getSelectionModel().selectFirst();
    }

    public void setupCode() {
        QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccess());
        List<Question> allQuistions = questionDAO.getAll();
        for (Question question : allQuistions) {
            questionList.getItems().add(question);
        }
        questionList.getSelectionModel().selectFirst();
    }
}
