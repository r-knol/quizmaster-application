package controller;

import database.mysql.DBAccess;
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

public class CreateUpdateUserController {

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

    // Huidige gebruiker aanmaken (this.user) en huidige data uit database weergeven als gebruiker al bestaat
    public void setup(User user) {
        this.user = user;
        setupCode();
    }

    public void doCreateUpdateUser() {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        if (user != null) { // Wijzigen van een bestaande gebruiker
            user.setRol(Rol.getText());
            user.setVoornaam(Voornaam.getText());
            user.setTussenvoegsels(Tussenvoegsel.getText());
            user.setAchternaam(Achternaam.getText());
            userDAO.updateOne(user);
            Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
            gewijzigd.setContentText("Gebruiker gewijzigd");
            gewijzigd.show();
        }
        else { // Nieuwe gebruiker aanmaken
            user = new User(Rol.getText(), Voornaam.getText(), Tussenvoegsel.getText(), Achternaam.getText());
            userDAO.storeOne(user);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Gebruiker aangemaakt");
            aangemaakt.show();
            setupCode(); // Gegenereerde ID, gebruikersnaam en wachtwoord tonen
        }
    }

    public void doMenu() {
        // Terug naar manageUsers scherm
        Main.getSceneManager().showManageUserScene();
    }

    public void setupCode() {
        if (user == null) {
            titleLabel.setText("Nieuwe gebruiker");
            GebruikersID.setText("");
            Rol.setText("");
            Gebruikersnaam.setText("");
            Wachtwoord.setText("");
            Voornaam.setText("");
            Tussenvoegsel.setText("");
            Achternaam.setText("");
            submitButton.setText("Nieuw");
        } else {
            titleLabel.setText("Wijzig gebruiker");
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
}
