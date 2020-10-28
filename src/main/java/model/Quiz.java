package model;

/**
 * @author Olaf van der Kaaij
 */

public class Quiz {

    private int quizID;
    private Course cursusID;
    private int succesDefinitie;

    public Quiz (int quizID, Course cursusID, int succesDefinitie) {
        this.quizID = quizID;
        this.cursusID = cursusID;
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

    public int getSuccesDefinitie() {
        return succesDefinitie;
    }

    public void setSuccesDefinitie(int succesDefinitie) {
        this.succesDefinitie = succesDefinitie;
    }
}

