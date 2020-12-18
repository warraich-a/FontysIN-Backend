package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrivacyTest {
    @Test
    void NewPrivacyTest(){

    Privacy p = new Privacy(1);

        assertEquals(1, p.getUserId());



    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Privacy e= new Privacy();
        e.setId(0);
        e.setEducationSetting(null);
        e.setUserId(0);
        e.setSkillSetting(null);
    }
}
