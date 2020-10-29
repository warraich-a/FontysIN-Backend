package service.repository;

import service.model.*;

import javax.management.Descriptor;
import javax.swing.*;
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
    private final List<Department> departments = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>();

    private final List<Working> workings = new ArrayList<>();


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

        Education edu1 = new Education(1, 1, "Fontys", 2019, 2023, "High School", "ICT", "Got good grades" );
        Education edu2 = new Education(2, 2, "NJC", 2000, 2005, "Bechelors", "ICT", "Got good grades" );
        Education edu3 = new Education(3, 3, "jklafd ", 2016, 2021, "Bechelors", "ICT", "Got good grades" );
        Education edu4 = new Education(4, 4, "FoId", 2015, 2020, "Bechelors", "ICT", "Got good grades" );
        Education edu5 = new Education(5, 5, "AGA ", 2017,2022, "Bechelors", "ICT", "Got good grades" );
        Education edu6 = new Education(6, 6, "----- Id",1997, 2005, "Bechelors", "ICT", "Got good grades" );


        educations.add(edu1);
        educations.add(edu2);
        educations.add(edu3);
        educations.add(edu4);
        educations.add(edu5);
        educations.add(edu6);

        //adding work info to the workings list
        Working work1 = new Working(1, 1, "Fontys", 2019);
        Working work2 = new Working(2, 2, "Fontys", 2005);
        Working work3 = new Working(3, 6, "Fontys ", 2000);

        workings.add(work1);
        workings.add(work2);
        workings.add(work3);


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



          //public User(int id, String firstName, String lastName, UserType type, String email, String password,
              //  String phoneNumbar, int addressId, int locationId, int departmentId, String userNumber)
        // Users
        User user1 = new User(1, "Rawan", "AD", UserType.Student, "rawan@fontys.com", "1234", "0634457345", 1, 1, 1, "123748", edu1, null);
        User user2 = new User(2, "Ranim", "Ayoubi", UserType.Student, "ranim@fontys.com", "1234", "0634586375", 2, 1, 1, "364957", edu2, null);
        User user3 = new User(3, "Anas", "Ahmad", UserType.Student, "anas@fontys.com", "1234", "0638465827", 3, 2, 2, "175947", edu3, null);
        User user4 = new User(4, "Denys", "Sytnyk", UserType.Student, "denys@fontys.com", "1234", "0638465283", 4, 3, 3, "947392", edu1, null);
        User user5 = new User(5, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", "0638483829", 5, 1, 4, "734695",edu3, null);
        User user6 = new User(6, "Michiel", "Koehorst", UserType.Teacher, "michiel@fontys.com", "1294", "0638489029", 5, 2, 3, "734695", edu6, work3);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

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
        About a2 = new About(2,2, "I am businesman");
        About a3 = new About(3,3, "I am software engineer");
        About a4 = new About(4,4, "I am businesman");
        About a5 = new About(5,5, "I am software engineer");
        About a6 = new About(6,6, "I am businesman");

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

    // get woking by its start work year
    public Working getWorking(int year) {
        for (Working w : workings) {
            if (w.getStartYearWork() == year) {
                return w;
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
            if (u.getUserDepartment() == depId) {
                filetered.add(u);
            }
        }
        return filetered;
    }

    //3. filter users by location
    public List<User> getUsersByLocation(int locId) {
        List<User> filetered = new ArrayList<>();
        for (User u : users) {
            if (u.getUserLocation() == locId) {
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

    //5. filter users by work year
    public List<User> getUsersByWorkYear(Working working) {
        List<User> filetered = new ArrayList<>();
        for (User u : users) {
            if (u.getWorking().equals(working)) {
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

    // to get experience by profile id
    public List<Experience> GetExperienceByProfileId(int id) {
        List<Experience> foundExperience = new ArrayList<>();
        for (Experience p : experiences) {
            if (p.getProfileId() == id) {
                foundExperience.add(p);
            }
        }
        return foundExperience;
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
        old.setLocationId(e.getLocationId());
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
        Experience eId = getExperienceID(experienceId);
        for (User u :users){
            for(Profile p: profiles){
                for(Experience e :experiences){
                    if(u.getUserID()== userId && p.getId()== profileId && experienceId == e.getId()){
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
                    if(u.getUserID()== userId && p.getId()== profileId && educationId == e.getId()){
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
                    if(u.getUserID()== userId && p.getId()== profileId && skillId == s.getId()){
                        skills.remove(es);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
