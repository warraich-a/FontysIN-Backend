package service.repository;

import service.model.*;

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

    private static final FakeDataProfile INSTANCE = new FakeDataProfile();

    // Contacts
    private final List<Contact> contacts = new ArrayList<>();

    public static FakeDataProfile getInstance() {
        return INSTANCE;
    }

    private FakeDataProfile(){

        Experience e1 = new Experience(1, 1, "Profile id 1", "Fontys", EmplymentType.FreeLancer, 1, LocalDate.of(1998,01,01), LocalDate.of(2000,01,01), "I love it" );
        Experience e2 = new Experience(2, 2, "profile id 2", "Fontys", EmplymentType.FullTime, 1, LocalDate.of(1996,01,01), LocalDate.of(1998,01,01), "was good" );
        Experience e3 = new Experience(3, 1, "Profile id 1", "Fontys", EmplymentType.PartTime, 1, LocalDate.of(1998,01,01), LocalDate.of(2000,01,01), "I love it" );
        Experience e4 = new Experience(4, 2, "profile id 2", "Fontys", EmplymentType.FullTime, 1, LocalDate.of(1996,01,01), LocalDate.of(1998,01,01), "was good" );
        Experience e5 = new Experience(5, 1, "Profile id 1", "Fontys", EmplymentType.FreeLancer, 1, LocalDate.of(1998,01,01), LocalDate.of(2000,01,01), "I love it" );
        Experience e6 = new Experience(6, 2, "profile id 2", "Fontys", EmplymentType.FullTime, 1, LocalDate.of(1996,01,01), LocalDate.of(1998,01,01), "was good" );


        experiences.add(e1);
        experiences.add(e2);
        experiences.add(e3);
        experiences.add(e4);
        experiences.add(e5);
        experiences.add(e6);

        Education edu1 = new Education(1, 1, "Fontys", LocalDate.of(2018,01,01), LocalDate.of(2020,01,01), "High School", "ICT", "Got good grades" );
        Education edu2 = new Education(2, 2, "NJC", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );
        Education edu3 = new Education(3, 3, "jklafd ", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );
        Education edu4 = new Education(4, 4, "FoId", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );
        Education edu5 = new Education(5, 5, "AGA ", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );
        Education edu6 = new Education(6, 6, "----- Id", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );


        educations.add(edu1);
        educations.add(edu2);
        educations.add(edu3);
        educations.add(edu4);
        educations.add(edu5);
        educations.add(edu6);


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
        createContact(1, 2);
        createContact(1, 4);
        createContact(2, 5);

        acceptContact(2, 1);
        acceptContact(5, 2);

        Profile p1 = new Profile(1, 1, "English");
        Profile p2 = new Profile(2, 1, "Dutch");
        Profile p3 = new Profile(3, 2, "English");
        Profile p4 = new Profile(4, 2, "Dutch");
        Profile p5 = new Profile(5, 3, "English");
        Profile p6 = new Profile(6, 3, "Dutch");

        profiles.add(p1);
        profiles.add(p2);
        profiles.add(p3);
        profiles.add(p4);
        profiles.add(p5);
        profiles.add(p6);

        About a1 = new About(1,1, "I am software engineer");
        About a2 = new About(2,1, "I am businesman");
        About a3 = new About(3,2, "I am software engineer");
        About a4 = new About(4,3, "I am businesman");
        About a5 = new About(5,4, "I am software engineer");
        About a6 = new About(6,5, "I am businesman");

        abouts.add(a1);
        abouts.add(a2);
        abouts.add(a3);
        abouts.add(a4);
        abouts.add(a5);
        abouts.add(a6);

        Skill s1 = new Skill(1,1, "HTML");
        Skill s2 = new Skill(2,1, "PHP");
        Skill s3 = new Skill(3,2, "C#");
        Skill s4 = new Skill(4,3, "Java");
        Skill s5 = new Skill(5,4, "C#");
        Skill s6 = new Skill(6,5, "Java");


        skills.add(s1);
        skills.add(s2);
        skills.add(s3);
        skills.add(s4);
        skills.add(s5);
        skills.add(s6);

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
    public List<Experience> GetExperiencesByProfileID(int id){
        List<Experience> foundExperiences = new ArrayList<>();
        for (Experience e: experiences){
            if(e.getProfileId() == id){
                foundExperiences.add(e);
            }
        }
        return foundExperiences;
    }

    //
    public User GetUserById(int id){
        for (User e: users){
            if(e.getUserID() == id){
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
            if(e.getProfileId() == id){
               return e;
            }
        }
        return null;
    }

    //get educations by profile id
    public  List<Education>  GetEducationsByProfileId(int id){
        List<Education> foundEducations = new ArrayList<>();
        for (Education e: educations){
            if(e.getProfileId() == id){
                foundEducations.add(e);
            }
        }
        return foundEducations;
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
    public List<About> GetAboutByProfileID(int id){
        List<About> foundAbout = new ArrayList<>();
        for (About a: abouts){
            if(a.getProfileId() == id){
                foundAbout.add(a);
            }
        }
        return foundAbout;
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
    public Skill GetSkillById(int id){
        for (Skill s: skills){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    // get skill by profile id
    public List<Skill> GetSkillByProfileID(int id){
        List<Skill> foundSkills = new ArrayList<>();
        for (Skill s: skills){
            if(s.getProfileId() == id){
                foundSkills.add(s);
            }
        }
        return foundSkills;
    }
    //to add about
    public boolean AddSkill(Skill s, int userId, int profileId){

        for (Profile p: profiles) {
            if (p.getUserId() == userId) {
                if (p.getId() == profileId) {
                    if (this.GetSkillById(s.getId()) == null) {
                        skills.add(s);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean updateExperience(int id, Experience e) {
        Experience old = this.GetExperienceById(id);
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
    public boolean updateEducation(int id, Education e) {
        Education old = this.GetEducationById(id);
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
    public boolean updateUser(int id, User e) {
        User old = this.GetUserById(id);
        if (old == null) {
            return false;
        }

        old.setUserPhoneNumber(e.getUserPhoneNumber());
        old.setUserAddress(e.getUserAddress());
        old.setUserImage(e.getUserImage());
        old.setUserLocation(e.getUserLocation());
        old.setUserDepartment(e.getUserDepartment());

        return true;
    }


                                                                                        /* Contacts */
    public int createContact(int userId, int friendId) {
        boolean areAlreadyFriends;
        for (Contact contact: contacts) {
            areAlreadyFriends = (contact.getUserId() == userId && contact.getFriendId() == friendId) || (contact.getFriendId() == userId && contact.getUserId() == friendId);
            if(areAlreadyFriends) {
                return -1;
            }
        }

        // if users aren't friends
        Contact contact = new Contact(userId, friendId);
        contacts.add(contact);
        return contact.getId();
    }

    // Delete or Reject
    public boolean deleteContact(int userId, int friendId) {
        for (Contact contact: contacts) {
            if((contact.getUserId() == userId && contact.getFriendId() == friendId) || (contact.getFriendId() == userId && contact.getUserId() == friendId)) {
                contacts.remove(contact);
                return true;
            }
        }

        return false;
    }

    public List<User> getContacts(int id) {
        List<Contact> foundContacts = new ArrayList<>();
        List<User> userContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getUserId() == id || contact.getFriendId() == id) {
                foundContacts.add(contact);
            }
        }
        for (Contact contact : foundContacts) {
            int userId = contact.getUserId();
            int friendId = contact.getFriendId();
            boolean isAccepted = contact.isAccepted();
            User friend = null;
            if(userId == id && isAccepted) {
                friend = getUser(friendId);
                userContacts.add(friend);
            }
            else if(friendId == id && isAccepted) {
                friend = getUser(userId);
                userContacts.add(friend);
            }
        }

        return userContacts;
    }

    // userId is sender
    // friendId is receiver
    public void acceptContact(int friendId, int userId) {
        for (Contact contact: contacts) {
            if(contact.getUserId() == userId && contact.getFriendId() == friendId) {
                contact.setAccepted(true);
            }
        }
    }

    public User getUser(int id) {
        for (User user: users) {
            if(user.getUserID() == id) {
                return user;
            }
        }

        return null;
    }

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
        Experience e = getExperienceID(experienceId);
        for (User u :users){
            for(Profile p: profiles){
                if(u.getUserID()== userId && p.getId()== profileId){
                    experiences.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    //delete experience id for specific user
    public boolean deleteEducation(int userId, int profileId, int educationId) {
        Education e = getEducationID(educationId);
        for (User u :users){
            for(Profile p: profiles){
                if(u.getUserID()== userId && p.getId()== profileId){
                    educations.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    //delete Skill id for specific user
    public boolean deleteSkill(int userId, int profileId, int skillId) {
        Skill s = getSkillID(skillId);
        for (User u :users){
            for(Profile p: profiles){
                if(u.getUserID()== userId && p.getId()== profileId){
                    skills.remove(s);
                    return true;
                }
            }
        }
        return false;
    }

}
