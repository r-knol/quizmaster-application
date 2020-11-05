package database.mysql;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Richard Knol, Wendy Ellens
 * Om cursusgegevens aan de SQL-database toe te voegen of eruit op te halen
 */

public class UserDAO extends AbstractDAO implements GenericDAO<User> {
    public UserDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public ArrayList<User> getAll() {
        String sql = "Select * FROM Gebruiker";
        ArrayList<User> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                int id = resultSet.getInt("gebruikersID");
                String role = resultSet.getString("rolNaam");
                String userName = resultSet.getString("gebruikersNaam");
                String password = resultSet.getString("wachtwoord");
                String firstName = resultSet.getString("voornaam");
                String preposition = resultSet.getString("tussenvoegsel");
                String lastName = resultSet.getString("achternaam");
                user = new User(id, role, userName, password, firstName, preposition, lastName);
                result.add(user);
            }
        }
        catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    public ArrayList<User> getAllByRole(String role) {
        String sql = "Select * FROM Gebruiker WHERE rolNaam = ?";
        ArrayList<User> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, role);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                int id = resultSet.getInt("gebruikersID");
                String userName = resultSet.getString("gebruikersNaam");
                String password = resultSet.getString("wachtwoord");
                String firstName = resultSet.getString("voornaam");
                String preposition = resultSet.getString("tussenvoegsel");
                String lastName = resultSet.getString("achternaam");
                user = new User(id, role, userName, password, firstName, preposition, lastName);
                result.add(user);
            }
        }
        catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    @Override
    public User getOneById(int id) {
        String sql = "Select * FROM Gebruiker WHERE gebruikersID = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String role = resultSet.getString("rolNaam");
                String userName = resultSet.getString("gebruikersNaam");
                String password = resultSet.getString("wachtwoord");
                String firstName = resultSet.getString("voornaam");
                String preposition = resultSet.getString("tussenvoegsel");
                String lastName = resultSet.getString("achternaam");
                result = new User(id, role, userName, password, firstName, preposition, lastName);
            }
            else {
                System.out.println("Gebruiker met dit gebruikerID bestaat niet");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    public User getOneByUsername(String userName) {
        String sql = "Select * FROM Gebruiker WHERE gebruikersNaam = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int id = resultSet.getInt("gebruikersID");
                String role = resultSet.getString("rolNaam");
                String password = resultSet.getString("wachtwoord");
                String firstName = resultSet.getString("voornaam");
                String preposition = resultSet.getString("tussenvoegsel");
                String lastName = resultSet.getString("achternaam");
                result = new User(id, role, userName, password, firstName, preposition, lastName);
            } // Als de gebruikersnaam bestaat, wordt de gebruiker terug gegeven, anders een leeg object.
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    public User getOneByUsernameAndPassword(String userName, String password) {
        String sql = "Select * FROM Gebruiker WHERE gebruikersNaam = ? AND wachtwoord = ?";
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
                String preposition = resultSet.getString("tussenvoegsel");
                String lastName = resultSet.getString("achternaam");
                result = new User(id, role, userName, password, firstName, preposition, lastName);
            }   /* Als de combinatie gebruikersnaam-wachtwoord bestaat, wordt de gebruiker terug gegeven,
                anders een leeg object.*/
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void storeOne(User user) {
        String sql = "Insert into Gebruiker(rolNaam, gebruikersNaam, wachtwoord, " +
                "voornaam, tussenvoegsel, achternaam) values(?,?,?,?,?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, user.getRol());
            preparedStatement.setString(2, user.getGebruikersnaam());
            preparedStatement.setString(3, user.getWachtwoord());
            preparedStatement.setString(4, user.getVoornaam());
            preparedStatement.setString(5, user.getTussenvoegsels());
            preparedStatement.setString(6, user.getAchternaam());
            int key = executeInsertStatementWithKey();
            user.setGebruikerID(key);
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void updateOne(User user) {
        String sql = "Update Gebruiker SET rolNaam = ?, gebruikersNaam = ?, " +
                "wachtwoord = ?, voornaam = ?, tussenvoegsel = ?, achternaam = ? " +
                "where gebruikersID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, user.getRol());
            preparedStatement.setString(2, user.getGebruikersnaam());
            preparedStatement.setString(3, user.getWachtwoord());
            preparedStatement.setString(4, user.getVoornaam());
            preparedStatement.setString(5, user.getTussenvoegsels());
            preparedStatement.setString(6, user.getAchternaam());
            preparedStatement.setInt(7, user.getGebruikerID());
            executeManipulateStatement();
        }
        catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

    @Override
    public void deleteOne(User user) {
        String sql = "DELETE FROM Gebruiker WHERE gebruikersID = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, user.getGebruikerID());
            executeManipulateStatement();
        }
        catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }
}