package service.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.*;
import service.repository.DatabaseException;
import service.repository.ProfileRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    ProfileRepository repository;
    @InjectMocks
    ProfileController profileController;

    @Test
    public void getUserByEmail() throws DatabaseException, URISyntaxException, SQLException {
        User user5 = new User(1, "Beatrice", "Forslund", UserType.Student, "Bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        when(repository.getUsers()).thenReturn(
                Arrays.asList(
                        new User(1, "Beatrice", "Forslund", UserType.Student, "Bea@fontys.com", "1234", 2, 2, "734695", "noImage"),
                        new User(2, "Rawan", "Abou Dehn", UserType.Student, "Rawan@fontys.com", "1234", 2, 2, "734695", "noImage"),
                        new User(3, "Anas", "Ahmad", UserType.Student, "Anas@fontys.com", "1234", 2, 2, "734695", "noImage")
                )
        );


        User expected =  userController.getUserByEmail("Bea@fontys.com");

        assertEquals(user5, expected);

    }
    @Test
    public void loginAndHashingTest() throws DatabaseException, URISyntaxException, SQLException {
        String password = userController.doHashing("1234");
        User user5 = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", password, 2, 2, "734695", "noImage");
        when(repository.getUsers()).thenReturn(
                Arrays.asList(
                        user5,
                        new User(2, "Rawan", "Abou Dehn", UserType.Student, "Rawan@fontys.com", "1234", 2, 2, "734695", "noImage"),
                        new User(3, "Anas", "Ahmad", UserType.Student, "Anas@fontys.com", "1234", 2, 2, "734695", "noImage")
                )
        );


        Boolean expected =  userController.login("bea@fontys.com", "1234");

        assertEquals(true, expected);

    }
    @Test
    public void getUserFromToken() throws DatabaseException, URISyntaxException, SQLException {

        User user5 = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        when(repository.getUserById(1)).thenReturn(user5);
        String userId = Integer.toString(user5.getId());
       String token = userController.createJWT(userId, user5.getFirstName(), user5.getLastName(), -1);

        User expected =  userController.getUserFromToken(token);

        assertEquals(user5, expected);

    }


    /// I will move these once Anas created the class for profile
    @Test
    public void updateEducation() throws DatabaseException, URISyntaxException, SQLException {

        Education e = new Education(1,1,"school",2019,2020,"ICT", "software", "I am the best at unit testing");
        when(repository.updateEducation(e)).thenReturn(true);

        e.setSchool("Fontys");
        boolean expected =  profileController.updateEdu(e);

        assertEquals(true, expected);
    }
    @Test
    public void updateExperience() throws DatabaseException, URISyntaxException, SQLException {

        Experience e = new Experience(1,1,"title","Ikea", EmplymentType.FullTime,"Eindhoven", 2019,2020, "Good food");
        when(repository.updateExperience(e)).thenReturn(true);

        e.setTitle("Ikea");
        boolean expected =  profileController.updateExp(e);

        assertEquals(true, expected);
    }
    @Test
    public void updateAbout() throws DatabaseException, URISyntaxException, SQLException {

        About a = new About(1,1,"Hello");
        when(repository.updateAbout(a)).thenReturn(true);

        a.setContent("I like unit testing (Not)");
        boolean expected =  profileController.updateAbo(a);

        assertEquals(true, expected);
    }

}
