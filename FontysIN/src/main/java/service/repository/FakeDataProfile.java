package service.repository;

import service.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDataProfile {

    private final List<Experience> experiences = new ArrayList<>();
    private final List<Education> educations = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private static final FakeDataProfile INSTANCE = new FakeDataProfile();

    // Contacts
    private final List<Contact> contacts = new ArrayList<>();

    public static FakeDataProfile getInstance() {
        return INSTANCE;
    }

    private FakeDataProfile(){
        Experience e1 = new Experience(1, 1, "No title", "Fontys", "Teacher", 1, LocalDate.of(1998,01,01), LocalDate.of(2000,01,01), "I love it" );
        Experience e2 = new Experience(2, 1, "No title", "Fontys", "Employee", 1, LocalDate.of(1996,01,01), LocalDate.of(1998,01,01), "was good" );

        experiences.add(e1);
        experiences.add(e2);

        Education edu1 = new Education(1, 1, "Fontys", LocalDate.of(2018,01,01), LocalDate.of(2020,01,01), "High School", "ICT", "Got good grades" );
        Education edu2 = new Education(2, 1, "Fontys", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );

        educations.add(edu1);
        educations.add(edu2);


          //public User(int id, String firstName, String lastName, UserType type, String email, String password,
              //  String phoneNumbar, int addressId, int locationId, int departmentId, String userNumber)
        // Users
        User user1 = new User(1, "Rawam", "AD", UserType.Student, "rawan@fontys.com", "1234", "0634457345", 1, 2, 2, "123748");
        User user2 = new User(2, "Ranim", "Ayoubi", UserType.Student, "ranim@fontys.com", "1234", "0634586375", 2, 2, 2, "364957");
        User user3 = new User(3, "Anas", "Ahmad", UserType.Student, "anas@fontys.com", "1234", "0638465827", 3, 2, 2, "175947");
        User user4 = new User(4, "Denys", "Sytnyk", UserType.Student, "denys@fontys.com", "1234", "0638465283", 4, 2, 2, "947392");
        User user5 = new User(5, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", "0638483829", 5, 2, 2, "734695");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        // Contacts
        Contact contact1 = new Contact(1, 2);
        Contact contact2 = new Contact(1, 4);
        Contact contact3 = new Contact(2, 5);

        createContact(contact1);
        createContact(contact2);
        createContact(contact3);

        acceptContact(1);
        acceptContact(2);

    }

    // to get all the experiences
    public List<Experience> GetExperiences (){return experiences;}

    //to get experience
    public Experience GetExperienceById(int id){
        for (Experience e: experiences){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    public User GetUserById(int id){
        for (User e: users){
            if(e.getUserID() == id){
                return e;
            }
        }
        return null;
    }

    //to add experience
    public boolean AddExperience(Experience e){
        if (this.GetExperienceById(e.getId()) == null)
        {
            experiences.add(e);
                return true;
        }
        return false;
    }


    //Educations
    // to get all the All educations
    public List<Education> GetEducations (){return educations;}

    //to get experience
    public Education GetEducationById(int id){
        for (Education e: educations){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    //to add experience
    public boolean AddEducation(Education e){
        if (this.GetExperienceById(e.getId()) == null)
        {
            educations.add(e);
            return true;
        }
        return false;
    }

    public boolean updateExperience(Experience e) {
        Experience old = this.GetExperienceById(e.getId());
        if (old == null) {
            return false;
        }
        old.setTitle(e.getTitle());
        old.setEmploymentType(e.getEmploymentType());
        old.setLocationId(e.getLocationId());
        old.setCompany(e.getCompany());
        old.setStartDate(e.getStartDate());
        old.setEndDate(e.getEndDate());
        old.setDescription(e.getDescription());

        return true;
    }
    public boolean updateEducation(Education e) {
        Education old = this.GetEducationById(e.getId());
        if (old == null) {
            return false;
        }
        old.setSchool(e.getSchool());
        old.setStartYear(e.getStartYear());
        old.setEndDate(e.getEndDate());
        old.setDegree(e.getDegree());
        old.setFieldStudy(e.getFieldStudy());
        old.setDescription(e.getDescription());

        return true;
    }
    public boolean updateUser(User e) {
        User old = this.GetUserById(e.getUserID());
        if (old == null) {
            return false;
        }
        old.setUserEmail(e.getUserEmail());
        old.setUserPhoneNumber(e.getUserPhoneNumber());
        old.setUserAddress(e.getUserAddress());
        old.setUserImage(e.getUserImage());
        old.setUserLocation(e.getUserLocation());
        old.setUserDepartment(e.getUserDepartment());

        return true;
    }


    /*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */
    public int createContact(Contact createdContact) {
        boolean areAlreadyFriends;
        for (Contact contact: contacts) {
            areAlreadyFriends = (contact.getUserId() == createdContact.getUserId() && contact.getFriendId() == createdContact.getFriendId()) || (contact.getFriendId() == createdContact.getUserId() && contact.getUserId() == createdContact.getFriendId());
            if(areAlreadyFriends) {
                return -1;
            }
        }

        // if users aren't friends
        Contact contact = createdContact;
        contacts.add(contact);
        return contact.getId();
    }

    // Delete or Reject
    public boolean deleteContact(int userId, int contactId) {
        for (Contact contact: contacts) {
            if((contact.getUserId() == userId || contact.getFriendId() == userId) && contact.getId() == contactId) {
                contacts.remove(contact);
                return true;
            }
        }

        return false;
    }

    public List<Contact> getContacts(int id) {
        List<Contact> foundContacts = new ArrayList<>();
        List<User> userContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getUserId() == id || contact.getFriendId() == id) {
                foundContacts.add(contact);
            }
        }
//        for (Contact contact : foundContacts) {
//            int userId = contact.getUserId();
//            int friendId = contact.getFriendId();
//            //boolean isAccepted = contact.isAccepted();
//            User friend = null;
//            if(userId == id) {
//                friend = getUser(friendId);
//                userContacts.add(friend);
//            }
//            else if(friendId == id) {
//                friend = getUser(userId);
//                userContacts.add(friend);
//            }
////            if(userId == id && isAccepted) {
////                friend = getUser(friendId);
////                userContacts.add(friend);
////            }
////            else if(friendId == id && isAccepted) {
////                friend = getUser(userId);
////                userContacts.add(friend);
////            }
//        }

        return foundContacts;
    }

    // userId is sender
    // friendId is receiver
    public void acceptContact(int contactId) {
        for (Contact contact: contacts) {
            if(contact.getId() == contactId) {
                contact.setAccepted(true);
            }
        }
    }
    /*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */




    public User getUser(int id) {
        for (User user: users) {
            if(user.getUserID() == id) {
                return user;
            }
        }

        return null;
    }


}
