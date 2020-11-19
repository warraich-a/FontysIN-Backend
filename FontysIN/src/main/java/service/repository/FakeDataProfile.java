package service.repository;

import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDataProfile {

    private final List<Experience> experiences = new ArrayList<>();
    private final List<Education> educations = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Profile> profiles = new ArrayList<>();
    private final List<About> abouts = new ArrayList<>();
    private final List<Skill> skills = new ArrayList<>();
    private final List<Address> addresses = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>();
    private final List<Privacy> privacyList = new ArrayList<>();


    private static final FakeDataProfile INSTANCE = new FakeDataProfile();

    // Contacts
    private final List<Contact> contacts = new ArrayList<>();

    public static FakeDataProfile getInstance() {
        return INSTANCE;
    }

    private FakeDataProfile(){


        Experience e1 = new Experience( 1, "Content Writer", "Private", EmplymentType.FreeLancer, "Islamabad, Pakistan",2016, 2020, "I had a pretty good time there" );
        Experience e2 = new Experience( 2, "Manager", "AKU", EmplymentType.FullTime,  "Islamabad, Pakistan",2016, 2020, "One of my best jobs" );
        Experience e3 = new Experience( 3, "Staff", "Fontys", EmplymentType.PartTime,  "Islamabad, Pakistan",2016, 2020,"Loved to work there" );
        Experience e4 = new Experience( 2, "Employee", "Fontys", EmplymentType.FullTime,  "Islamabad, Pakistan",2016, 2020, "Not bad" );
        Experience e5 = new Experience( 4, "Clerk", "Fontys", EmplymentType.FreeLancer,  "Islamabad, Pakistan",2016, 2020, "I was happy there" );
        Experience e6 = new Experience( 2, "Boss", "Fontys", EmplymentType.FullTime,  "Islamabad, Pakistan",2016, 2020, "Good one" );



        experiences.add(e1);
        experiences.add(e2);
        experiences.add(e3);
        experiences.add(e4);
        experiences.add(e5);
        experiences.add(e6);

        Education edu1 = new Education(1, "Fontys", 2019, 2023, "High School", "ICT", "Got good grades" );
        Education edu2 = new Education(2, "NJC", 2000, 2005, "Bechelors", "ICT", "Got good grades" );
        Education edu3 = new Education(3, "SGD ", 2016, 2021, "Bechelors", "ICT", "Got good grades" );
        Education edu4 = new Education(4, "FoId", 2015, 2020, "Bechelors", "ICT", "Got good grades" );
        Education edu5 = new Education(5, "AGA ", 2017,2022, "Bechelors", "ICT", "Got good grades" );
        Education edu6 = new Education(6, "Lahore",101010, 2005, "Bechelors", "ICT", "Got good grades" );



        educations.add(edu1);
        educations.add(edu2);
        educations.add(edu3);
        educations.add(edu4);
        educations.add(edu5);
        educations.add(edu6);

        Address adress1 = new Address(1, "Meijhorst", "3339", "Nijmegen", "6537JA");

        addresses.add(adress1);

        //adding department to alist
        Department dep1 = new Department(1, "ICT", "Information....");
        Department dep2 = new Department(2, "Pedagogy", "Information....");
        Department dep3 = new Department(3, "Buisness", "Information....");
        Department dep4 = new Department(4, "Economy", "Information....");


        departments.add(dep1);
        departments.add(dep2);
        departments.add(dep3);
        departments.add(dep4);

        //adding location at a list
        Location loc1 = new Location(1, "Rachelsmolen", "R10", "Eindhoven", "5612 MA");
        Location loc2 = new Location(2, "Professor Goossenslaan", "R01", "Tilburg", "5022 DM ");
        Location loc3 = new Location(3, "Tegelseweg", "T12", "Venlo", "5912 BG");


        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);


        // Users

        User user1 = new User(1, "Rawan", "AD", UserType.Student, "rawan@fontys.com", "1234", "0634457345", 1, 1, 1, "123748", edu1);
        User user2 = new User(2, "Ranim", "Ayoubi", UserType.Student, "ranim@fontys.com", "1234", "0634586375", 2,1, 1, "364957", edu2);
        User user3 = new User(3, "Anas", "Ahmad", UserType.Student, "anas@fontys.com", "1234", "0638465827", 3, 2, 2, "175947", edu3);
        User user4 = new User(4, "Denys", "Sytnyk", UserType.Student, "denys@fontys.com", "1234", "0638465283", 4, 3, 3, "947392", edu1);
        User user5 = new User(5, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", "0638483829", 5, 1, 4, "734695",edu3);
        User user6 = new User(6, "Ahmad", "Ahmad", UserType.FontysStaff, "ahmad@fontys.com", "1234", "0638483829", 5, 2, 3, "734695", edu6);
        User user7 = new User(7, "Robin", "Bomers", UserType.FontysStaff, "robin@fontys.com", "1234", "0638465283", 4, 1, 1, "364957", edu6);
        User user8 = new User(8, "Kelvin", "Kanen", UserType.Teacher, "kelvin@fontys.com", "1234", "0638483829", 5, 2, 3, "734695", edu6);
        User user9 = new User(9, "Ali", "Hweja", UserType.Teacher, "ali@fontys.com", "1234", "0638483829", 5, 1, 4, "734695",edu6);


        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);


        // Contacts
        Contact contact1 = new Contact(user1, user2); // Ranim
        Contact contact2 = new Contact(user1, user4); // Denys
        Contact contact3 = new Contact(user1, user5); // Beatrice
        Contact contact4 = new Contact(user1, user3); // Anas
        Contact contact5 = new Contact(user2, user3);
        Contact contact6 = new Contact(user2, user4);
        Contact contact7 = new Contact(user7, user8); // Robin
        Contact contact8 = new Contact(user8, user1); // Kelvin
        Contact contact9 = new Contact(user9, user1); // Ali
        Contact contact10 = new Contact(user2, user5);

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        contacts.add(contact6);
        contacts.add(contact7);
        contacts.add(contact8);
        contacts.add(contact9);
        contacts.add(contact10);

        // Accept contact
        contact1.setIsAccepted(true);
        contact2.setIsAccepted(true);
        contact3.setIsAccepted(true);

        updateContact(0, contact1);
        updateContact(1, contact2);
        updateContact(2, contact3);



        Profile p1 = new Profile(1, "English");
        Profile p2 = new Profile(1, "Dutch");
        Profile p3 = new Profile( 2, "English");
        Profile p4 = new Profile( 2, "Dutch");
        Profile p5 = new Profile( 3, "English");
        Profile p6 = new Profile( 3, "Dutch");
        Profile p7 = new Profile( 4, "English");
        Profile p8 = new Profile( 4, "Dutch");
        Profile p9 = new Profile( 5, "English");
        Profile p10 = new Profile( 5, "Dutch");
        Profile p11 = new Profile( 6, "English");
        Profile p12 = new Profile( 6, "Dutch");
        Profile p13 = new Profile( 7, "English");
        Profile p14 = new Profile( 7, "Dutch");
        Profile p15 = new Profile( 8, "English");
        Profile p16 = new Profile( 8, "Dutch");
        Profile p17 = new Profile( 9, "English");
        Profile p18 = new Profile( 9, "Dutch");


        profiles.add(p1);
        profiles.add(p2);
        profiles.add(p3);
        profiles.add(p4);
        profiles.add(p5);
        profiles.add(p6);
        profiles.add(p7);
        profiles.add(p8);
        profiles.add(p9);
        profiles.add(p10);
        profiles.add(p11);
        profiles.add(p12);
        profiles.add(p13);
        profiles.add(p14);
        profiles.add(p15);
        profiles.add(p16);
        profiles.add(p17);
        profiles.add(p18);


        About a1 = new About(1, "I am software engineer");
        About a2 = new About(2, "I am businesman");
        About a3 = new About(3, "I am architecture");
        About a4 = new About(4, "I am chief");
        About a5 = new About(5, "I am free engineer");
        About a6 = new About(6, "I am nothing");

        abouts.add(a1);
        abouts.add(a2);
        abouts.add(a3);
        abouts.add(a4);
        abouts.add(a5);
        abouts.add(a6);

        Skill s1 = new Skill(1, "HTML");
        Skill s2 = new Skill(1, "PHP");
        Skill s3 = new Skill(2, "C#");
        Skill s4 = new Skill(3, "Java");
        Skill s5 = new Skill(4, "C#");
        Skill s6 = new Skill(5, "Java");


        skills.add(s1);
        skills.add(s2);
        skills.add(s3);
        skills.add(s4);
        skills.add(s5);
        skills.add(s6);


//        Privacy privacy1 = new Privacy(1,1);
//        Privacy privacy2 = new Privacy(2,2);
//        privacyList.add(privacy1);
//        privacyList.add(privacy2);
    }

    //searching implementation by filtering

    //first:
    //get all users
    public List<User> getUsers() {
        return users;
    }

    //Second:
    // get User type
    public User getUserType(UserType type) {
        for (User u : users) {
            if (u.getUserType().equals(type)) {
                return u;
            }
        }
        return null;
    }

    // get education by its studyYear
    public Education getEducation(int year) {
        for (Education e : educations) {
            if (e.getStartYearEducation() == year) {
                return e;
            }
        }
        return null;
    }

    // get department by its name
    public Department getDepartment(String depName) {
        for (Department d : departments) {
            if (d.getName().equals(depName)) {
                return d;
            }
        }
        return null;
    }

    //filters:

    //1. filter users by type
    public List<User> getUsersByUserType(UserType type) {
        List<User> filetered = new ArrayList<>();
        for (User u : users) {
            if (u.getUserType().equals(type)) {
                filetered.add(u);
            }
        }
        return filetered;
    }

    //2. filter users by department
    public List<User> getUsersByDepartment(int depId) {
        List<User> filetered = new ArrayList<>();
        for (User u : users) {
            if (u.getDepartmentId() == depId) {
                filetered.add(u);
            }
        }
        return filetered;
    }

    //3. filter users by location
    public List<User> getUsersByLocation(int locId) {
        List<User> filetered = new ArrayList<>();
        for (User u : users) {
            if (u.getLocationId() == locId) {
                filetered.add(u);
            }
        }
        return filetered;
    }

    //4. filter users by study year
    public List<User> getUsersByStudyYear(Education education) {
        List<User> filetered = new ArrayList<>();
        for (User u : users) {
            if (u.getEducation().equals(education)) {
                filetered.add(u);
            }
        }
        return filetered;
    }



    //Experience

    // Get list
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




    // get experiences by profile id
    public List<Experience> GetExperiencesByProfileID(int userId, int profileId){

        List<Experience> foundExperiences = new ArrayList<>();
        for (Profile p: profiles)
        {
            if(p.getUserId() == userId && p.getId() == profileId)
            {
                for (Experience e : experiences) {
                    if (e.getProfileId() == profileId) {
                        foundExperiences.add(e);
                    }
                }
                return foundExperiences;
            }

        }

        return null;
    }

    //
    public User GetUserById(int id){
        for (User e: users){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    //to add experience
    public boolean AddExperience(Experience e, int userId, int profileId){

        for (Profile p: profiles) {
            if (p.getUserId() == userId) {
                if(p.getId() == profileId) {
                    if (this.GetExperienceById(e.getId()) == null) {
                        experiences.add(e);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //update

    //delete



    //Educations
    // to get all the All educations
    public List<Education> GetEducations (){return educations;}

    //to get experience
    public  Education GetEducationById(int id){

        for (Education e: educations){
            if(e.getId() == id){
               return e;
            }
        }
        return null;
    }

    // to get experience by profile id
    public List<Experience> GetExperienceByProfileId(int id){
        List<Experience> foundExperience = new ArrayList<>();
        for (Experience p: experiences){
            if(p.getProfileId() == id){
                foundExperience.add(p);
            }
        }
        return foundExperience;
    }

    //get educations by profile id
    public  List<Education>  GetEducationsByProfileId(int userId, int profileId){

        List<Education> foundEducations = new ArrayList<>();
        for (Profile p: profiles)
        {
            if(p.getUserId() == userId && p.getId() == profileId)
            {
                for (Education e : educations) {
                    if (e.getProfileId() == profileId) {
                        foundEducations.add(e);
                    }
                }
                return foundEducations;
            }

        }

        return null;
    }

    //get skills by profile id
    public  List<Skill>  GetSkillsByProfileId(int id){
        List<Skill> foundSkills = new ArrayList<>();
        for (Skill s: skills){
            if(s.getProfileId() == id){
                foundSkills.add(s);
            }
        }
        return foundSkills;
    }

    //to add educations
    public boolean AddEducation(Education e, int userId, int profileId){

        for (Profile p: profiles) {
            if (p.getUserId() == userId) {
                if(p.getId() == profileId) {
                    if (this.GetEducationById(e.getId()) == null) {
                        educations.add(e);
                        return true;
                    }
                }
            }
        }
        return false;

    }

    //update

    //delete



    //Profile

    // to get profiles
    public List<Profile> GetProfileByUserId(int id){
        List<Profile> foundProfiles = new ArrayList<>();

        for (Profile p: profiles){
            if(p.getUserId() == id){
                foundProfiles.add(p);
            }
        }
        return foundProfiles;
    }


    //About

    // to get about
    // Get list
    public List<About> GetAbouts (){return abouts;}

    //to get about
    public About GetAboutById(int id){
        for (About a: abouts){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    // get about by profile id
    public List<About> GetAboutByProfileID(int userId, int profileId){

        List<About> foundAbout = new ArrayList<>();
        for (Profile p: profiles) {
            if (p.getUserId() == userId && p.getId() == profileId)
            {
                for (About a : abouts) {
                    if (a.getProfileId() == profileId) {
                        foundAbout.add(a);
                    }
                }
                return foundAbout;
            }
        }

        return null;
    }
    //to add about
    public boolean AddAbout(About a, int userId, int profileId){

        for (Profile p: profiles) {
            if (p.getUserId() == userId) {
                if(p.getId() == profileId) {
                    if (this.GetAboutById(a.getId()) == null) {
                        abouts.add(a);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Skills

    // to get skills
    // Get list
    public List<Skill> GetSkills (){return skills;}

    //to get about
    public Skill GetSkillById(String name){
        for (Skill s: skills){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    // get skill by profile id
    public  List<Skill>  GetSkillsByProfileId(int userId, int profileId){

        List<Skill> foundSkills = new ArrayList<>();
        for (Profile p: profiles) {
            if (p.getUserId() == userId && p.getId() == profileId) {
                for (Skill s : skills) {
                    if (s.getProfileId() == profileId) {
                        foundSkills.add(s);
                    }
                }
                return foundSkills;
            }
        }

        return null;
    }
    //to add about
    public boolean AddSkill(Skill s, int userId, int profileId){

        for (Profile p: profiles) {
            if (p.getUserId() == userId) {
                for (Skill sk: skills){
                        if(sk.getProfileId() == profileId) {
                            if (sk.getName().equals(s.getName())) {

                                return false;
                            }
                        }
                }
            }
        }
        skills.add(s);
        return true;
    }


    //to add profile


    public boolean AddProfile(int userId, Profile newProfile){

        About about = new About();

        for (Profile p: profiles) {
            if (p.getUserId() == userId) {
                if (p.getLanguage().equals(newProfile.getLanguage())) {
                    return false;
                }
            }
        }
        profiles.add(newProfile);
//        about.setContent(contentAbout);
//        about.setProfileId(newProfile.getId()); ;
//        AddAbout(about, userId, newProfile.getId());
        return true;
    }

    public boolean updateAbout(int id, About a) {
        About old = this.GetAboutById(id);
        if (old == null) {
            return false;
        }
        old.setContent(a.getContent());

        return true;
    }
    public boolean updateExperience(int id, Experience e) {
        Experience old = this.GetExperienceById(id);
        if (old == null) {
            return false;
        }
        old.setTitle(e.getTitle());
        old.setEmploymentType(e.getEmploymentType());
        old.setLocation(e.getLocation());
        old.setCompany(e.getCompany());
        old.setStartDateExperience(e.getStartDateExperience());
        old.setEndDateExperience(e.getEndDateExperience());
        old.setDescriptionExperience(e.getDescriptionExperience());

        return true;
    }
    public boolean updateEducation(int id, Education e) {
        Education old = this.GetEducationById(id);
        if (old == null) {
            return false;
        }
        old.setSchool(e.getSchool());
        old.setStartYearEducation(e.getStartYearEducation());
        old.setEndYearEducation(e.getEndYearEducation());
        old.setDegreeEducation(e.getDegreeEducation());
        old.setFieldStudy(e.getFieldStudy());
        old.setDescriptionEducation(e.getDescriptionEducation());

        return true;
    }
    public boolean updateUser(int id, User e) {
        User old = this.GetUserById(id);
        if (old == null) {
            return false;
        }

        old.setUserPhoneNumber(e.getUserPhoneNumber());

       // old.setUserImage(e.getUserImage());
        //old.setUserDepartment(e.getUserDepartment());

        return true;
    }

    public Address GetAddressById(int id){
        for (Address a: addresses){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public boolean updateAddress(int id, Address a) {
        Address old = this.GetAddressById(id);
        if (old == null) {
            return false;
        }

        old.setCity(a.getCity());
        old.setHouseNumber(a.getHouseNumber());
        old.setStreetName(a.getStreetName());
        old.setZipCode(a.getZipCode());
        return true;
    }


    /*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */
    // Convert ContactDTO to Contact
    public Contact constructContact(ContactDTO contactDTO) {
        User user = getUser(contactDTO.getUser().getId()); //  null
        User friend = getUser(contactDTO.getFriend().getId()); // null

        return new Contact(user, friend);
    }

    // Convert Contact to ContactDTO
    public ContactDTO constructContactDTO(Contact contact) {
        UserDTO user = getUserDTO(contact.getUser().getId());
        UserDTO friend = getUserDTO(contact.getFriend().getId());

        return new ContactDTO(contact.getId(), user, friend, contact.getIsAccepted());
    }

    public int createContact(ContactDTO createdContactDTO) {
        Contact createdContact = constructContact(createdContactDTO);

        boolean areAlreadyFriends;
        for (Contact contact: contacts) {
            areAlreadyFriends = (contact.getUser().getId() == createdContact.getUser().getId() && contact.getFriend().getId() == createdContact.getFriend().getId()) || (contact.getFriend().getId() == createdContact.getUser().getId() && contact.getUser().getId() == createdContact.getFriend().getId());

            if(areAlreadyFriends) {
                return -1;
            }
        }

        // if users aren't friends
        contacts.add(createdContact);
        return createdContact.getId();
    }

    // Delete or Reject
    public boolean deleteContact(int userId, int contactId) {
        for (Contact contact: contacts) {
            if((contact.getUser().getId() == userId || contact.getFriend().getId() == userId) && contact.getId() == contactId) {
                contacts.remove(contact);
                return true;
            }
        }

        return false;
    }

    public List<Contact> getAllContacts(int id) {
        List<Contact> allContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if ((contact.getUser().getId() == id || contact.getFriend().getId() == id)) {
                allContacts.add(contact);
            }
        }

        return allContacts;
    }

    public List<ContactDTO> getAllContactsDTO(int id) {
        // All contacts of current user
        List<Contact> allContacts = getAllContacts(id);

        List<ContactDTO> allContactsDTO = new ArrayList<>();

        for (Contact contact : allContacts) {

            // USER
            User foundUser = contact.getUser();
            // Get first profile
            int userProfileId = GetProfileByUserId(foundUser.getId()).get(0).getId();
            // Create User data transfer object
            UserDTO user = new UserDTO(foundUser.getId(), userProfileId, foundUser.getFirstName(), foundUser.getLastName(), foundUser.getImg());


            // Friend
            User foundFriend = contact.getFriend();
            // Get first profile
            int friendProfileId = GetProfileByUserId(foundFriend.getId()).get(0).getId();
            // Create Friend data transfer object
            UserDTO friend = new UserDTO(foundFriend.getId(), friendProfileId, foundFriend.getFirstName(), foundFriend.getLastName(), foundFriend.getImg());


            // Create contact data transfer object
            ContactDTO contactDTO = new ContactDTO(contact.getId(), user, friend, contact.getIsAccepted());

            allContactsDTO.add(contactDTO);

        }

        return allContactsDTO;
    }

    // Accepted contacts
    public List<Contact> getContacts(int id) {
        List<Contact> allContacts = getAllContacts(id);
        List<Contact> acceptedContacts = new ArrayList<>();

        for (Contact contact : allContacts) {
            if (contact.getIsAccepted()) {
                acceptedContacts.add(contact);
            }
        }

        return acceptedContacts;
    }

    public List<ContactDTO> getContactsDTO(int id) {
        List<ContactDTO> allContacts = getAllContactsDTO(id);
        List<ContactDTO> acceptedContacts = new ArrayList<>();

        for (ContactDTO contact : allContacts) {
            if (contact.getIsAccepted()) {
                acceptedContacts.add(contact);
            }
        }

        return acceptedContacts;
    }


    // Get requests
    public List<Contact> getContactsRequests(int id) {
        List<Contact> allContacts = getAllContacts(id);

        List<Contact> requests = new ArrayList<>();

        for (Contact contact : allContacts) {
            if(!contact.getIsAccepted() && contact.getFriend().getId() == id) {
                requests.add(contact);
            }
        }

        return requests;
    }

    public List<ContactDTO> getContactsRequestsDTO(int id) {
        List<ContactDTO> allContacts = getAllContactsDTO(id);

        List<ContactDTO> requests = new ArrayList<>();

        for (ContactDTO contact : allContacts) {
            if(!contact.getIsAccepted() && contact.getFriend().getId() == id) {
                requests.add(contact);
            }
        }

        return requests;
    }


    public void updateContact(int contactId, Contact updatedContact) {
        updatedContact.setId(contactId);
        Contact.decreaseIdSeeder();

        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getId() == contactId) {
                contacts.remove(i);
                contacts.add(i, updatedContact);
            }
        }
    }

    public UserDTO getUserDTO(int id) {
        for (User user: users) {
            if(user.getId() == id) {
                // Get first profile
                int userProfileId = GetProfileByUserId(user.getId()).get(0).getId();

                return new UserDTO(user.getId(), userProfileId, user.getFirstName(), user.getLastName(), user.getImg());
            }
        }

        return null;
    }

    public User getUser(int id) {
        for (User user: users) {
            if(user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    /*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */




    //get experience id
    public Experience getExperienceID(int id) {
        for (Experience e : experiences) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    //get education id
    public Education getEducationID(int id) {
        for (Education e : educations) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    //get skill id
    public Skill getSkillID(int id) {
        for (Skill s : skills) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    //delete experience id for specific user
    public boolean deleteExperience(int userId, int profileId, int experienceId) {
        Experience eId = getExperienceID(experienceId);
        for (User u :users){
            for(Profile p: profiles){
                for(Experience e :experiences){
                    if(u.getId()== userId && p.getId()== profileId && experienceId == e.getId()){
                        experiences.remove(eId);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //delete education id for specific user
    public boolean deleteEducation(int userId, int profileId, int educationId) {
        Education eId = getEducationID(educationId);
        for (User u :users){
            for(Profile p: profiles){
                for(Education e :educations){
                    if(u.getId()== userId && p.getId()== profileId && educationId == e.getId()){
                        educations.remove(eId);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //delete Skill id for specific user
    public boolean deleteSkill(int userId, int profileId, int skillId) {
        Skill es = getSkillID(skillId);
        for (User u :users){
            for(Profile p: profiles){
                for(Skill s :skills){
                    if(u.getId()== userId && p.getId()== profileId && skillId == s.getId()){
                        skills.remove(es);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //////////////////Privacy


}
