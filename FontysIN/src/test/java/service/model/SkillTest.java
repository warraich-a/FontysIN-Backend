package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillTest {

    @Test
    void NewSkillTest(){

        Skill s = new Skill(1, "HTML");

        assertEquals(1, s.getProfileId());
        assertEquals("HTML", s.getName());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Skill s= new Skill();
        s.setProfileId(0);
        s.setName(null);

    }
}
