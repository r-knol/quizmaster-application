package model;

/**
 * @author Wendy Ellens
 * Om de vraag, de antwoorden en het gekozen antwoord op te slaan
 */

public class QuestionAnswerPair {
    private Question vraag;
    private String antwoord;

    public QuestionAnswerPair(Question vraag, String antwoord) {
        super();
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
