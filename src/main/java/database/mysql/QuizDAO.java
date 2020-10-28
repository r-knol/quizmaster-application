package database.mysql;

/**
 * @author Olaf van der Kaaij
 */

import model.Course;
import model.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDAO extends AbstractDAO implements  GenericDAO<Quiz> {

    public QuizDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public ArrayList<Quiz> getAll() {
        String sql = "Select * FROM quiz";
        ArrayList<Quiz> result = new ArrayList<>();
        Quiz tussenResultaat = null;
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                Course course = courseDAO.getOneById(resultSet.getInt("cursusID"));
                String quizNaam = resultSet.getString("quizNaam");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                tussenResultaat = new Quiz(quizID, course, quizNaam, succesDefinitie);
                result.add(tussenResultaat);
            }
            if (result.isEmpty()) {
                System.out.println("Deze Cursus heeft geen quizzes.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        } return result;
    }

    public Quiz getOneById(int quizID) {
        String sql = "Select * FROM quiz WHERE quizID = ?";
        Quiz result = null;
        CourseDAO courseDAO = new CourseDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quizID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                Course course = courseDAO.getOneById(resultSet.getInt("CursusID"));
                String quizNaam = resultSet.getString("quizNaam");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                result = new Quiz(course, quizNaam, succesDefinitie);
                result.setQuizID(quizID);
            } else {
                System.out.println("Quiz met dit quizID bestaat niet");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } return result;
    }

    @Override
    public void storeOne(Quiz quiz) {
        String sql = "Insert into quiz(curusID, quizNaam, succesDefinitie) values(?,?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setInt(1, quiz.getCursusID());
            preparedStatement.setString(2,quiz.getQuizNaam());
            preparedStatement.setInt(3, quiz.getSuccesDefinitie());
            int quizID = executeInsertStatementWithKey();
            quiz.setQuizID(quizID);
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void updateQuiz (Quiz quiz) {
        String sql = "UPDATE Quiz SET cursusID = ?, quizNaam = ?, succesDefinitie = ? WHERE quizID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getQuizID());
            preparedStatement.setInt(2, quiz.getCursusID());
            preparedStatement.setString(3, quiz.getQuizNaam());
            preparedStatement.setInt(4, quiz.getSuccesDefinitie());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }
}
