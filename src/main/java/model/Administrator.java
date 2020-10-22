package model;

/**
 * @author Wendy Ellens
 */
public class Administrator extends User {

    private final static String rolNaam = "Administrator";

    public Administrator(String naam, String wachtwoord) {
        super(rolNaam, naam, wachtwoord);
    }

}
