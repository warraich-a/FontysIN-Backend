package service;

import service.model.*;
import service.repository.DatabaseException;
import service.repository.JDBCComments;
import service.repository.JDBCPosts;
import service.repository.JDBCProfileRepository;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * Show/print the users with the given type
     * @param type of the user to be shown.
     */
    //show user by user type type
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
    //show book by user type
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
    //show book by user type
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



}
