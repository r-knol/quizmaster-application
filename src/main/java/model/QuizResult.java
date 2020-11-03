package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Wendy Ellens
 */

// TODO poging uit ERD halen
public class QuizResult {
    private User student;
    private Quiz quiz;
    private LocalDateTime datum;
    private List<QuestionAnswerPair> vraagAntwoordParen;
    private Boolean behaald;

    public QuizResult(User student, Quiz quiz, int pogingnummer, LocalDateTime datum, List<QuestionAnswerPair> vraagAntwoordParen) {
        this.student = student;
        this.quiz = quiz;
        this.datum = datum;
        this.vraagAntwoordParen = vraagAntwoordParen;
        this.behaald = bepaalBehaald(quiz, vraagAntwoordParen);
    }

    private Boolean bepaalBehaald(Quiz quiz, List<QuestionAnswerPair> vraagAntwoordParen) {
        int aantalJuisteAntwoorden = 0;
        for (QuestionAnswerPair vraagAntwoordPaar : vraagAntwoordParen) {
            if (vraagAntwoordPaar.getVraag().getJuistAntwoord().equals(vraagAntwoordPaar.getAntwoord())) {
                aantalJuisteAntwoorden++;
            }
        }
        return aantalJuisteAntwoorden >= quiz.getSuccesDefinitie();
    }

    @Override
    public String toString() {
        return "Het resultaat van quiz " + quiz.getQuizNaam() + " op " + datum.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " was: " + behaald;
    }
}
