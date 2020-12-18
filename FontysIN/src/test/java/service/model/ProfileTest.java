package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileTest {
    @Test
    void NewProfileTest(){

        Profile p = new Profile(1, "English");
        assertEquals(1, p.getUserId());
        assertEquals("English", p.getLanguage());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Profile p= new Profile();
        p.setUserId(0);
        p.setLanguage(null);

    }
}
