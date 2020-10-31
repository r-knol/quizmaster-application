package model;

/**
 * @author Wendy Ellens
 */

public class Course {

    private int cursusID;
    private String cursusNaam;
    private User coordinator;

    public Course(int cursusID, String cursusNaam, User coordinator) {
        this.cursusID = cursusID;
        this.cursusNaam = cursusNaam;
        this.coordinator = coordinator;
    }

    public Course(String cursusNaam, User coordinator) {
        this(0, cursusNaam, coordinator);
    }

    public Course () {
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

    public User getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(User coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public String toString() {
        return cursusNaam;
    }

}
