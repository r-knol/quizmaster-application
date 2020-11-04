package controller;

import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.User;
import view.Main;

import java.util.Collections;
import java.util.List;

/** @author Richard Knol
 */

public class ManageUsersController extends AbstractController {

    @FXML
    ListView<User> userList;

    // Alle courses in ListView userList laten zien
    public void setup() {
        setupCode();
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
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        User user = userList.getSelectionModel().getSelectedItem();
        showInformationAlert("Gebruiker verwijderd");
        userDAO.deleteOne(user);
        // Lijst met gebruikers verversen
        userList.getItems().clear();
        setupCode();
    }

    public void setupCode() {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        List<User> allUsers = userDAO.getAll();
        for (User u : allUsers) {
            userList.getItems().add(u);
        }
        userList.getSelectionModel().selectFirst(); // selecteert de eerste gebruiker op de lijst
    }
}
