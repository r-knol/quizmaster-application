package view;

import database.mysql.DBAccess;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBAccess db = null;         // alvast toegevoegd door Richard

    public static void main(String[] args) {
        launch(args);
    }

    // Toegevoegd door Richard om DB connectie te leggen.
    public static DBAccess getDBaccess() {
        if (db == null) {
            db = new DBAccess("quizmaster","userQuizmaster", "userQuizmasterPW");
        }
        return db;
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

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


}