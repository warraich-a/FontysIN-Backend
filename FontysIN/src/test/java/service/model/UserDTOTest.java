package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOTest {

    @Test //check whether user info are correct
    void NewUserTest()
    {
        UserDTO u = new UserDTO(1, 1, "Ranim", "Alayoubi", "image.png");

        assertEquals(1, u.getId());
        assertEquals(1, u.getProfileId());
        assertEquals("Ranim", u.getFirstName());
        assertEquals("Alayoubi", u.getLastName());
        assertEquals("image.png", u.getImage());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void getUserFirstNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be null");

        UserDTO u= new UserDTO();
        u.setFirstName(null);
    }

    @Test // user first name string is empty
    void getUserFirstNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be empty");

        UserDTO u= new UserDTO();
        u.setFirstName(" ");
    }

    @Test // user last name null
    void getUserLastNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be null");

        UserDTO u= new UserDTO();
        u.setLastName(null);
    }

    @Test // user last name string is empty
    void getUserLastNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be empty");

        UserDTO u= new UserDTO();
        u.setLastName(" ");
    }
}
