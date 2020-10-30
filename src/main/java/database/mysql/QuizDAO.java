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




    public ArrayList<Quiz> getAll() {
        String sql = "Select * FROM quizmaster.quiz";
        ArrayList<Quiz> result = new ArrayList<>();
        Quiz tussenResultaat = null;
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                int cursusID = resultSet.getInt("cursusID");
                String quizNaam = resultSet.getString("quizNaam");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                tussenResultaat = new Quiz(quizID, cursusID, quizNaam, succesDefinitie);
                result.add(tussenResultaat);
            }
            if (result.isEmpty()) {
                System.out.println("Deze Cursus heeft geen quizzes.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        } return result;
    }

    public ArrayList<Quiz> getAllByCourseId(int id) {
        String sql = "SELECT * FROM Quizmaster.quiz WHERE cursusID = ?";
        ArrayList<Quiz> result = new ArrayList<>();
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = executeSelectStatement();
            Quiz quiz;
            if (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                int cursusID = resultSet.getInt("cursusID");
                String quizNaam = resultSet.getString("quizNaam");
                int sDefinitie = resultSet.getInt("succesDefinitie");
                quiz = new Quiz(quizID, cursusID, quizNaam, sDefinitie);
                result.add(quiz);
            } else {
                System.out.println("Cursus met dit cursusID bestaat niet");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }

    public Quiz getOneById(int quizID) {
        String sql = "Select * FROM quizmaster.quiz WHERE quizID = ?";
        Quiz result = null;
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quizID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int cursusID = resultSet.getInt("cursusID");
                String quizNaam = resultSet.getString("quizNaam");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                result = new Quiz(quizID, cursusID, quizNaam, succesDefinitie);
            } else {
                System.out.println("Quiz met dit quizID bestaat niet");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }

    @Override
    public void storeOne(Quiz quiz) {
        String sql = "INSERT INTO quiz (cursusID, quiznaam, succesDefinitie) \n" +
                "VALUES (?,?,?);";
        //CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            //ResultSet resultSet = executeSelectStatement();
            setupPreparedStatementWithKey(sql);
            //Course course = courseDAO.getOneById(resultSet.getInt("cursusID"));
            preparedStatement.setInt(1, quiz.getCursusID());
            //System.out.println(quiz.getCourse().getCursusID());
            preparedStatement.setString(2,quiz.getQuizNaam());
            preparedStatement.setInt(3, quiz.getSuccesDefinitie());
            int key = executeInsertStatementWithKey();
            quiz.setQuizID(key);
        }    catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            }
    }

    public void updateOne (Quiz quiz) {
        String sql = "UPDATE quizmaster.quiz SET quizNaam = ?, succesDefinitie = ? WHERE quizID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, quiz.getQuizNaam());
            preparedStatement.setInt(2, quiz.getSuccesDefinitie());
            preparedStatement.setInt(3, quiz.getQuizID());
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
