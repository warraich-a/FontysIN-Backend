package service.controller;

import service.model.*;
import service.model.dto.UserDTO;
import service.repository.ContactsRepository;
import service.repository.DatabaseException;
import service.repository.ProfileRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {

    ProfileRepository profileRepository = new ProfileRepository();

    public Data getData(int userId, int profileId, int loggedInUser) throws URISyntaxException, DatabaseException, SQLException {
        PrivacyController pController = new PrivacyController();

        try {
            List<Education> educations = profileRepository.getEducations(userId, profileId);
            List<Experience> experiences = profileRepository.getExperiences(userId, profileId);
            List<Skill> skills = profileRepository.getSkills(userId, profileId);

          boolean seeEducation =  pController.AllowedToSee(userId, loggedInUser, PrivacyController.ProfilePart.EDUCATION);
          boolean seeExperience =  pController.AllowedToSee(userId, loggedInUser, PrivacyController.ProfilePart.EXPERIENCE);
          boolean seeSkills =  pController.AllowedToSee(userId, loggedInUser, PrivacyController.ProfilePart.SKILLS);

          if(!seeEducation){
              educations.clear();
          }
          if(!seeExperience){
              experiences.clear();
          }
          if(!seeSkills){
              skills.clear();
          }

            Data data = new Data(experiences, educations, skills);

            return data;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<About> getAbout(int userId, int profileId){

        try {
            List<About> about = profileRepository.getAbout(userId, profileId);

            System.out.println("ok");

            return about;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Experience> getExperience(int userId, int profileId){

        List<Experience> experience;
        try {
            experience = profileRepository.getExperiences(userId, profileId);

            System.out.println("ok");

            return experience;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Education> getEducations(int userId, int profileId){

        try {
            List<Education> educations = profileRepository.getEducations(userId, profileId);

            System.out.println("ok");

            return educations;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Skill> getSkills(int userId, int profileId){

        try {
            List<Skill> skills = profileRepository.getSkills(userId, profileId);

            System.out.println("ok");

            return skills;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Profile> getProfile(int userId){
        try {
            List<Profile> profiles = profileRepository.getProfile(userId);

            System.out.println("ok");

            return profiles;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int userId){

        try {
            User user = profileRepository.getUserById(userId);

            System.out.println("ok");

            return user;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getCurrentUser(int userId){
        ContactsRepository contactsRepository = new ContactsRepository();
        try {
            User user = contactsRepository.getUser(userId);

            System.out.println("ok");

            return user;
        } catch (DatabaseException  | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addExperience(Experience experience) {
        try {
            if(profileRepository.createExperience(experience)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addEducation(Education education) {
        try {
            if(profileRepository.createEducation(education)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addSkill(Skill skill, int userId) {
        try {
            if(profileRepository.createSkill(skill, userId)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addProfile(Profile profile, int userId) throws DatabaseException, SQLException, URISyntaxException {
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
        try {
            if(profileRepository.createAbout(about)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException | URISyntaxException e) {
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

        ProfileRepository profileRepository = new ProfileRepository();

        try{
            profileRepository.deleteEducation(userId, profileId, educationId);
            System.out.println("deleted");
            return true;
        }
        catch (DatabaseException | URISyntaxException e) {
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

        ProfileRepository profileRepository = new ProfileRepository();

        try{
            profileRepository.deleteExperience(userId, profileId, experienceId);
            System.out.println("deleted");
            return true;
        }
        catch (DatabaseException | URISyntaxException e) {
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

        ProfileRepository profileRepository = new ProfileRepository();

        try{
            profileRepository.deleteSkill(userId, profileId, skillId);
            System.out.println("deleted");
            return true;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Experience getExp(int Id){
        ProfileRepository profileRepository = new ProfileRepository();

        try {
            Experience exp = (Experience) profileRepository.getExperienceById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Education getEdu(int Id){
        ProfileRepository profileRepository = new ProfileRepository();
        try {
            Education exp = (Education) profileRepository.getEducationById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public About getAbo(int Id){
        ProfileRepository profileRepository = new ProfileRepository();
        try {
            About exp = (About) profileRepository.getAboutById(Id);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean updateEdu(Education edu){

//        ProfileRepository profileRepository = new ProfileRepository();

        try {
            return profileRepository.updateEducation(edu);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateExp(Experience edu){

//        ProfileRepository profileRepository = new ProfileRepository();

        try {
            return profileRepository.updateExperience(edu);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateAbo(About edu){

//        ProfileRepository profileRepository = new ProfileRepository();

        try {
            return profileRepository.updateAbout(edu);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean uploadPicture(int userId, String path){
//        ProfileRepository profileRepository = new ProfileRepository();
        try {
            if(profileRepository.uploadImage(userId, path)) {
                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Location> getFontysLocations(){
//        ProfileRepository profileRepository = new ProfileRepository();
        try {
            List<Location> locations = profileRepository.getFontysLocation();

            System.out.println("ok");

            return locations;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Department> getFontysDepartments(){
//        ProfileRepository profileRepository = new ProfileRepository();
        try {
            return  profileRepository.getFontysDepartments();
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
