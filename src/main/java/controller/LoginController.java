package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import view.Main;

/**
 * @author Richard Knol, Wendy Ellens
 */

public class LoginController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;

    public void doLogin() {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());

        // De gegevens van de inloggende gebruiker opzoeken in de database
        Main.setUser(userDAO.getOneByUsernameAndPassword(nameTextField.getText(),passwordField.getText()));

        // Controleren of de gebruikersnaam en het wachtwoord juist zijn
        if (Main.getUser() == null) { // Indien onjuist: gebruiker waarschuwen en op inlogpagina blijven
            Alert foutmelding = new Alert(Alert.AlertType.WARNING);
            foutmelding.setContentText("Deze combinatie van gebruikersnaam en wachtwoord is onbekend.");
            foutmelding.show();
        }
        else { // Indien juist: doorgaan naar het welkomstscherm
            Main.getSceneManager().showWelcomeScene();
        }
    }

    public void doQuit() {
        Main.getDBaccess().closeConnection();
        System.exit(0);
    }
}
