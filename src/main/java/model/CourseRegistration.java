package model;

/**
 * @author Olaf van der Kaaij
 */

public class CourseRegistration {

    private User gebruikersID;
    private Course cursusID;

    public CourseRegistration(Course cursusID, User gebruikersID) {
        this.cursusID = cursusID;
        this.gebruikersID = gebruikersID;
    }
}
