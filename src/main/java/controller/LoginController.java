package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;
import view.Main;

/**
 * @author Richard Knol, Wendy Ellens
 */

public class LoginController {
    private UserDAO userDAO;
    private DBAccess dBaccess;

    public LoginController() {
        this.dBaccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dBaccess);
    }

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;

    public void doLogin() {
        Main.setUser(userDAO.getOneByNameAndPassword(nameTextField.getText(),passwordField.getText()));
        if (Main.getUser() == null) {
            Alert foutmelding = new Alert(Alert.AlertType.WARNING);
            foutmelding.setContentText("Deze combinatie van gebruikersnaam en wachtwoord is onbekend.");
            foutmelding.show();
        }
        else {
            Main.getSceneManager().showWelcomeScene();
        }
    }

    public void doQuit() {
        dBaccess.closeConnection();
        System.exit(0);
    }
}
