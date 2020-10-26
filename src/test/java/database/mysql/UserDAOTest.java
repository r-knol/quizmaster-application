package database.mysql;

import model.User;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final User actual = new User(1, "student", "rwknol", "leeg");

    @org.junit.jupiter.api.Test
    void getAll() {
    }

    @org.junit.jupiter.api.Test
    void getOneById() {
    }

    @org.junit.jupiter.api.Test
    void getOneByNameAndPassword() {
        assertEquals(actual.getNaam(), "rwknol");
        assertEquals(actual.getRolNaam(), "student");
    }

    @org.junit.jupiter.api.Test
    void storeOne() {
    }
}