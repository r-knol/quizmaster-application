package model;

/**
 * @author Richard Knol
 */
public class User {
    private static int aantalUsers = 0;
    private int gebruikerID;
    private String rolNaam;
    private String naam;
    private String wachtwoord;
    public User(String rolNaam, String naam, String wachtwoord) {
        this.gebruikerID = ++aantalUsers;
        this.rolNaam = rolNaam;
        this.naam = naam;
        this.wachtwoord = wachtwoord;
    }
    public User(int gebruikerID, String rolNaam, String naam, String wachtwoord) {
        this.gebruikerID = gebruikerID;
        this.rolNaam = rolNaam;
        this.naam = naam;
        this.wachtwoord = wachtwoord;
    }
    public int getGebruikerID() {
        return gebruikerID;
    }
    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
    }
    public String getRolNaam() {
        return rolNaam;
    }
    public void setRolNaam(String rolNaam) {
        this.rolNaam = rolNaam;
    }
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}