package model;

/**
 * @author Olaf van der Kaaij
 */

public class Quiz {

    private int quizID;
    private Course course;
    private String quizNaam;
    private int succesDefinitie;

    public Quiz (String quizNaam, int succesDefinitie) {
        this(0, new Course(), quizNaam, succesDefinitie);

    }

    public Quiz(int quizID, Course course, String quizNaam, int succesDefinitie) {
        this.quizID = quizID;
        this.course = course;
        this.quizNaam = quizNaam;
        this.succesDefinitie = succesDefinitie;
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

    public void setCursusID(Course course) {
        this.course = course;
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


    public String toString() {
        return quizNaam;
    }
}

