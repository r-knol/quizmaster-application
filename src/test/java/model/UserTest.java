package model;

import database.mysql.UserDAO;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;
import view.Main;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wendy Ellens, Richard Knol
 * Unit testen voor klasse User
 */

class UserTest {

    String voornaam = "Voornaam";
    String achternaam = "Achternaam";
    User user = new User("student", voornaam, "", achternaam);
    UserDAO userDAO = new UserDAO(Main.getDBaccess());
    int wachtwoordLengte = 5;
    User expected = new User("Student", "Menno", "de", "Bruin");

    // Test methode User.genereerGebruikersnaam()
    @Test
    void genereerGebruikersnaam() { // Nieuwe gebruiker
        String gebruikersnaam = User.genereerGebruikersnaam(voornaam, achternaam);
        assertEquals("VAchternaam", gebruikersnaam);
    }
    @Test
    void genereerGebruikersnaam2() { // Gebruikersnaam bestaat al
        userDAO.storeOne(user);
        String gebruikersnaam = User.genereerGebruikersnaam(voornaam, achternaam);
        assertEquals("VAchternaam1", gebruikersnaam);
        userDAO.deleteOne(user);
    }
    @Test
    void genereerGebruikersnaam3() { // Gebruikersnaam met suffix = 1 bestaat ook al
        userDAO.storeOne(user);
        User user2 = new User("student", voornaam, "", achternaam);
        userDAO.storeOne(user2);
        String gebruikersnaam = User.genereerGebruikersnaam(voornaam, achternaam);
        assertEquals("VAchternaam2", gebruikersnaam);
        userDAO.deleteOne(user);
        userDAO.deleteOne(user2);
    }

    // Test methode User.genereerWachtwoord()
    @Test
    void genereerWachtwoord() { // Test lengte
        String wachtwoord = User.genereerWachtwoord(wachtwoordLengte);
        assertEquals(wachtwoordLengte, wachtwoord.length());
    }
    @Test
    void genereerWachtwoord2() { // Test karakters
        String tekens = "a2!";
        String wachtwoord = User.genereerWachtwoord(wachtwoordLengte, tekens);
        assertTrue(StringUtils.containsOnly(wachtwoord, tekens));
    }
    @Test
    void genereerWachtwoord3() { // Test volgende wachtwoord verschillend
        String wachtwoord1 = User.genereerWachtwoord(wachtwoordLengte);
        String wachtwoord2 = User.genereerWachtwoord(wachtwoordLengte);
        assertNotEquals(wachtwoord1, wachtwoord2);
    }

    @Test
    void getVoornaam() {
        assertEquals(expected.getVoornaam(), "Menno");
    }

    @Test
    void getAchternaam() {
        assertEquals(expected.getAchternaam(), "Bruin");
    }

    @Test
    void testToString() {
    }
}