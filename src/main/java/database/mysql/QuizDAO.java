package database.mysql;

/**
 * @author Olaf van der Kaaij
 */

import model.Course;
import model.Quiz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDAO extends AbstractDAO implements GenericDAO<Quiz> {

    public QuizDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public ArrayList<Quiz> getAll() {
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        String sql = "Select * FROM Quiz";
        ArrayList<Quiz> result = new ArrayList<>();
        Quiz tussenResultaat;
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                Course course = courseDAO.getOneById(resultSet.getInt("cursusID"));
                String quizNaam = resultSet.getString("quizNaam");
                int aantalVragen = resultSet.getInt("aantalVragen");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                tussenResultaat = new Quiz(quizID, course, quizNaam, aantalVragen,succesDefinitie);
                result.add(tussenResultaat);
            }
            if (result.isEmpty()) {
                System.out.println("Deze Cursus heeft geen quizzes.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        } return result;
    }

    public ArrayList<Quiz> getAllByCourseId(int courseID) {
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        String sql = "SELECT * FROM Quiz WHERE cursusID = ?";
        ArrayList<Quiz> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, courseID);
            ResultSet resultSet = executeSelectStatement();
            Quiz quiz;
            while (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                String quizNaam = resultSet.getString("quizNaam");
                int aantalVragen = resultSet.getInt("aantalVragen");
                int sDefinitie = resultSet.getInt("succesDefinitie");
                quiz = new Quiz(quizID, courseDAO.getOneById(courseID), quizNaam, aantalVragen, sDefinitie);
                result.add(quiz);
            }
            if (result.isEmpty()) {
                System.out.println("Deze cursus heeft geen quizzen.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Quiz getOneById(int quizID) {
        String sql = "Select * FROM quizmaster.quiz WHERE quizID = ?";
        Quiz result = null;
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quizID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                Course course = courseDAO.getOneById(resultSet.getInt("cursusID"));
                String quizNaam = resultSet.getString("quizNaam");
                int aantalVragen = resultSet.getInt("aantalVragen");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                result = new Quiz(quizID, course, quizNaam, aantalVragen,succesDefinitie);
            } else {
                System.out.println("Quiz met dit quizID bestaat niet");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void storeOne(Quiz quiz) {
        String sql = "INSERT INTO quiz (cursusID, quiznaam, aantalVragen,succesDefinitie)" +
                "VALUES (?,?,?,?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setInt(1, quiz.getCourse().getCursusID());
            preparedStatement.setString(2,quiz.getQuizNaam());
            preparedStatement.setInt(3,quiz.getAantalVragen());
            preparedStatement.setInt(4, quiz.getSuccesDefinitie());
            int key = executeInsertStatementWithKey();
            quiz.setQuizID(key);
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void updateOne (Quiz quiz) {
        String sql = "UPDATE Quiz SET cursusID = ?, quizNaam = ?, aantalVragen = ?, succesDefinitie = ? WHERE quizID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getCourse().getCursusID());
            preparedStatement.setString(2, quiz.getQuizNaam());
            preparedStatement.setInt(3,quiz.getAantalVragen());
            preparedStatement.setInt(4, quiz.getSuccesDefinitie());
            preparedStatement.setInt(5, quiz.getQuizID());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteOne(Quiz quiz) {
        String sql = "DELETE FROM quiz WHERE quizID = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getQuizID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }
}
