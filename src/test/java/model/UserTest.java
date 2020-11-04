package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User expected = new User("Student", "Menno", "de", "Bruin");

    @Test
    void genereerWachtwoord() {
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