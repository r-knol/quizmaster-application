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

/** @ Author Richard Knol
 */

public class CreateUpdateUserController {

    private UserDAO userDAO;
    private DBAccess dbAccess;
    private User user;

    public CreateUpdateUserController() {
        super();
        this.dbAccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dbAccess);
    }

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

    // Huidige gebruiker aanmaken (this.user) en data weergeven uit MySQL
    public void setup(User user) {
        if (user == null) {
            this.user = user;
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
        // Wijzigen van een bestaande gebruiker met de updateOne methode
        if (user != null) {
            user.setRol(Rol.getText());
            Wachtwoord.getText();
            user.setVoornaam(Voornaam.getText());
            user.setTussenvoegsels(Tussenvoegsel.getText());
            user.setAchternaam(Achternaam.getText());
            userDAO.updateOne(user);
            Alert gewijzigd = new Alert(Alert.AlertType.INFORMATION);
            gewijzigd.setContentText("Gebruiker gewijzigd");
            gewijzigd.show();
        } else { // Gebruiker aanmaken
            user = new User(Rol.getText(), Voornaam.getText(), Tussenvoegsel.getText(), Achternaam.getText());
            userDAO.storeOne(user);
            Alert aangemaakt = new Alert(Alert.AlertType.INFORMATION);
            aangemaakt.setContentText("Gebruiker aangemaakt");
            aangemaakt.show();
        }
    }

    public void doMenu() {
        // Terug naar manageUsers scherm
        Main.getSceneManager().showManageUserScene();
    }

}
