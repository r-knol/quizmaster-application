package model;

/**
 * @author Wendy Ellens
 * Om het nummer van het gekozen antwoord op te slaan bij het nummer van de vraag
 */

public class QuestionAnswerPair {
    private Question vraag;
    private String antwoord;


    public QuestionAnswerPair(Question vraag, String antwoord) {
        this.vraag = vraag;
        this.antwoord = antwoord;
    }

    public Question getVraag() {
        return vraag;
    }
    public void setVraag(Question vraag) {
        this.vraag = vraag;
    }

    public String getAntwoord() {
        return antwoord;
    }
}
