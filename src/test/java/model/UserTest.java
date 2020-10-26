package model;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User test = new User(2, "administrator", "leeg", "leeg");

    @org.junit.jupiter.api.Test
    void getGebruikerID() {
    }

    @org.junit.jupiter.api.Test
    void setGebruikerID() {
    }

    @org.junit.jupiter.api.Test
    void getRolNaam() {
        assertEquals(test.getRolNaam(), "administrator");
    }

    @org.junit.jupiter.api.Test
    void setRolNaam() {
    }

    @org.junit.jupiter.api.Test
    void getNaam() {
    }

    @org.junit.jupiter.api.Test
    void setNaam() {
    }

    @org.junit.jupiter.api.Test
    void getWachtwoord() {
    }

    @org.junit.jupiter.api.Test
    void setWachtwoord() {
    }
}