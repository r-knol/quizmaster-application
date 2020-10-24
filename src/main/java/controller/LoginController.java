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
        User user = userDAO.getOneByName(nameTextField.getText());
        if (user == null) {
            return;
        }
        if (passwordField.getText().equals(user.getWachtwoord())) {
            Main.getSceneManager().showWelcomeScene();
        } else {
            Alert foutmelding = new Alert(Alert.AlertType.WARNING);
            foutmelding.setContentText("Het wachtwoord is onjuist");
            foutmelding.show();
        }
    }

    public void doQuit() {
        dBaccess.closeConnection();
        System.out.println("Connection closed");
        System.exit(0);
    }
}
