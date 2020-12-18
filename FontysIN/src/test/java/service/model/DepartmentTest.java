package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentTest {


    @Test
    void NewDepartmentTest(){

        Department e = new Department( 1, "ICT", "Good" );

        assertEquals(1, e.getId());
        assertEquals("ICT", e.getName());
        assertEquals("Good", e.getDescription());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void getUserFirstNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Department d= new Department();
        d.setId(0);
        d.setName(null);
        d.setDescription(null);

    }
}
