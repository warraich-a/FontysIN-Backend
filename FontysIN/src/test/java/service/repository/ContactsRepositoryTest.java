package service.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.User;
import service.model.UserType;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ContactsRepositoryTest {

    @InjectMocks
    ContactsRepository contactsRepository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, FileNotFoundException, URISyntaxException, DatabaseException {
        Class.forName ("org.h2.Driver");

        when(jdbcRepository.getDatabaseConnection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test") // test is the name of the folder inside db
        );

        JDBCRepositoryTest.generateData();
    }

    @AfterEach
    public void tearDown() throws Exception {
        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:shutdown.sql", UTF8, false);
    }


    @Test
    public void getAllContactsDTO() throws DatabaseException, URISyntaxException {
        List<ContactDTO> expectedContacts = Arrays.asList(
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "assets/anas image"), true),
                new ContactDTO(2, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "assets/bea image"), true),
                new ContactDTO(3, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(4, 4, "Ranim", "Alayoubi", "assets/ranim image"), false),
                new ContactDTO(4, new UserDTO(5, 0, "Denys", "Sytnyk", "assets/denys image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), false)
        );

        List<ContactDTO> actualContacts = contactsRepository.getAllContactsDTO(1);

        assertEquals(expectedContacts.size(), actualContacts.size());
        assertArrayEquals(expectedContacts.toArray(), actualContacts.toArray());
    }


    @Test
    public void getAcceptedContactsDTO() throws DatabaseException, URISyntaxException {
        List<ContactDTO> expectedContacts = Arrays.asList(
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "assets/anas image"), true),
                new ContactDTO(2, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "assets/bea image"), true)
        );

        List<ContactDTO> actualContacts = contactsRepository.getAcceptedContactsDTO(1);

        assertEquals(expectedContacts.size(), actualContacts.size());
        assertArrayEquals(expectedContacts.toArray(), actualContacts.toArray());
    }


    @Test
    public void getContactsRequestsDTO() throws DatabaseException, URISyntaxException {
        List<ContactDTO> expectedContacts = Arrays.asList(
                new ContactDTO(4, new UserDTO(5, 0, "Denys", "Sytnyk", "assets/denys image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), false)
        );

        List<ContactDTO> actualContacts = contactsRepository.getContactsRequestsDTO(1);

        assertEquals(expectedContacts.size(), actualContacts.size());
        assertArrayEquals(expectedContacts.toArray(), actualContacts.toArray());
    }


    @Test
    public void createContact() throws DatabaseException, URISyntaxException {
        ContactDTO newContact = new ContactDTO(7,
                new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"),
                new UserDTO(6, 0, "Kalina", "Petrova", "assets/kalina image"), false);

        int contactId = contactsRepository.createContact(newContact);

        assertEquals(6, contactId);
    }


    @Test
    public void createContact_alreadyExists_throwsException() {
        ContactDTO sameContact = new ContactDTO(1,
                new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"),
                new UserDTO(2, 2, "Anas", "Ahmad", "assets/anas image"), true);

        assertThrows(DatabaseException.class, () -> {
            contactsRepository.createContact(sameContact);
        });
    }


    @Test
    public void deleteContact() throws DatabaseException, URISyntaxException {
        boolean result = contactsRepository.deleteContact(1, 1);

        assertTrue(result);
    }


    @Test
    public void deleteContact_doesNotExist_returnsFalse() throws DatabaseException, URISyntaxException {
        boolean result = contactsRepository.deleteContact(9, 9);

        assertFalse(result);
    }


    @Test
    public void updateContact() throws DatabaseException, URISyntaxException {
        ContactDTO updatedContact = new ContactDTO(3,
                new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"),
                new UserDTO(4, 4, "Ranim", "Alayoubi", "assets/ranim image"), true);

        boolean result = contactsRepository.updateContact(1, updatedContact);

        assertTrue(result);
    }


    @Test
    public void updateContact_doesNotExist_returnsFalse() throws DatabaseException, URISyntaxException {
        ContactDTO updatedContact = new ContactDTO(8,
                new UserDTO(7, 7, "user", "user lastname", "assets/user image"),
                new UserDTO(8, 8, "friend", "friend lastname", "assets/friend image"), true);

        boolean result = contactsRepository.updateContact(8, updatedContact);

        assertFalse(result);
    }


    @Test
    public void getUserDTO() throws DatabaseException, URISyntaxException {
        UserDTO expectedUser = new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image");

        UserDTO actualUser = contactsRepository.getUserDTO(1);

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void getUserDTO_invalidId_throwsException() {
        assertThrows(DatabaseException.class, () -> {
            contactsRepository.getUserDTO(8);
        });
    }


    @Test
    public void getUser() throws DatabaseException, URISyntaxException {
        User expectedUser = new User(1, "Rawan", "Abou Dehn", UserType.Student, "rawan@student.fontys.nl", "1234", 1, 1, "123456", "assets/rawan image");

        User actualUser = contactsRepository.getUser(1);

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void getUser_invalidId_throwsException() {
        assertThrows(DatabaseException.class, () -> {
            contactsRepository.getUser(8);
        });
    }
}
