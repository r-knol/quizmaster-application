package model;

/**
 * @author Olaf van der Kaaij
 */

public class Quiz {

    private int quizID;
    private Course course;
    private String quizNaam;
    private int aantalVragen;
    private int succesDefinitie;

    public Quiz(int quizID, Course course , String quizNaam, int aantalVragen, int succesDefinitie) {
        this.quizID = quizID;
        this.course = course;
        this.quizNaam = quizNaam;
        this.aantalVragen = aantalVragen;
        this.succesDefinitie = succesDefinitie;
    }

    public Quiz (Course course, String quizNaam, int aantalVragen, int succesDefinitie) {
        this(0,course, quizNaam, aantalVragen,succesDefinitie);
    }

    public Quiz() {
        this(new Course(), "", 0,0);
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse (Course course) {
        this.course = course;
    }

    public String getQuizNaam() {
        return quizNaam;
    }

    public void setQuizNaam(String quizNaam) {
        this.quizNaam = quizNaam;
    }

    public int getAantalVragen() {
        return aantalVragen;
    }

    public void setAantalVragen(int aantalVragen) {
        this.aantalVragen = aantalVragen;
    }

    public int getSuccesDefinitie() {
        return succesDefinitie;
    }

    public void setSuccesDefinitie(int succesDefinitie) {
        this.succesDefinitie = succesDefinitie;
    }

    public String toString() {
        return quizNaam;
    }
}

