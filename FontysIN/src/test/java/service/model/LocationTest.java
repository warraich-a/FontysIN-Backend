package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    void NewDepartmentTest(){

        Location l = new Location(1, "JulianStraat", "R10", "Eindhoven", "4545DF"  );

        assertEquals(1, l.getId());
        assertEquals("JulianStraat", l.getStreetName());
        assertEquals("R10", l.getBuildingNumber());
        assertEquals("Eindhoven", l.getCity());
        assertEquals("4545DF", l.getZipcode());


    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void NullValueException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("None of the fields should be null");

        Location l = new Location();
        l.setId(0);
        l.setBuildingNumber(null);
        l.setCity(null);
        l.setZipcode(null);

    }
}
