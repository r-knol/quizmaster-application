package view;

import database.couchdb.CouchDBaccess;
import database.couchdb.QuizResultCouchDBDAO;
import database.mysql.DBAccess;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

    private static CouchDBaccess couchDBaccess;
    private static QuizResultCouchDBDAO quizResultCouchDBDAO;
    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBAccess db = null;
    private static User user;

    // stukje code om couchDB te testen
    // Constructor van main om couchdb access te regelen
//    public Main() {
//        super();
//        couchDBaccess = new CouchDBaccess();
//        quizResultCouchDBDAO = new QuizResultCouchDBDAO(couchDBaccess);
//    }

    public static void main(String[] args) {
        // stukje code om couchDB te testen
//        Main main = new Main();
//        main.setupCouchDBConnection();
//        quizResultCouchDBDAO.runTest();
        // code om Application klasse te launchen
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

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // setupconnection CouchDB
//    public void setupCouchDBConnection() {
//        try {
//            couchDBaccess.setupConnection();
//            System.out.println("Connection open");
//        } catch (Exception e) {
//            System.out.println("\nEr is iets fout gegaan");
//            e.printStackTrace();
//        }
//    }

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