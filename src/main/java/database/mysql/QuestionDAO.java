package database.mysql;

/**
 * @author Olaf van der Kaaij
 */

import model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO extends  AbstractDAO implements  GenericDAO<Question> {

    public QuestionDAO (DBAccess dbAccess) {
        super((dbAccess));
    }

    @Override
    public ArrayList<Question> getAll() {
        String sql = "SELECT * FROM Vraag";
        ArrayList<Question> result = new ArrayList<>();
        Question tussenResultaat;
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Question question;
            while (resultSet.next()) {
                int vraagID = resultSet.getInt("vraagID");
                int quizID = resultSet.getInt("quizID");
                String quizVraag = resultSet.getString("vraag");
                String juistAntwoord = resultSet.getString("antwoord1");
                String foutAntwoord1 = resultSet.getString("antwoord2");
                String foutAntwoord2 = resultSet.getString("antwoord3");
                String foutAntwoord3 = resultSet.getString("antwoord4");
                tussenResultaat = new Question (vraagID, quizID, quizVraag, juistAntwoord, foutAntwoord1, foutAntwoord2, foutAntwoord3);
                result.add(tussenResultaat);
            }
            if (result.isEmpty()) {
                System.out.println("Deze quiz heeft geen vragen.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        } return  result;
    }

    @Override
    public Question getOneById(int vraagID) {
        String sql = "SELECT * FROM Vraag WHERE vraagID = ?";
        Question result = null;
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, vraagID);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int quizID = resultSet.getInt("quizID");
                String quizVraag = resultSet.getString("vraag");
                String juistAntwoord = resultSet.getString("antwoord1");
                String foutAntwoord1 = resultSet.getString("antwoord2");
                String foutAntwoord2 = resultSet.getString("antwoord3");
                String foutAntwoord3 = resultSet.getString("antwoord4");
                result = new Question(quizID, quizVraag, juistAntwoord, foutAntwoord1, foutAntwoord2, foutAntwoord3);
                result.setVraagID(vraagID);
            } else {
                System.out.println("Vraag met dit vraagID bestaat niet.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    public void storeOne(Question question) {
        String sql = "INSERT into Vraag(quizID, vraag, antwoord1, antwoord2, antwoord3, antwoord4) VALUES (?,?,?,?,?,?)";
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        try {
            setupPreparedStatementWithKey(sql);
            ResultSet resultSet = executeSelectStatement();
            preparedStatement.setInt(1, question.getQuizID());
            preparedStatement.setString(2, question.getQuizVraag());
            preparedStatement.setString(3, question.getJuistAntwoord());
            preparedStatement.setString(4, question.getFoutAntwoord1());
            preparedStatement.setString(5, question.getFoutAntwoord2());
            preparedStatement.setString(6, question.getFoutAntwoord3());
            int id = executeInsertStatementWithKey();
            question.setVraagID(id);
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

    public void updateQuestion (Question question) {
        String sql = "UPDATE Vraag SET vraag = ?, antwoord1 = ?, antwoord2 = ?, antwoord3 = ?, antwoord4 = ? WHERE vraagID = ? AND quizID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, question.getQuizVraag());
            preparedStatement.setString(2, question.getJuistAntwoord());
            preparedStatement.setString(3, question.getFoutAntwoord1());
            preparedStatement.setString(4, question.getFoutAntwoord2());
            preparedStatement.setString(5, question.getFoutAntwoord3());
            preparedStatement.setInt(6, question.getVraagID());
            preparedStatement.setInt(7, question.getQuizID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }
}
