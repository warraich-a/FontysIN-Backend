package service.controller;

import service.model.*;
import service.repository.DatabaseException;
import service.repository.JDBCProfileRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProfileController {


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
            User user = profileRepository.getUserById(userId);

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

}
