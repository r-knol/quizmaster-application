package model;

import database.mysql.UserDAO;

// Peter van den Bol
public class Student extends User {

    private String rolNaam;
    private String naam;
    private String wachtwoord;

    public Student(String rolNaam, String naam, String wachtwoord){
        this.rolNaam = rolNaam;
        this.naam = naam;
        this.wachtwoord = wachtwoord;

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

