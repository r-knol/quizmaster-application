package model;

import database.mysql.UserDAO;
import view.Main;

import java.util.Collections;

/**
 * @author Richard Knol, Wendy Ellens
 */

public class User {

    private final static int WACHTWOORD_LENGTE = 6;

    private int gebruikerID;
    private String rol;
    private String gebruikersnaam;
    private String wachtwoord;
    private String voornaam;
    private String tussenvoegsels;
    private String achternaam;

    public User(int gebruikerID, String rol, String gebruikersnaam, String wachtwoord,
                String voornaam, String tussenvoegsels, String achternaam) {
        this.gebruikerID = gebruikerID;
        this.rol = rol;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.tussenvoegsels = tussenvoegsels;
        this.achternaam = achternaam;
    }

    // GebruikerID, gebruikersnaam en wachtwoord worden automatisch gegenereerd.
    public User(String rol, String voornaam, String tussenvoegsels, String achternaam) {
        this(0, rol, "", "", voornaam, tussenvoegsels, achternaam);
        // TODO: testen. Zal worden gedaan in de CreateUpdateUserController en als Unittest.
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        int gebruikersnaam_suffix = 1;

        // Gebruikersnaam genereren o.b.v. eerste letter voornaam en achternaam
        gebruikersnaam = voornaam.charAt(0) + achternaam;

        // Suffix 1 toevoegen als de gebruikersnaam al bestaat
        if (!(userDAO.getOneByUsername(gebruikersnaam) == null)) {
            gebruikersnaam += gebruikersnaam_suffix;
        }

        // Suffix verhogen zolang als nodig is om een nieuwe gebruikersnaam te creeren
        while (!(userDAO.getOneByUsername(gebruikersnaam) == null)) {
            gebruikersnaam_suffix++; // Suffix ophogen
            gebruikersnaam = gebruikersnaam.substring(0, gebruikersnaam.length() - 1); // Oude suffix verwijderen
            gebruikersnaam += gebruikersnaam_suffix; // Nieuwe suffix toevoegen
        }
        wachtwoord = genereerWachtwoord(WACHTWOORD_LENGTE);
    }

    public static String genereerWachtwoord(int lengte, String tekens) {
        StringBuilder wachtwoord = new StringBuilder();

        // Een willekeurig karakter kiezen uit karakters en dit zo vaak herhalen als het wachtwoord lang moet zijn
        for (int i = 0; i < lengte; i++) {
            int randomIndex = (int) (Math.random() * tekens.length());
            wachtwoord.append(tekens.charAt(randomIndex));
        }
        return wachtwoord.toString();
    }

    // TODO: testen. Zal worden gedaan in de CreateUpdateUserController en als Unittest.
    public static String genereerWachtwoord(int lengte) {
        final String tekens
                = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return genereerWachtwoord(lengte, tekens);
    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
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

    public String getTussenvoegsels() {
        return tussenvoegsels;
    }

    public void setTussenvoegsels(String tussenvoegsels) {
        this.tussenvoegsels = tussenvoegsels;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    @Override
    public String toString() {
        return achternaam + ", " + voornaam + ", " + rol;
    }
}