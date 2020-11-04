package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wendy Ellens
 * Om de resultaten van een quiz(poging) door een student in op te slaan.
 * De vragen worden ook opgeslagen voor het geval dat deze worden gewijzigd.
 */

public class QuizResult {
    private User student;
    private Quiz quiz;
    private LocalDateTime datum;
    private List<QuestionAnswerPair> vraagAntwoordParen; // lijst van vragen en gegeven antwoorden
    private Boolean behaald;

    public QuizResult(User student, Quiz quiz, LocalDateTime datum, List<QuestionAnswerPair> vraagAntwoordParen) {
        this.student = student;
        this.quiz = quiz;
        this.datum = datum;
        this.vraagAntwoordParen = vraagAntwoordParen;
        this.behaald = bepaalBehaald(quiz, vraagAntwoordParen);
    }

    public QuizResult(User student, Quiz quiz, LocalDateTime datum) {
        this(student, quiz, datum, new ArrayList<QuestionAnswerPair>());
    }

    // De quiz is behaald als het aantal juist antwoorden minstens zo groot is als de succesdefinitie
    private Boolean bepaalBehaald(Quiz quiz, List<QuestionAnswerPair> vraagAntwoordParen) {
        int aantalJuisteAntwoorden = 0;
        for (QuestionAnswerPair vraagAntwoordPaar : vraagAntwoordParen) {
            // Controleren of het gegeven antwoord juist is
            if (vraagAntwoordPaar.getVraag().getJuistAntwoord().equals(vraagAntwoordPaar.getAntwoord())) {
                aantalJuisteAntwoorden++;
            }
        }
        return aantalJuisteAntwoorden >= quiz.getSuccesDefinitie();
    }

    public User getStudent() {
        return student;
    }

    public Quiz getQuiz() {
        return quiz;
    }
    public void setVraagAntwoordParen(List<QuestionAnswerPair> vraagAntwoordParen) {
        this.vraagAntwoordParen = vraagAntwoordParen;
    }

    public Boolean getBehaald() {
        return behaald;
    }
    public void setBehaald() {
        this.behaald = bepaalBehaald(quiz, vraagAntwoordParen);
    }

    @Override
    public String toString() {
        String oordeel;
        if (behaald) { oordeel = "gehaald"; }
        else { oordeel = "niet gehaald"; }
        return "Het resultaat van quiz " + quiz.getQuizNaam() + " op "
                + datum.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " was: " + oordeel;
    }
}
