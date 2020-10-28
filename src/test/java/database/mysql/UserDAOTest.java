package database.mysql;

import model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final User actual = new User("student", "rwknol", "Richard", "hoi");

    @org.junit.jupiter.api.Test
    void getAll() {
    }

    @org.junit.jupiter.api.Test
    void getOneById() {
    }

    @org.junit.jupiter.api.Test
    void getOneByNameAndPassword() {
        assertEquals(actual.getVoornaam(), "rwknol");
        assertEquals(actual.getRol(), "student");
    }

    @org.junit.jupiter.api.Test
    void storeOne() {
    }
}