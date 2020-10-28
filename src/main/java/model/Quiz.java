package model;

/**
 * @author Olaf van der Kaaij
 */

public class Quiz {

    private int quizID;
    private Course cursusID;
    private String quizNaam;
    private int succesDefinitie;

    public Quiz (int quizID, Course cursusID, String quizNaam, int succesDefinitie) {
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

    public Course getCursusID() {
        return cursusID;
    }

    public void setCursusID(Course cursusID) {
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

