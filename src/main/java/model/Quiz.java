package model;

/**
 * @author Olaf van der Kaaij
 */

public class Quiz {

    private static int aantalQuizzes = 0;
    private static int aantalCursussen = 0;
    private int quizID;
    private int cursusID;
    private int succesDefinitie;

    public Quiz () {
        this(++aantalQuizzes, ++ aantalCursussen, 0);
    }

    public Quiz (int quizID, int cursusID, int succesDefinitie) {
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

    public int getCursusID() {
        return cursusID;
    }

    public void setCursusID(int cursusID) {
        this.cursusID = cursusID;
    }

    public int getSuccesDefinitie() {
        return succesDefinitie;
    }

    public void setSuccesDefinitie(int succesDefinitie) {
        this.succesDefinitie = succesDefinitie;
    }
}

