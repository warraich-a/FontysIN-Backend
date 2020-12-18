package service.model;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test //check whether user info are correct
    void NewUserTest()
    {
        Education education = new Education(1, "Fontys", 2019, 2023, "Bachelor", "ICT", "Info");

        User u = new User(1, "Ranim", "Alayoubi", UserType.Student , "ranim@gmail.com", "password199", 1, 1, "433335", education);

        assertEquals(1, u.getId());
        assertEquals("Ranim", u.getFirstName());
        assertEquals("Alayoubi", u.getLastName());
        assertEquals(UserType.Student, u.getUserType());
        assertEquals("ranim@gmail.com", u.getEmail());
        assertEquals("password199", u.getPassword());
        assertEquals(1, u.getLocationId());
        assertEquals(1, u.getDepartmentId());
        assertEquals("433335", u.getUserNumber());
        assertEquals(education, u.getEducation());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void getUserFirstNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be null");

        User u= new User();
        u.setFirstName(null);
    }

    @Test // user first name string is empty
    void getUserFirstNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be empty");

        User u= new User();
        u.setFirstName(" ");
    }

    @Test // user last name null
    void getUserLastNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be null");

        User u= new User();
        u.setLastName(null);
    }

    @Test // user last name string is empty
    void getUserLastNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be empty");

        User u= new User();
        u.setLastName(" ");
    }

    @Test // user email null
    void getUserEmailWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email must not be null");

        User u= new User();
        u.setEmail(null);
    }

    @Test // user email string is empty
    void getUserEmailWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email must not be empty");

        User u= new User();
        u.setEmail(" ");
    }

    @Test // user password null
    void getUserPasswordWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password must not be null");

        User u= new User();
        u.setPassword(null);
    }

    @Test // user password string is empty
    void getUserPasswordWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password must not be empty");

        User u= new User();
        u.setPassword(" ");
    }

    @Test // user type null
    void getUserTypeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User type must not be null");

        User u= new User();
        u.setUserType(null);
    }

    @Test // user Education null
    void getUserEducationWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User education must not be null");
        User u= new User();
        u.setEducation(null);
    }
}
