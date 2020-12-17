package Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import service.model.*;

public class UserTest {

    @Test //check user type
    void GetUserType(){
        Education education = new Education(1, "Fontys", 2019, 2023, "Bachelor", "ICT", "Info");
        Experience experience = new Experience(1, "Teacher Assistance", "Fontys", EmplymentType.FullTime, "Fontys Rachelsmolen 189", 2011, 2023, "Info");

        User u1 = new User(1, "Ranim", "Alayoubi", UserType.Student , "ranim@gmail.com", "password199", 1, 1, "433335", education);
        User u2 = new User(2, "Adnan", "Adnan", UserType.Teacher , "adnan@gmail.com", "adnan1998", 2, 3, "355556", education);

        Profile p = new Profile(1,2,"English");

        assertEquals(UserType.Student, u1.getUserType());
        assertEquals(UserType.Teacher, u2.getUserType());

        assertEquals(p.getUserId(), u2.getId());
        assertEquals(p.getId(), experience.getProfileId());

    }

    @Test //check user education start year education
    void GetUserstartYearEducation(){
        Education education = new Education(1, "Fontys", 2019, 2023, "Bachelor", "ICT", "Info");

        User u = new User(1, "Ranim", "Alayoubi", UserType.Student , "ranim@gmail.com", "password199", 1, 1, "433335", education);

        assertEquals("2019", u.getEducation().getStartYearEducation());
    }

    @Test //check user location
    void GetUserLocation(){
        Education education = new Education(1, "Fontys", 2019, 2023, "Bachelor", "ICT", "Info");

        User u = new User(1, "Ranim", "Alayoubi", UserType.Student , "ranim@gmail.com", "password199", 1, 1, "433335", education);

        assertEquals("1", u.getLocationId());
    }

    @Test //check user department
    void GetUserDepartment(){
        Education education = new Education(1, "Fontys", 2019, 2023, "Bachelor", "ICT", "Info");

        User u = new User(1, "Ranim", "Alayoubi", UserType.Student , "ranim@gmail.com", "password199", 1, 1, "433335", education);

        assertEquals("1", u.getDepartmentId());
    }

    @Test //check user type, education start year education, location and department
    void GetUserTypeEducationLocationAndDepartment(){
        Education education = new Education(1, "Fontys", 2019, 2023, "Bachelor", "ICT", "Info");

        User u = new User(1, "Ranim", "Alayoubi", UserType.Student , "ranim@gmail.com", "password199", 1, 1, "433335", education);

        assertEquals(UserType.Student, u.getUserType());
        assertEquals("2019", u.getEducation().getStartYearEducation());
        assertEquals("1", u.getLocationId());
        assertEquals("1", u.getDepartmentId());
    }
}

