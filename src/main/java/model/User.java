package model;

import database.mysql.UserDAO;
import view.Main;

/**
 * @author Richard Knol, Wendy Ellens
 */

public class User {

    private final static int WACHTWOORD_LENGTE = 6;

    private static int aantalUsers = 0;
    private int gebruikerID;
    private String rol;
    private String gebruikersnaam;
    private String wachtwoord;
    private String voornaam;
    private String tussenvoegsels;
    private String achternaam;

    // GebruikerID, gebruikersnaam en wachtwoord worden automatisch gegenereerd.
    public User(String rol, String voornaam, String tussenvoegsels, String achternaam) {
        this.gebruikerID = ++aantalUsers;
        this.rol = rol;
        this.voornaam = voornaam;
        this.tussenvoegsels = tussenvoegsels;
        this.achternaam = achternaam;

        // Gebruikersnaam genereren, indien deze al bestaat een '1' toevoegen (of '2' of '3' ... als dat nodig is).
        // TODO: testen. Zal worden gedaan in de CreateUpdateUserController.
        this.gebruikersnaam = voornaam.charAt(0) + achternaam;
        int gebruikersnaam_suffix = 1;
        UserDAO userDAO = new UserDAO(Main.getDBaccess());
        while (!(userDAO.getOneByUsername(gebruikersnaam) == null)) {
            gebruikersnaam += gebruikersnaam_suffix;
            gebruikersnaam_suffix++;
        }

        this.wachtwoord = genereerWachtwoord();
    }

    public User(int gebruikerID, String rol, String gebruikersnaam, String wachtwoord, String voornaam, String tussenvoegsels, String achternaam) {
        this.gebruikerID = gebruikerID;
        this.rol = rol;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.tussenvoegsels = tussenvoegsels;
        this.achternaam = achternaam;
    }

    public User(String voornaam) {
        this.voornaam = voornaam;
    }

    public static String genereerWachtwoord() {
        final String karakters
                = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        StringBuilder wachtwoord = new StringBuilder();

        // Een willekeurig karakter kiezen uit karakters en dit zo vaak herhalen als het wachtwoord lang moet zijn
        for (int i = 0; i < WACHTWOORD_LENGTE; i++) {
            int randomIndex = (int) (Math.random() * karakters.length());
            wachtwoord.append(karakters.charAt(randomIndex));
        }

        return wachtwoord.toString();
    }

    public int getGebruikerID() {
        return gebruikerID;
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

    public String getWachtwoord() {
        return wachtwoord;
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
        return "User{" +
                "gebruikersNaam='" + '\'' +
                '}';
    }
}