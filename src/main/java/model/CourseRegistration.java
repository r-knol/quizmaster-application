package model;

import view.Main;

/**
 * @author Olaf van der Kaaij
 */

public class CourseRegistration {

    private Course course;
    private User gebruikersID;
    private Course cursusID;

    public CourseRegistration(Course cursusID, User gebruikersID) {
        this.cursusID = cursusID;
        this.gebruikersID = gebruikersID;
    }

    public CourseRegistration () {
        this(new Course(),Main.getUser());
    }

    public User getGebruikersID() {
        return gebruikersID;
    }

    public void setGebruikersID(User gebruikersID) {
        this.gebruikersID = gebruikersID;
    }

    public Course getCursusID() {
        return cursusID;
    }

    public void setCursusID(Course cursusID) {
        this.cursusID = cursusID;
    }
}
