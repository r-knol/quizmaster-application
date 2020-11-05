package model;

/**
 * @author Wendy Ellens
 * Om de gegevens van een cursus in op te slaan
 */

public class Course {

    private int cursusID;
    private String cursusNaam;
    private User user;

    public Course(int cursusID, String cursusNaam, User user) {
        this.cursusID = cursusID;
        this.cursusNaam = cursusNaam;
        this.user = user;
    }

    public Course(String cursusNaam, User user) {
        this(0, cursusNaam, user);
    }

    public Course(int cursusID, User user) {
        this(cursusID, "", user);
    }

    public Course() {
        this("", null);
    }

    public int getCursusID() {
        return cursusID;
    }
    public void setCursusID(int cursusID) {
        this.cursusID = cursusID;
    }

    public String getCursusNaam() {
        return cursusNaam;
    }
    public void setCursusNaam(String cursusNaam) {
        this.cursusNaam = cursusNaam;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return cursusNaam;
    }

}
