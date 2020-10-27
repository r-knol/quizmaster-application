package database.mysql;
import javafx.scene.control.Alert;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Richard Knol
 */

public class UserDAO extends AbstractDAO implements GenericDAO<User> {
    public UserDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public ArrayList<User> getAll() {
        String sql = "Select * FROM gebruiker";
        ArrayList<User> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                int id = resultSet.getInt("gebruikersID");
                String role = resultSet.getString("rolNaam");
                String userName = resultSet.getString("gebruikersNaam");
                String firstName = resultSet.getString("voornaam");
                String password = resultSet.getString("Wachtwoord");
                user = new User(id, role, userName, firstName, password);
                result.add(user);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        } return result;
    }

    @Override
    public User getOneById(int id) {
        String sql = "Select * FROM gebruiker WHERE gebruikerID = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String userName = resultSet.getString("gebruikersNaam");
                String role = resultSet.getString("rolNaam");
                String firstName = resultSet.getString("voornaam");
                String password = resultSet.getString("wachtwoord");
                result = new User(id, role, userName, firstName, password);
                result.setGebruikerID(id);
            } else {
                System.out.println("Gebruiker met dit gebruikerID bestaat niet");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }

    public User getOneByNameAndPassword(String userName, String password) {
        String sql = "Select * FROM gebruiker WHERE gebruikersNaam = ? AND wachtwoord = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int id = resultSet.getInt("gebruikersID");
                String role = resultSet.getString("rolNaam");
                String firstName = resultSet.getString("voornaam");
                result = new User(id, role, userName, firstName, password);
                result.setGebruikerID(id);
            }   /* Als de combinatie gebruikersnaam-wachtwoord bestaat, wordt de gebruiker terug gegeven,
                anders een leeg object.*/
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }

    @Override
    public void storeOne(User user) {
        String sql = "Insert into gebruiker(GebruikerID, rolNaam, gebruikersNaam, wachtwoord) values(?,?,?,?) ;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, user.getGebruikerID());
            preparedStatement.setString(2, user.getRolNaam());
            preparedStatement.setString(3, user.getNaam());
            preparedStatement.setString(4, user.getWachtwoord());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}