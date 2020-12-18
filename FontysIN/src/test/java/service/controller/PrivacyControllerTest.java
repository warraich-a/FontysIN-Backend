package service.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.controller.ContactController;
import service.controller.PrivacyController;
import service.controller.ProfileController;
import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;
import service.repository.*;
import service.repository.DatabaseException;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrivacyControllerTest {

    @InjectMocks
    PrivacyController controller;
    @Mock
    ProfileController profileController;

    @Mock
    PrivacyRepository repository;

    @Mock
    ContactController contactController;

  
    @Test
   public void GetEducationSettingOfUser() throws DatabaseException, URISyntaxException {
        User user5 = new User(5, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        when(repository.getPrivacyByUser(user5)).thenReturn(new Privacy(5));
        Privacy actual = new Privacy(5);
      Privacy expected =  controller.getPrivacy(user5);

        assertEquals(actual.getEducationSetting(), expected.getEducationSetting());

    }
    @Test
    public void GetExperienceSettingOfUser() throws DatabaseException, URISyntaxException {
        User user5 = new User(5, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        when(repository.getPrivacyByUser(user5)).thenReturn(new Privacy(5));
        Privacy actual = new Privacy(5);
        Privacy expected =  controller.getPrivacy(user5);

        assertEquals(actual.getExperienceSetting(), expected.getExperienceSetting());

    }
    @Test
    public void GetHideSettingOfUser() throws DatabaseException, URISyntaxException {
        User user5 = new User(5, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        when(repository.getPrivacyByUser(user5)).thenReturn(new Privacy(5));
        Privacy actual = new Privacy(5);
        Privacy expected =  controller.getPrivacy(user5);

        assertEquals(actual.getHideFromSearch(), expected.getHideFromSearch());

    }
    @Test
    public void UpdateAPrivacy() throws DatabaseException, URISyntaxException {
        Privacy p = new Privacy(8);
        when(repository.updatePrivacy(p)).thenReturn(true);

        p.setEducationSetting(Privacy.Setting.ONLYME);
        boolean expected =  controller.updatePri(p);

        assertEquals(true, expected);

    }

    @Test
    public void AllowedToSeeEducation() throws DatabaseException, URISyntaxException, SQLException {
        User user3 = new User(3, "Rawan", "Abou Dehn", UserType.Student, "sdfsdf", "1234", 2, 2, "734695", "noImage");
        User user1 = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        Privacy pForUser3 = new Privacy(3);
        Privacy pForUser1 =  new Privacy(2,1, Privacy.Setting.CONNECTIONS, Privacy.Setting.CONNECTIONS, Privacy.Setting.CONNECTIONS, false);

     lenient().when(profileController.getUser(1)).thenReturn(user1);
        lenient().when(profileController.getUser(3)).thenReturn(user3);
        lenient().when(contactController.getAcceptedContactsDTO(1)).thenReturn(
                Arrays.asList(
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true)
                )
        );

        when(repository.getPrivacyList()).thenReturn(
                Arrays.asList(
                      pForUser3, pForUser1
                )
        );

        boolean expected =  controller.AllowedToSee(1,3, PrivacyController.ProfilePart.EDUCATION);

        assertEquals(true, expected);


    }
    @Test
    public void AllowedToSeeOnlyMe() throws DatabaseException, URISyntaxException, SQLException {
        User user3 = new User(3, "Rawan", "Abou Dehn", UserType.Student, "sdfsdf", "1234", 2, 2, "734695", "noImage");
        User user1 = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        Privacy pForUser3 = new Privacy(3);
        Privacy pForUser1 =  new Privacy(2,1, Privacy.Setting.ONLYME, Privacy.Setting.ONLYME, Privacy.Setting.ONLYME, false);


        lenient().when(profileController.getUser(1)).thenReturn(user1);
        lenient().when(profileController.getUser(3)).thenReturn(user3);
        lenient().when(contactController.getAcceptedContactsDTO(1)).thenReturn(
                Arrays.asList(
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true)
                )
        );
        when(repository.getPrivacyList()).thenReturn(
                Arrays.asList(
                        pForUser3, pForUser1
                )
        );

        boolean expected =  controller.AllowedToSee(1,3, PrivacyController.ProfilePart.EDUCATION);

        assertEquals(false, expected);

    }
    @Test
    public void AllowedToSeeEveryone() throws DatabaseException, URISyntaxException, SQLException {
        User user3 = new User(3, "Rawan", "Abou Dehn", UserType.Student, "sdfsdf", "1234", 2, 2, "734695", "noImage");
        User user1 = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        Privacy pForUser3 = new Privacy(3);
        Privacy pForUser1 =  new Privacy(1);


        lenient().when(profileController.getUser(1)).thenReturn(user1);
        lenient().when(profileController.getUser(3)).thenReturn(user3);
        lenient().when(contactController.getAcceptedContactsDTO(1)).thenReturn(
                Arrays.asList(
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true)
                )
        );
        when(repository.getPrivacyList()).thenReturn(
                Arrays.asList(
                        pForUser3, pForUser1
                )
        );

        boolean expected =  controller.AllowedToSee(1,3, PrivacyController.ProfilePart.EDUCATION);

        assertEquals(true, expected);

    }
}
