package database.mysql;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final User expected = new User("student", "Peter", "",
            "Vroon");

    @org.junit.jupiter.api.Test
    void getOneByUsername() {
        assertEquals(expected.getGebruikersnaam(), "PVroon");
    }

}