package model;

/**
 * @author Wendy Ellens
 */

public class Course {

    private int cursusID;
    private String cursusNaam;
    private int coordinatorID;

    public Course(int cursusID, String cursusNaam, int coordinatorID) {
        this.cursusID = cursusID;
        this.cursusNaam = cursusNaam;
        this.coordinatorID = coordinatorID;
    }

    public Course(String cursusNaam, int coordinatorID) {
        this(0, cursusNaam, coordinatorID);
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

    public int getCoordinatorID() {
        return coordinatorID;
    }
}
