package database.mysql;

import model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Wendy Ellens
 */

public class CourseDAO extends AbstractDAO implements GenericDAO<Course> {

    public CourseDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public ArrayList getAll() {
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
                course = new Course(cursusID, cursusNaam, coordinatorID);
                result.add(course);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    @Override
    public Course getOneById(int cursusID) {
        String sql = "Select * From Cursus Where cursusID = ?";
        Course result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, cursusID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String cursusNaam = resultSet.getString("cursusNaam");
                int coordinatorID = resultSet.getInt("coordinatorID");
                result = new Course(cursusID, cursusNaam, coordinatorID);
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
            setupPreparedStatement(sql);
            preparedStatement.setString(1, course.getCursusNaam());
            preparedStatement.setInt(2, course.getCoordinatorID());
            int key = executeInsertStatementWithKey();
            course.setCursusID(key);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    @Override
    public void updateOne(Course course) {
        String sql = "Update Cursus Set cursusNaam = ?, coordinatorID = ? Where cursusID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, course.getCursusNaam());
            preparedStatement.setInt(2, course.getCoordinatorID());
            preparedStatement.setInt(3, course.getCursusID());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

}
