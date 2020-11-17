package service;

import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;
import service.repository.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

public class PersistenceController {

    // Post section

    public List<Posts> getPosts(){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPosts();

                System.out.println("ok");

            return posts;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Posts> getNewsfeed(int uId){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getNewsfeed(uId);

            for (Posts post: posts) {
                System.out.println(post.getId());
            }

            return posts;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Posts> getPostByUserId(int uId){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPostsByUserId(uId);

            System.out.println("ok");

            return posts;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Posts getPost(int Id){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            Posts post = (Posts) postsRepository.getPost(Id);

            System.out.println("ok");

            return post;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addPost(Posts post){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            return postsRepository.addPosts(post);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePost(Posts post){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            return postsRepository.updatePost(post);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePost(Posts post){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            return postsRepository.deletePost(post);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    // End of Post section
    // Comment section

    public List<Comments> getCommets(){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getComments();

            System.out.println("ok");

            return comments;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comments> getCommetsByPostId(int pId){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getCommentsByPostId(pId);

            System.out.println("ok");

            return comments;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Comments getCommet(int Id){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            Comments comm = (Comments) commentsRepository.getComment(Id);

            System.out.println("ok");

            return comm;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addComment(Comments comm){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            return commentsRepository.addComm(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateComment(Comments comm){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            return commentsRepository.updateComm(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(Comments comm){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            return commentsRepository.deleteComment(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    //End of Comment section

    //Profile

    public List<About> getAbout(int userId, int profileId){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<About> about = profileRepository.getAbout(userId, profileId);

            System.out.println("ok");

            return about;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Experience> getExperience(int userId, int profileId){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<Experience> experience = profileRepository.getExperiences(userId, profileId);

            System.out.println("ok");

            return experience;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Education> getEducations(int userId, int profileId){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<Education> educations = profileRepository.getEducations(userId, profileId);

            System.out.println("ok");

            return educations;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Skill> getSkills(int userId, int profileId){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<Skill> skills = profileRepository.getSkills(userId, profileId);

            System.out.println("ok");

            return skills;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Profile> getProfile(int userId){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<Profile> profiles = profileRepository.getProfile(userId);

            System.out.println("ok");

            return profiles;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int userId){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            User user = profileRepository.getUser(userId);

            System.out.println("ok");

            return user;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addExperience(Experience experience) {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            if(profileRepository.createExperience(experience)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addEducation(Education education) {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            if(profileRepository.createEducation(education)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addSkill(Skill skill, int userId) {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            if(profileRepository.createSkill(skill, userId)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addProfile(Profile profile, int userId) throws DatabaseException, SQLException {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        int id = profileRepository.createProfile(profile, userId);
        if( id != 0) {
            return id;
        }
        else
        {
            return 0;
        }
    }
    public boolean addAbout(About about) {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            if(profileRepository.createAbout(about)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /******************RANIM***********************Delete data in the profile page**************************/

    //delete education
    /**
     * This method deletes the education record from the DB for given education id and profileId.
     * @param userId
     * @param educationId
     * @param profileId
     * @throws DatabaseException
     */
    public boolean DeleteEducation(int userId, int profileId, int educationId){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try{
            profileRepository.deleteEducation(userId, profileId, educationId);
            System.out.println("deleted");
            return true;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //delete experience
    /**
     * This method deletes the experience record from the DB for given experience id and profileId.
     * @param userId
     * @param experienceId
     * @param profileId
     * @throws DatabaseException
     */
    public boolean DeleteExperience(int userId, int profileId, int experienceId){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try{
            profileRepository.deleteExperience(userId, profileId, experienceId);
            System.out.println("deleted");
            return true;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //delete skill
    /**
     * This method deletes the experience record from the DB for given skill id and profileId.
     * @param userId
     * @param skillId
     * @param profileId
     * @throws DatabaseException
     */
    public boolean DeleteSkill(int userId, int profileId, int skillId){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try{
            profileRepository.deleteSkill(userId, profileId, skillId);
            System.out.println("deleted");
            return true;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /******************RANIM***********************Filter Search One bu One**************************/


    /**
     * Show/print the users with the given type
     * @param type of the user to be shown.
     */
    //show user by user type
    public List<User> UserFilteredWithType(UserType type){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByType(type);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given location id
     * @param id of the user to be shown.
     */
    //show book by location
    public List<User> UserFilteredWithLocation(int id){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByLocation(id);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given department id
     * @param id of the user to be shown.
     */
    //show book by department
    public List<User> UserFilteredWithDepartment(int id){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByDepartment(id);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given start study year
     * @param year of the user to be shown.
     */
    //show book by start study year
    public List<User> UserFilteredWithStartStudyYear(int year){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByStartStudyYear(year);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Show/print the users with the given start work year
     * @param year of the user to be shown.
     */
    //show book by start work year
    public List<User> UserFilteredWithStartWorkYear(int year){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByStartWorkYear(year);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /******************RANIM***********************Combined Filter Search**************************/

    /**
     * Show/print the users with the given usertype location and department
     * @param type
     * @param lId
     * @param dId
     * of the user to be shown.
     */
    //show book by user type location and department
    public List<User> UserFilterByTypeLocationAndDepartment(UserType type, int lId, int dId){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByUserTypeAndLocationAndDepartment(type, lId, dId);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given usertype start study year location and department
     * @param type
     * @param year
     * @param lId
     * @param dId
     * of the user to be shown.
     */
    //show book by location user type department and start study year
    public List<User> UserFilterByTypeLocationDepartmentAndStartSudyYear(UserType type, int year, int lId, int dId){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByUserTypeAndStartStudyYearAndDepartmentAndLocation(type, year, lId, dId);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given usertype start work year location and department
     * @param type
     * @param year
     * @param lId
     * @param dId
     * of the user to be shown.
     */
    //show users by location user type department and start study year
    public List<User> UserFilterByTypeLocationDepartmentAndStartWorkyearFontysStaff(UserType type, int year, int lId, int dId){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getUsersByUserTypeAndStartWorkYearAndDepartmentAndLocationFontysStaff(type, year, lId, dId);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /****************************RANIM*****************************Normal searching*********************/
    /**
     * Show/print the users of FontysIn Web Application
     */
    //show all users
    public List<User> GetAllUsers(){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            List<User> users = profileRepository.getAllUsers();
            System.out.println(users);
            return users;
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }



    /* ------------------------------------------------- Contacts -----------------------------------------------------------------*/

    /**
     *
     * @param id
     * @return List of all contacts of type ContactDTO
     */
    public List<ContactDTO> getAllContactsDTO(int id) {
        ContactsRepository contactsRepository = new ContactsRepository();


        List<ContactDTO> allContacts;
        try {
            allContacts = contactsRepository.getAllContactsDTO(id);

            return allContacts;
        }
        catch (DatabaseException e) {
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
        System.out.println("Accepted contact repository");


        ContactsRepository contactsRepository = new ContactsRepository();

        List<ContactDTO> acceptedContacts;
        try {
            acceptedContacts = contactsRepository.getAcceptedContactsDTO(id);
            System.out.println("Accepted contact repository");

            return acceptedContacts;
        }
        catch (DatabaseException e) {
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
        ContactsRepository contactsRepository = new ContactsRepository();

        List<ContactDTO> requests;
        try {
            requests = contactsRepository.getContactsRequestsDTO(id);

            return requests;
        }
        catch (DatabaseException e) {
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
        ContactsRepository contactsRepository = new ContactsRepository();

        int contactId = -1;
        try {
            contactId =  contactsRepository.createContact(contact);
        }
        catch (DatabaseException e) {
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
        ContactsRepository contactsRepository = new ContactsRepository();

        try {
            return contactsRepository.deleteContact(userId, contactId);
        }
        catch (DatabaseException e) {
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
        ContactsRepository contactsRepository = new ContactsRepository();

        try {
            contactsRepository.updateContact(contactId, contact);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return User of type UserDTO
     */
    public UserDTO getUserDTO(int id) {
        ContactsRepository contactsRepository = new ContactsRepository();

        try {
            return contactsRepository.getUserDTO(id);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /* ------------------------------------------------- Contacts -----------------------------------------------------------------*/
    public Experience getExp(int Id){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();

        try {
            Experience exp = (Experience) profileRepository.getExperienceById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Education getEdu(int Id){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            Education exp = (Education) profileRepository.getEducationById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public About getAbo(int Id){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            About exp = (About) profileRepository.getAboutById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Address getAddress(int Id){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            Address exp = (Address) profileRepository.getAddressById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Privacy getPrivacy(User u){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            Privacy exp = (Privacy) profileRepository.getPrivacyByUser(u);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateEdu(Education edu){

        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return profileRepository.updateEducation(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateExp(Experience edu){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return profileRepository.updateExperience(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateAbo(About edu){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return profileRepository.updateAbout(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateAd(Address edu){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return profileRepository.updateAddress(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updatePri(Privacy edu){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return profileRepository.updatePrivacy(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updatePh(User edu){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return profileRepository.updatePhone(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Privacy GetPrivacySetting(int id){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try{
            List<Privacy> privacyList = profileRepository.getPrivacyList();
            for (Privacy p :privacyList){
                if(p.getUserId() == id){
                    return p;
                }
            }
        } catch (DatabaseException e) {
        e.printStackTrace();
    }
        return null;

    }

    private List<User> GetUsersConnections(int userId){
        ContactsRepository contactsRepository = new ContactsRepository();
        try{
            List<Contact> contactList = contactsRepository.getAllContacts(userId);
            List<User> connections = new ArrayList<>();
            for (Contact c :contactList) {
                if(c.getUser().getId() == userId){
                    if(c.getIsAccepted()){
                        User u = c.getFriend();
                        connections.add(u);
                    }
                }
            }

            return connections;
        } catch (DatabaseException e) {
                e.printStackTrace();
            }
        return null;
    }

    public boolean AllowedToSee(int userId, int visitorId, ProfilePart profilePart){
        User visitor = getUser(visitorId);
        Privacy settings = GetPrivacySetting(userId);

        // If there are no settings everyone is allowed to see
        if(settings == null)
        {
            return true;
        }

        Privacy.Setting privacySetting;
        switch(profilePart)
        {
            case EDUCATION:
                privacySetting = settings.getEducationSetting();
                break;
            case EXPERIENCE:
                privacySetting = settings.getExperienceSetting();
                break;
            case SKILLS:
                privacySetting = settings.getSkillSetting();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + profilePart);
        }
        if(userId == visitorId){ // So am i visting my own page
            return true;
        }
        else if(privacySetting == Privacy.Setting.EVERYONE){
            return true;
        }
        else if(privacySetting == Privacy.Setting.CONNECTIONS){
            List<User> Connections = GetUsersConnections(userId); // Get a user connections
            if(Connections.contains(visitor)){
                return true;
            }
        }
        return false;
    }

    public enum ProfilePart
    {
        EDUCATION, EXPERIENCE, SKILLS
    }
    public User getUserByEmail(String email) {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try{
            User u = null;
            List<User> Users = profileRepository.getUsers();
            for (User p :Users){
                if(p.getEmail().equals(email)){
                    return p;
                }
            }

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public boolean login(String email, String password){
        User u = getUserByEmail(email);
        if(u.equals(null)){
            return false;
        }
        if(u.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean isIdAndAuthSame(int id, String auth) {
        String encodedCredentials = auth.replaceFirst("Basic ", "");
        String credentials = new
                String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();

        User user = getUser(id);//studentsRepository.get(stNr);
        if (!user.getEmail().equals(email)) {
            return false;
        } else {
            return true;
        }
    }
    public boolean uploadPicture(int userId, String path){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            if(profileRepository.uploadImage(userId, path)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Location> getFontysLocations(){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            List<Location> locations = profileRepository.getFontysLocation();

            System.out.println("ok");

            return locations;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Department> getFontysDepartments(){
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            return  profileRepository.getFontysDepartments();
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int createAddress(Address address) throws DatabaseException, SQLException {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        int id = profileRepository.createAddress(address);
        if( id != 0) {
            return id;
        }
        else
        {
            return 0;
        }
    }

    public boolean addUser(User user) {
        JDBCProfileRepository profileRepository = new JDBCProfileRepository();
        try {
            if(profileRepository.createUser(user)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
