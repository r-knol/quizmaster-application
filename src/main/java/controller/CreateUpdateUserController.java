package controller;

import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import view.Main;

/** @author Richard Knol
 */

public class CreateUpdateUserController extends AbstractController {

    private User user;
    private String rol;

    @FXML
    Label titleLabel;
    @FXML
    private TextField GebruikersID;
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
    @FXML
    private MenuButton menuButton;

    public void setup(User user) {
        // Code voor dropdown menu
        String[] rollen = {"coordinator", "technisch beheerder", "administrator", "student"};
        for (String rol : rollen) {
            MenuItem item = new MenuItem(rol);
            item.setOnAction(e -> {
                this.rol = rol;
                menuButton.setText(rol);
            });
            menuButton.getItems().add(item);
        }

        // Scherm voor aanmaken nieuwe gebruiker
        if (user == null) {
            titleLabel.setText("Nieuwe gebruiker");
            submitButton.setText("Nieuw");
        }

        // Scherm voor het wijzigen van een bestaande gebruiker
        else {
            this.user = user;
            GebruikersID.setText(String.valueOf(user.getGebruikerID()));
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
        // Aanmaken van nieuwe gebruiker
        if (user == null) {
            user = new User(rol, Voornaam.getText(), Tussenvoegsel.getText(), Achternaam.getText());
            userDAO.storeOne(user);
            showInformationAlert(String.format("Gebruiker aangemaakt \nHet gebruikersID is: %s" +
                            "\nDe gebruikersnaam is: %s\nHet wachtwoord is %s",
                    user.getGebruikerID(), user.getGebruikersnaam(), user.getWachtwoord()));
            doMenu();
        }
        // Wijzigen van een bestaande gebruiker in de database
        else {
            user.setRol(rol);
            user.setGebruikersnaam(Gebruikersnaam.getText());
            user.setVoornaam(Voornaam.getText());
            user.setWachtwoord(Wachtwoord.getText());
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
