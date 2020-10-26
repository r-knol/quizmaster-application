package model;

/**
 * @author Richard Knol
 */

public class User {

    private static int aantalUsers = 0;
    private int gebruikerID;
    private String rolNaam;
    private String gebruikersNaam;
    private String voornaam;
    private String achternaam;
    private String wachtwoord;

    public User(String rolNaam, String gebruikersNaam, String wachtwoord) {
        this.gebruikerID = ++aantalUsers;
        this.rolNaam = rolNaam;
        this.gebruikersNaam = gebruikersNaam;
        this.wachtwoord = wachtwoord;
    }

    public User(int gebruikerID, String rolNaam, String gebruikersNaam, String wachtwoord) {
        this.gebruikerID = gebruikerID;
        this.rolNaam = rolNaam;
        this.gebruikersNaam = gebruikersNaam;
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
        return gebruikersNaam;
    }

    public void setNaam(String naam) {
        this.gebruikersNaam = naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

}