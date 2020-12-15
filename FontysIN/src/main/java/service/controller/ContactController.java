package service.controller;

import service.model.Contact;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;
import service.repository.ContactsRepository;
import service.repository.DatabaseException;

import java.net.URISyntaxException;
import java.util.List;

public class ContactController {
    ContactsRepository contactsRepository = new ContactsRepository();

    /**
     *
     * @param id
     * @return List of all contacts of type ContactDTO
     */
    public List<ContactDTO> getAllContactsDTO(int id) {
        List<ContactDTO> allContacts;
        try {
            allContacts = contactsRepository.getAllContactsDTO(id);

            return allContacts;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param id
     * @return List of accepted contacts of type ContactDTO
     */
    public List<ContactDTO> getAcceptedContactsDTO(int id) {
        List<ContactDTO> acceptedContacts;
        try {
            acceptedContacts = contactsRepository.getAcceptedContactsDTO(id);
            System.out.println("Accepted contact repository");

            return acceptedContacts;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param id
     * @return List of contacts requests of type ContactDTO
     */
    public List<ContactDTO> getContactsRequestsDTO(int id) {
        List<ContactDTO> requests;
        try {
            requests = contactsRepository.getContactsRequestsDTO(id);

            return requests;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param contact
     * @return Create new contact
     */
    public int createContact(ContactDTO contact) {
        int contactId = -1;
        try {
            contactId =  contactsRepository.createContact(contact);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return contactId;
    }

    /**
     *
     * @param userId
     * @param contactId
     * @return Delete a contact
     */
    public boolean deleteContact(int userId, int contactId) {
        try {
            return contactsRepository.deleteContact(userId, contactId);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param contactId
     * @param contact
     */
    public void updateContact(int contactId, Contact contact) {
        try {
            contactsRepository.updateContact(contactId, contact);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return User of type UserDTO
     */
    public UserDTO getUserDTO(int id) {
        try {
            return contactsRepository.getUserDTO(id);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
