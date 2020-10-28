package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import model.User;
import view.Main;

import java.util.List;

public class ManageUsersController {

    private UserDAO userDAO;
    private DBAccess dbAccess;

    @FXML
    ListView<User> userList;

    public ManageUsersController() {
        super();
        this.dbAccess = Main.getDBaccess();
    }

    // Alle gebruikers (met gebruikersnaam) in ListView userList laten zien
    public void setup() {
        this.userDAO = new UserDAO(dbAccess);
        List<User> allUsers = userDAO.getAll();
        for (User u : allUsers) {
            userList.getItems().add(u);
        }
        userList.getSelectionModel().selectFirst(); // selecteert de eerste gebruiker op de lijst
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        Main.getSceneManager().showCreateUpdateUserScene(null); // pakt lege user
    }

    public void doUpdateUser() {
        User user = userList.getSelectionModel().getSelectedItem(); // pakt geselecteerde item
        Main.getSceneManager().showCreateUpdateUserScene(user);
    }

    public void doDeleteUser() {


    }
}
