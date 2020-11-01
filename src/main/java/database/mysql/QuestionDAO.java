package database.mysql;

/**
 * @author Olaf van der Kaaij
 */

import model.Question;
import model.Quiz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO extends AbstractDAO implements GenericDAO<Question> {

    public QuestionDAO(DBAccess dbAccess) {
        super((dbAccess));
    }

    @Override
    public ArrayList<Question> getAll() {
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        String sql = "SELECT * FROM vraag";
        ArrayList<Question> result = new ArrayList<>();
                Question tussenResultaat;
                try {
                    setupPreparedStatement(sql);
                    ResultSet resultSet = executeSelectStatement();
                    while (resultSet.next()) {
                        int vraagID = resultSet.getInt("vraagID");
                        Quiz quiz = quizDAO.getOneById(resultSet.getInt("quizID"));
                        String quizVraag = resultSet.getString("vraag");
                        String juistAntwoord = resultSet.getString("antwoord1");
                        String foutAntwoord1 = resultSet.getString("antwoord2");
                String foutAntwoord2 = resultSet.getString("antwoord3");
                String foutAntwoord3 = resultSet.getString("antwoord4");
                tussenResultaat = new Question(vraagID, quiz, quizVraag, juistAntwoord, foutAntwoord1, foutAntwoord2, foutAntwoord3);
                result.add(tussenResultaat);
            }
            if (result.isEmpty()) {
                System.out.println("Deze quiz heeft geen vragen.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    public ArrayList<Question> getAllByQuizId(int quizID) {
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        String sql = "SELECT * FROM Vraag WHERE quizID = ?";
        ArrayList<Question> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt( 1,quizID);
            ResultSet resultSet = executeSelectStatement();
            Question question;
            while (resultSet.next()) {
                int vraagID = resultSet.getInt("vraagID");
                String quizVraag = resultSet.getString("vraag");
                String juistAntwoord = resultSet.getString("antwoord1");
                String foutAntwoord1 = resultSet.getString("antwoord2");
                String foutAntwoord2 = resultSet.getString("antwoord3");
                String foutAntwoord3 = resultSet.getString("antwoord4");
                question = new Question(vraagID, quizDAO.getOneById(quizID), quizVraag, juistAntwoord, foutAntwoord1, foutAntwoord2, foutAntwoord3);
                result.add(question);
            }
            if (result.isEmpty()) {
                System.out.println("Deze quiz heeft geen vragen.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    @Override
    public Question getOneById(int id) {
        String sql = "SELECT * FROM vraag WHERE vraagID = ?" ;
        Question result = null;
        QuizDAO quizDAO = new QuizDAO(dbAccess);
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                Quiz quiz = quizDAO.getOneById(resultSet.getInt("quizID"));
                String quizVraag = resultSet.getString("vraag");
                String juistAntwoord = resultSet.getString("antwoord1");
                String foutAntwoord1 = resultSet.getString("antwoord2");
                String foutAntwoord2 = resultSet.getString("antwoord3");
                String foutAntwoord3 = resultSet.getString("antwoord4");
                result = new Question(id, quiz, quizVraag, juistAntwoord, foutAntwoord1, foutAntwoord2, foutAntwoord3);
                result.setVraagID(id);
            } else {
                System.out.println("Vraag met dit vraagID bestaat niet.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    @Override
    public void storeOne(Question question) {
        String sql = "INSERT into Vraag(vraagID, quizID, vraag, antwoord1, antwoord2, antwoord3, \n" +
                "antwoord4) VALUES (?,?,?,?,?,?,?);";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1,question.getVraagID());
            preparedStatement.setInt(2, question.getQuiz().getQuizID());
            preparedStatement.setString(3, question.getQuizVraag());
            preparedStatement.setString(4, question.getJuistAntwoord());
            preparedStatement.setString(5, question.getFoutAntwoord1());
            preparedStatement.setString(6, question.getFoutAntwoord2());
            preparedStatement.setString(7, question.getFoutAntwoord3());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

    public void updateOne(Question question) {
        String sql = "UPDATE Vraag SET vraag = ?, antwoord1 = ?, antwoord2 = ?, antwoord3 = ?, antwoord4 = ? WHERE vraagID = ? AND quizID = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, question.getQuizVraag());
            preparedStatement.setString(2, question.getJuistAntwoord());
            preparedStatement.setString(3, question.getFoutAntwoord1());
            preparedStatement.setString(4, question.getFoutAntwoord2());
            preparedStatement.setString(5, question.getFoutAntwoord3());
            preparedStatement.setInt(6, question.getVraagID());
            preparedStatement.setInt(7, question.getQuiz().getQuizID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

    @Override
    public void deleteOne(Question question) {
        String sql = "DELETE FROM Vraag WHERE vraagID = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, question.getVraagID());
            executeManipulateStatement();
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }
}
