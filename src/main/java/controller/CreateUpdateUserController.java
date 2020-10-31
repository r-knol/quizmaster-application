package controller;

import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import view.Main;

/** @author Richard Knol
 */

public class CreateUpdateUserController extends AbstractController {

    private User user;

    @FXML
    Label titleLabel;
    @FXML
    private TextField GebruikersID;
    @FXML
    private TextField Rol;
    @FXML
    private TextField Gebruikersnaam;
    @FXML
    private TextField Wachtwoord;
    @FXML
    private TextField Voornaam;
    @FXML
    private TextField Tussenvoegsel;
    @FXML
    private TextField Achternaam;
    @FXML
    private Button submitButton;

    public void setup(User user) {
        // Scherm voor het aanmaken van een nieuwe gebruiker
        if (user == null) {
            titleLabel.setText("Nieuwe gebruiker");
            submitButton.setText("Maak");
        }
        // Scherm voor het wijzigen van een bestaande gebruiker
        else {
            this.user = user;
            GebruikersID.setText(String.valueOf(user.getGebruikerID()));
            Rol.setText(user.getRol());
            Gebruikersnaam.setText(user.getGebruikersnaam());
            Wachtwoord.setText(user.getWachtwoord());
            Voornaam.setText(user.getVoornaam());
            Tussenvoegsel.setText(user.getTussenvoegsels());
            Achternaam.setText(user.getAchternaam());
            submitButton.setText("Wijzig");
        }
    }

    public void doCreateUpdateUser() {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        // Nieuwe gebruiker opslaan in de database
        if (user == null) {
            user = new User(Rol.getText(), Voornaam.getText(), Tussenvoegsel.getText(), Achternaam.getText());
            userDAO.storeOne(user);
            showInformationAlert(String.format("Gebruiker %s aangemaakt" +
                            "\nDe gebruikersnaam is: %s\nHet wachtwoord is %s",
                    user.getGebruikerID(), user.getGebruikersnaam(), user.getWachtwoord()));
            doMenu();
        }
        // Wijzigen van een bestaande gebruiker in de database
        else {
            user.setRol(Rol.getText());
            user.setVoornaam(Voornaam.getText());
            user.setTussenvoegsels(Tussenvoegsel.getText());
            user.setAchternaam(Achternaam.getText());
            userDAO.updateOne(user);
            showInformationAlert("Gebruiker gewijzigd");
            doMenu();
        }
    }

    public void doMenu() {
        Main.getSceneManager().showManageUserScene();
    }
}
