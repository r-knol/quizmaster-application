package model;

/**
 * @author Olaf van der Kaaij
 */

public class Quiz {

    private int quizID;
    private int cursusID;
    private String quizNaam;
    private int succesDefinitie;

    public Quiz (int cursusID, String quizNaam, int succesDefinitie) {
        this(0, cursusID, quizNaam, succesDefinitie);
    }

    public Quiz (int quizID, int cursusID, String quizNaam, int succesDefinitie) {
        this.quizID = quizID;
        this.cursusID = cursusID;
        this.quizNaam = quizNaam;
        this.succesDefinitie = succesDefinitie;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getCursusID() {
        return cursusID;
    }

    public void setCursusID(int cursusID) {
        this.cursusID = cursusID;
    }

    public String getQuizNaam() {
        return quizNaam;
    }

    public void setQuizNaam(String quizNaam) {
        this.quizNaam = quizNaam;
    }

    public int getSuccesDefinitie() {
        return succesDefinitie;
    }

    public void setSuccesDefinitie(int succesDefinitie) {
        this.succesDefinitie = succesDefinitie;
    }
}

