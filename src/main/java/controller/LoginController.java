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
        User user = userDAO.getOneByName(nameTextField.getText());
        //todo: checken of gebruikersnaam wel bestaat en wachtwoord goed is
    }

    public void doQuit() {
    }
}
