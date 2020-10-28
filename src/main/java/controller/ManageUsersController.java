package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
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

    // TODO: lijst met records uit mysql zichtbaar maken in ListView pane.
    public void setup() {
        this.userDAO = new UserDAO(dbAccess);
        List<User> allUsers = userDAO.getAll();
        for (User u : allUsers) {
            userList.getItems().add(u);
        }
        userList.getSelectionModel().selectFirst();


        // tostring methode in user
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        Main.getSceneManager().showCreateUpdateUserScene(Main.getUser());
    }

    public void doUpdateUser() {}

    public void doDeleteUser() {}
}
