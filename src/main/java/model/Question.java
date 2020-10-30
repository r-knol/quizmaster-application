package model;

/**
 * @author Olaf van der Kaaij
 */

public class Question {

    private int vraagID;
    private Quiz quiz;
    private String quizVraag;
    private String juistAntwoord;
    private String foutAntwoord1;
    private String foutAntwoord2;
    private String foutAntwoord3;

    public Question(){
        this(0,new Quiz(),"","","","","");
    }

    public Question (Quiz quiz, String quizVraag, String juistAntwoord,
                     String foutAntwoord1, String foutAntwoord2, String foutAntwoord3) {
        this(0, quiz, quizVraag, juistAntwoord, foutAntwoord1, foutAntwoord2, foutAntwoord3);
    }

    public Question (int vraagID, Quiz quiz, String quizVraag, String juistAntwoord,
                     String foutAntwoord1, String foutAntwoord2, String foutAntwoord3) {
        this.vraagID = vraagID;
        this.quiz = quiz;
        this.quizVraag = quizVraag;
        this.juistAntwoord = juistAntwoord;
        this.foutAntwoord1 = foutAntwoord1;
        this.foutAntwoord2 = foutAntwoord2;
        this.foutAntwoord3 = foutAntwoord3;
    }

    public int getVraagID() {
        return vraagID;
    }

    public void setVraagID(int vraagID) {
        this.vraagID = vraagID;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getQuizVraag() {
        return quizVraag;
    }

    public void setQuizVraag(String quizVraag) {
        this.quizVraag = quizVraag;
    }

    public String getJuistAntwoord() {
        return juistAntwoord;
    }

    public void setJuistAntwoord(String juistAntwoord) {
        this.juistAntwoord = juistAntwoord;
    }

    public String getFoutAntwoord1() {
        return foutAntwoord1;
    }

    public void setFoutAntwoord1(String foutAntwoord1) {
        this.foutAntwoord1 = foutAntwoord1;
    }

    public String getFoutAntwoord2() {
        return foutAntwoord2;
    }

    public void setFoutAntwoord2(String foutAntwoord2) {
        this.foutAntwoord2 = foutAntwoord2;
    }

    public String getFoutAntwoord3() {
        return foutAntwoord3;
    }

    public void setFoutAntwoord3(String foutAntwoord3) {
        this.foutAntwoord3 = foutAntwoord3;
    }



}
