package service;
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
public class PrivacyTest {

    @InjectMocks
    PrivacyController controller;
    @InjectMocks
    ProfileController profileController;

    @Mock
    PrivacyRepository repository;

    @InjectMocks
    ContactController contactController;

    @Mock
    ContactsRepository contactsRepository;
    @Mock
    JDBCProfileRepository profileRepository;


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
        User user1 = new User(3, "Rawan", "Abou Dehn", UserType.Student, "sdfsdf", "1234", 2, 2, "734695", "noImage");
        User user2 = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        lenient().when(profileController.getUser(1)).thenReturn(user1);
        lenient().when(profileController.getUser(3)).thenReturn(user2);
        lenient().when(contactsRepository.getAcceptedContactsDTO(3)).thenReturn(
                Arrays.asList(
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true)
                )
        );

        lenient().when(repository.getPrivacyList()).thenReturn(
                Arrays.asList(
                        new Privacy(1),  new Privacy(1,3, Privacy.Setting.CONNECTIONS, Privacy.Setting.CONNECTIONS, Privacy.Setting.CONNECTIONS, false),new Privacy(2),new Privacy(1)
                )
        );
//
//        boolean expected =  controller.AllowedToSee(3,1, PrivacyController.ProfilePart.EDUCATION);
//
//        assertEquals(true, expected);
        User actualUser = profileController.getUser(3);

        assertEquals(user2, actualUser);
        //K -__----------------
    }
}
