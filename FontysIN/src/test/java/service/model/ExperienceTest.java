package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExperienceTest {


    @Test
    void NewExperienceTest(){

        Experience e1 = new Experience( 1, "Content Writer", "Private", EmplymentType.FreeLancer, "Islamabad, Pakistan",2016, 2020, "I had a pretty good time there" );

        assertEquals(1, e1.getProfileId());
        assertEquals("Content Writer", e1.getTitle());
        assertEquals("Private", e1.getCompany());
        assertEquals(EmplymentType.FreeLancer, e1.getEmploymentType());
        assertEquals("Islamabad, Pakistan", e1.getLocation());
        assertEquals(2016, e1.getStartDateExperience());
        assertEquals(2020, e1.getEndDateExperience());
        assertEquals("I had a pretty good time there", e1.getDescriptionExperience());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Experience e= new Experience();
        e.setTitle(null);
        e.setCompany(null);
        e.setEmploymentType(null);
        e.setLocation(null);
    }


}
