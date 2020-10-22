package database.mysql;


package database.mysql;
import model.Student;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
    // Peter van den Bol onderlegger gebruikt van Richard Knol
    public class StudentDAO extends AbstractDAO implements GenericDAO<Student> {
        public UserDAO(DBAccess dbAccess) {
            super(dbAccess);
        }
        @Override
        public ArrayList<Student> getAll() {
            String sql = "Select * FROM gebruiker";
            ArrayList<Student> result = new ArrayList<>();
            try {
                setupPreparedStatement(sql);
                ResultSet resultSet = executeSelectStatement();
                Student student;
                while (resultSet.next()) {
                    int id = resultSet.getInt("GebruikerID");
                    String rolNaam = resultSet.getString("rolNaam");
                    String naam = resultSet.getString("Naam");
                    String wachtwoord = resultSet.getString("Wachtwoord");
                    student = new Student(id, rolNaam, naam, wachtwoord);
                    result.add(student);
                }
            } catch (SQLException e) {
                System.out.println("SQL error " + e.getMessage());
            } return result;
        }
        @Override
        public Student getOneById(int id) {
            String sql = "Select * FROM gebruiker WHERE gebruikerID = ?";
            Student result = null;
            try {
                setupPreparedStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = executeSelectStatement();
                if (resultSet.next()) {
                    String naam = resultSet.getString("naam");
                    String wachtwoord = resultSet.getString("wachtwoord");
                    result = new Student(naam, wachtwoord);
                    result.setGebruikerID(id);
                } else {
                    System.out.println("Gebruiker met dit gebruikerID bestaat niet");
                }
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
            } return result;
        }
        @Override
        public void storeOne(Student student) {
            String sql = "Insert into gebruiker(GebruikerID, rolNaam, naam, wachtwoord) values(?,?,?,?) ;";
            try {
                setupPreparedStatement(sql);
                preparedStatement.setInt(1, student.getGebruikerID());
                preparedStatement.setString(2, student.getRolNaam());
                preparedStatement.setString(3, student.getNaam());
                preparedStatement.setString(4, student.getWachtwoord());
                executeManipulateStatement();
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
            }
        }

}//class




