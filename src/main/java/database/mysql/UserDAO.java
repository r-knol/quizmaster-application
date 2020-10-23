package database.mysql;
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
                String name = resultSet.getString("gebruikersNaam");
                String password = resultSet.getString("Wachtwoord");
                user = new User(id, role, name, password);
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
                String name = resultSet.getString("gebruikersNaam");
                String role = resultSet.getString("rolNaam");
                String password = resultSet.getString("wachtwoord");
                result = new User(id, role, name, password);
                result.setGebruikerID(id);
            } else {
                System.out.println("Gebruiker met dit gebruikerID bestaat niet");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }
    public User getOneByName(String name) {
        String sql = "Select * FROM gebruiker WHERE gebruikersNaam = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(3, name);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int id = resultSet.getInt("gebruikersID");
                String role = resultSet.getString("rolNaam");
                String password = resultSet.getString("wachtwoord");
                result = new User(id, role, name, password);
                result.setRolNaam(role);
            } else {
                System.out.println("Gebruiker bestaat niet");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }
    @Override
    public void storeOne(User user) {
        String sql = "Insert into gebruiker(GebruikerID, rolNaam, naam, wachtwoord) values(?,?,?,?) ;";
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