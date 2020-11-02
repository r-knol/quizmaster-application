package database.mysql;

/**
 * @author Olaf van der Kaaij
 */

import model.Course;
import model.CourseRegistration;
import model.User;
import view.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseRegistrationDAO extends  AbstractDAO implements GenericDAO<CourseRegistration> {

    public CourseRegistrationDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public ArrayList<CourseRegistration> getAll() {
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        UserDAO userDAo = new UserDAO(dbAccess);
        String sql = "SELECT * FROM Cursusinschrijving";
        ArrayList<CourseRegistration> result = new ArrayList<>();
        CourseRegistration tussenResultaat;
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                Course course = courseDAO.getOneById(resultSet.getInt("cursusID"));
                User user = userDAo.getOneById(resultSet.getInt("studentID"));
                tussenResultaat = new CourseRegistration(course, user);
                result.add(tussenResultaat);
            }
            if (result.isEmpty()) {
                System.out.println("Deze student is niet ingeschreven voor een cursus");
            }
        } catch (SQLException e) {
            System.out.println( "SQL error " + e.getMessage() );
        }
        return result;
    }

    public ArrayList<CourseRegistration> getAllByUserId (int gebruikersID) {
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        UserDAO userDAO = new UserDAO(dbAccess);
        String sql = "SELECT * FROM Cursusinschrijving WHERE studentID = ?";
        ArrayList<CourseRegistration> result = new ArrayList<>();
        try {
            setupPreparedStatement( sql );
            preparedStatement.setInt( 1, Main.getUser().getGebruikerID() );
            ResultSet resultSet = executeSelectStatement();
            CourseRegistration courseRegistration;
            if (resultSet.next()) {
                Course course = courseDAO.getOneById( resultSet.getInt("cursusID"));
                courseRegistration = new CourseRegistration( course, userDAO.getOneById(Main.getUser().getGebruikerID() ) );
                result.add( courseRegistration );
            }
        } catch (SQLException e) {
            System.out.println( "SQL error " + e.getMessage() );
        }
        return result;
    }

    public CourseRegistration getOneById(int id) {
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        UserDAO userDAO = new UserDAO(dbAccess);
        String sql = "Select * FROM Cursusinschrijving WHERE studentID = ?";
        CourseRegistration result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                Course course = courseDAO.getOneById( resultSet.getInt("cursusID"));
                result = new CourseRegistration(course, userDAO.getOneById(Main.getUser().getGebruikerID()));
            }
            else {
                System.out.println("Student heeft zich niet ingeschreven voor deze cursus");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    public void storeOne(CourseRegistration courseRegistration) {
        String sql = "INSERT INTO Cursusinschrijving(cursusID, studentID) VALUES (?,?);";
        CourseDAO courseDAO = new CourseDAO( dbAccess );
        Course course = new Course();
        try {
            setupPreparedStatement( sql );
            preparedStatement.setInt( 1, course.getCursusID() );
            preparedStatement.setInt( 2, Main.getUser().getGebruikerID() );
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println( "SQL error " + e.getMessage() );
        }
    }

    public void updateOne (CourseRegistration courseRegistration) {
        String sql = "UPDATE Cursusinschrijving SET cursusID = ? WHERE gebruikersID = ?;";
        Course course = new Course();
        try {
            setupPreparedStatement( sql );
            preparedStatement.setInt( 1, course.getCursusID() );
            preparedStatement.setInt( 2, Main.getUser().getGebruikerID() );
        } catch (SQLException e) {
            System.out.println( "SQL error " + e.getMessage() );
        }
    }

    public void deleteOne (CourseRegistration courseRegistration) {
        String sql = "DELETE FROM Cursusinschrijving WHERE gebruikersID = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, Main.getUser().getGebruikerID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }
}
