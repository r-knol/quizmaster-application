package database.mysql;

import model.Course;
import model.User;
import view.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Wendy Ellens
 * Om cursusgegevens aan de SQL-database toe te voegen of eruit op te halen
 */

public class CourseDAO extends AbstractDAO implements GenericDAO<Course> {

    public CourseDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public ArrayList<Course> getAll() {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        String sql = "Select * FROM Cursus";
        ArrayList<Course> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Course course;
            while (resultSet.next()) {
                int cursusID = resultSet.getInt("cursusID");
                String cursusNaam = resultSet.getString("cursusNaam");
                int coordinatorID = resultSet.getInt("coordinatorID");
                course = new Course(cursusID, cursusNaam, userDAO.getOneById(coordinatorID));
                result.add(course);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    public ArrayList<Course> getAllByStudentID (int studentID) {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        String sql = "SELECT * FROM Cursusinschrijving WHERE studentID = ?";
        ArrayList<Course> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = executeSelectStatement();
            Course course;
            while (resultSet.next()) {
                int cursusID = resultSet.getInt("cursusID");
                course = getOneById(cursusID);
                result.add(course);
                System.out.println("bla" + course);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    public ArrayList<Course> getAllByCoordinatorID(int coordinatorID) {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        String sql = "Select * FROM Cursus Where coordinatorID = ?";
        ArrayList<Course> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, coordinatorID);
            ResultSet resultSet = executeSelectStatement();
            Course course;
            while (resultSet.next()) {
                int cursusID = resultSet.getInt("cursusID");
                String cursusNaam = resultSet.getString("cursusNaam");
                course = new Course(cursusID, cursusNaam, userDAO.getOneById(coordinatorID));
                result.add(course);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    @Override
    public Course getOneById(int cursusID) {
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        String sql = "Select * From Cursus Where cursusID = ?";
        Course result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, cursusID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String cursusNaam = resultSet.getString("cursusNaam");
                int coordinatorID = resultSet.getInt("coordinatorID");
                result = new Course(cursusID, cursusNaam, userDAO.getOneById(coordinatorID));
            } else {
                System.out.println("Cursus met dit cursusnummer bestaat niet");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void storeOne(Course course) {
        String sql = "Insert into Cursus(cursusnaam, coordinatorID) values(?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, course.getCursusNaam());
            preparedStatement.setInt(2, course.getUser().getGebruikerID());
            int key = executeInsertStatementWithKey();
            course.setCursusID(key);
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    @Override
    public void updateOne(Course course) {
        String sql = "Update Cursus Set cursusNaam = ?, coordinatorID = ? Where cursusID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, course.getCursusNaam());
            preparedStatement.setInt(2, course.getUser().getGebruikerID());
            preparedStatement.setInt(3, course.getCursusID());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    public void storeOneInCourseRegistration(Course course) {
        String sql = "INSERT INTO Cursusinschrijving(cursusID, studentID) VALUES (?,?);";
        course.setCursusID(course.getCursusID());
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt( 1, course.getCursusID() );
            preparedStatement.setInt( 2, Main.getUser().getGebruikerID() );
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println( "SQL error " + e.getMessage() );
        }
    }

    public void deleteOneFromCourseRegistration(Course course) {
        String sql = "DELETE FROM Cursusinschrijving WHERE studentID = ? AND cursusID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, Main.getUser().getGebruikerID());
            preparedStatement.setInt( 2,course.getCursusID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }


    @Override
    public void deleteOne(Course course) {
        String sql = "DELETE FROM Cursus WHERE cursusID = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, course.getCursusID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

}
