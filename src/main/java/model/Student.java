package model;
// Peter van den Bol
public class Student extends User {

    int gebruikerID;
    String rolNaam;
    String naam;
    String wachtwoord;

    public Student(int gebruikerID, String rolNaam, String naam, String wachtwoord){
        this.gebruikerID = gebruikerID;
        this.rolNaam = rolNaam;
        this.naam = naam;
        this.wachtwoord = wachtwoord;
    }
    public Student(String naam, String wachtwoord){
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

    public String getNaam() {
        return naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

}//class
