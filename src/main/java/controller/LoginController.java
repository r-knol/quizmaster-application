package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
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
        User user = userDAO.getOneByName(nameTextField.getText()); // TODO er gaat helaas iets mis in de methode getOneByName, maar wat?
        if (passwordField.getText().equals(user.getWachtwoord())) {
            Main.getSceneManager().showWelcomeScene();
            // TODO rolnaam meegeven aan welkomstscherm.
        }
        else {
            System.out.println("Het wachtwoord is onjuist.");
        }

        // if (user.getRolNaam().equals("student")) TODO gebruiken in welkomstscherm
    }

    public void doQuit() {
        // TODO System.exit & DBAccess.closeConnection?
    }
}
