package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AboutTest {

    @Test
    void NewAboutTest(){

        About a = new About(1, "I am software engineer");

        assertEquals(1, a.getProfileId());
        assertEquals("I am software engineer", a.getContent());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        About a= new About();
        a.setProfileId(0);
        a.setContent(null);

    }
}
