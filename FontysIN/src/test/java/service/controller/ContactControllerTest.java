package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Contact;
import service.model.User;
import service.model.UserType;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;
import service.repository.ContactsRepository;
import service.repository.DatabaseException;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {

    @InjectMocks
    ContactController contactController;

    @Mock
    ContactsRepository contactsRepository;

    @Test
    public void getAllContactsDTO() throws DatabaseException, URISyntaxException {
        List<ContactDTO> expectedContacts = Arrays.asList(
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true),
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(4, 4, "Ranim", "Alayoubi", "ranim image"), false),
                new ContactDTO(5, new UserDTO(5, 0, "Denys", "Sytnyk", "denys image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false),
                new ContactDTO(6, new UserDTO(6, 0, "Kalina", "Petrova", "kalina image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false)
        );

        when(contactsRepository.getAllContactsDTO(1)).thenReturn(
            Arrays.asList(
                    new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                    new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true),
                    new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(4, 4, "Ranim", "Alayoubi", "ranim image"), false),
                    new ContactDTO(5, new UserDTO(5, 0, "Denys", "Sytnyk", "denys image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false),
                    new ContactDTO(6, new UserDTO(6, 0, "Kalina", "Petrova", "kalina image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false)
            )
        );

        List<ContactDTO> actualContacts = contactController.getAllContactsDTO(1);

        assertEquals(expectedContacts.size(), actualContacts.size());
        assertEquals(expectedContacts, actualContacts);
    }

    @Test
    public void getAcceptedContactsDTO() throws DatabaseException, URISyntaxException {
        List<ContactDTO> expectedContacts = Arrays.asList(
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true)
        );

        when(contactsRepository.getAcceptedContactsDTO(1)).thenReturn(
                Arrays.asList(
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(2, 2, "Anas", "Ahmad", "anas image"), true),
                        new ContactDTO(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(3, 3, "Beatrice", "Forslund", "bea image"), true)
                )
        );

        List<ContactDTO> actualContacts = contactController.getAcceptedContactsDTO(1);

        assertEquals(expectedContacts, actualContacts);
    }


    @Test
    public void getContactsRequestsDTO() throws DatabaseException, URISyntaxException {
        List<ContactDTO> expectedContacts = Arrays.asList(
                new ContactDTO(5, new UserDTO(5, 0, "Denys", "Sytnyk", "denys image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false),
                new ContactDTO(6, new UserDTO(6, 0, "Kalina", "Petrova", "kalina image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false)
        );

        when(contactsRepository.getContactsRequestsDTO(1)).thenReturn(
                Arrays.asList(
                        new ContactDTO(5, new UserDTO(5, 0, "Denys", "Sytnyk", "denys image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false),
                        new ContactDTO(6, new UserDTO(6, 0, "Kalina", "Petrova", "kalina image"), new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), false)
                )
        );

        List<ContactDTO> actualContacts = contactController.getContactsRequestsDTO(1);

        assertEquals(expectedContacts.size(), actualContacts.size());
        assertEquals(expectedContacts, actualContacts);
    }

    @Test
    public void createContact() throws DatabaseException, URISyntaxException {
        ContactDTO newContact = new ContactDTO(7, new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image"), new UserDTO(6, 0, "Kalina", "Petrova", "kalina image"), false);

        when(contactsRepository.createContact(newContact)).thenReturn(7);

        int contactId = contactController.createContact(newContact);

        assertEquals(7, contactId);
    }

    @Test
    public void deleteContact() throws DatabaseException, URISyntaxException {
        when(contactsRepository.deleteContact(1, 1)).thenReturn(true);

        boolean result = contactController.deleteContact(1, 1);

        assertEquals(true, result);
    }

    @Test
    public void deleteContact_doesNotExist_returnsFalse() throws DatabaseException, URISyntaxException {
        when(contactsRepository.deleteContact(9, 9)).thenReturn(false);

        boolean result = contactController.deleteContact(9, 9);

        assertEquals(false, result);
    }

    @Test
    public void updateContact() throws DatabaseException, URISyntaxException {
        Contact updatedContact = new Contact(
                new User(1, "Rawan", "Abou Dehn", UserType.Student, "rawan@student.fontys.nl", "1234", 1, 1, "123456", "rawan image"),
                new User(4, "Ranim", "Alayoubi", UserType.Student, "ranim@student.fontys.nl", "1234567", 2, 1, "123456", "ranim image"));
        when(contactsRepository.updateContact(1, updatedContact)).thenReturn(true);

        boolean result = contactController.updateContact(1, updatedContact);

        assertEquals(true, result);
    }

    @Test
    public void getUserDTO() throws DatabaseException, URISyntaxException {
        UserDTO expectedUser = new UserDTO(1, 1, "Rawan", "Abou Dehn", "rawan image");

        when(contactsRepository.getUserDTO(1)).thenReturn(expectedUser);

        UserDTO actualUser = contactController.getUserDTO(1);

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void getUser() throws DatabaseException, URISyntaxException {
        User expectedUser = new User(1, "Rawan", "Abou Dehn", UserType.Student, "rawan.student@fontys.nl", "1234", 1, 1, "123456", "rawan image");

        when(contactsRepository.getUser(1)).thenReturn(expectedUser);

        User actualUser = contactController.getUser(1);

        assertEquals(expectedUser, actualUser);
    }
}
