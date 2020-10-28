package database.mysql;

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
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Quiz quiz;
            while (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                int cursusID = resultSet.getInt("cursusID");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                quiz = new Quiz(quizID, cursusID, succesDefinitie);
                result.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        } return result;
    }

    public Quiz getOneById(int quizID) {
        String sql = "Select * FROM gebruiker WHERE quizID = ?";
        Quiz result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quizID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int cursusID = resultSet.getInt("CursusID");
                int succesDefinitie = resultSet.getInt("succesDefinitie");
                result = new Quiz(quizID, cursusID, succesDefinitie);
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
        String sql = "Insert into quiz(quizID, curusID, succesDefinitie) values(?,?,?) ;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getQuizID());
            preparedStatement.setInt(2, quiz.getCursusID());
            preparedStatement.setInt(3, quiz.getSuccesDefinitie());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void updateQuiz (Quiz quiz) {
        String sql = "UPDATE Quiz SET cursusID = ?, succesDefinitie = ? WHERE quizID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getQuizID());
            preparedStatement.setInt(2, quiz.getCursusID());
            preparedStatement.setInt(3, quiz.getSuccesDefinitie());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }
}
