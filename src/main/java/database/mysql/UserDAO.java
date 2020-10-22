package database.mysql;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// gemaakt door Richard

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
            // User user;
            while (resultSet.next()) {
                resultSet.getString("rolNaam");
                resultSet.getString("gebruikersNaam");
                resultSet.getString("Wachtwoord");
                //user = new User(id, rolNaam, naam, wachtwoord);
                //result.add(user);
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
                resultSet.getString("gebruikersNaam");
                resultSet.getString("wachtwoord");
                // result = new User(naam, wachtwoord);
                // result.setGebruikerID(id);
            } else {
                System.out.println("Gebruiker met dit gebruikerID bestaat niet");
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