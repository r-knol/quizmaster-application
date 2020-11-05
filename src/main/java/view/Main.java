package view;

import database.mysql.DBAccess;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;


/**
 * @Author docenten MIW
 */

public class Main extends Application {

    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBAccess db = null;
    private static User user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Make IT Work - Project 1");
        getSceneManager().setWindowTool();
        primaryStage.show();
    }

    public static SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager(primaryStage);
        }
        return sceneManager;
    }

    // Toegevoegd door Wendy om de gegevens van de gebruiker die inlogt overal beschikbaar te hebben
    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        Main.user = user;
    }

    // Toegevoegd door Richard om DB connectie te leggen
    public static DBAccess getDBaccess() {
        if (db == null) {
            db = new DBAccess("quizmaster","userQuizmaster", "userQuizmasterPW");
        }
        return db;
    }
}