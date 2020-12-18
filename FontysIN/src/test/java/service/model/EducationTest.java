package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EducationTest {
    @Test
    void NewEducationTest(){

        Education e = new Education(1, "Fontys", 2019, 2023, "High School", "ICT", "Got good grades" );

        assertEquals(1, e.getProfileId());
        assertEquals("Fontys", e.getSchool());
        assertEquals(2019, e.getStartYearEducation());
        assertEquals(2023, e.getEndYearEducation());
        assertEquals("High School", e.getDegreeEducation());
        assertEquals("ICT", e.getFieldStudy());
        assertEquals("Got good grades", e.getDescriptionEducation());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Education e= new Education();
        e.setProfileId(0);
        e.setSchool(null);
        e.setStartYearEducation(0);
        e.setDegreeEducation(null);
    }
}
