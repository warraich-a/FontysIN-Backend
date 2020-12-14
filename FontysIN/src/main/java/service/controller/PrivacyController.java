package service.controller;


import service.model.Privacy;
import service.model.User;
import service.model.dto.ContactDTO;
import service.repository.DatabaseException;
import service.repository.JDBCPrivacyRepository;

import java.util.ArrayList;
import java.util.List;
import service.model.*;
import service.repository.*;

public class PrivacyController {
    PrivacyRepository controller = new PrivacyRepository();
    public Privacy getPrivacy(User u){

        try {
            Privacy exp = (Privacy) controller.getPrivacyByUser(u);

            System.out.println("ok");

            return exp;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePri(Privacy edu){

        try {
            return controller.updatePrivacy(edu);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Privacy GetPrivacySetting(int id){

        try{
            List<Privacy> privacyList = controller.getPrivacyList();
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
    public boolean AllowedToSee(int userId, int loggedinId, ProfilePart profilePart){
        ProfileController profileController = new ProfileController();

        ContactController contactController = new ContactController();
        User loggedIn = profileController.getUser(loggedinId); // The logged in user
        Privacy settings = GetPrivacySetting(userId);// Get privacy settings for the user i am visiting
        User userImVisiting = profileController.getUser(userId); // So if im logged in user 3 and visit 5
        List<ContactDTO> friends = new ArrayList<>();
        friends = controller.getAllContactsDTO(userImVisiting.getId());
        List<Integer> friendsId = new ArrayList<>();
        for (ContactDTO f: friends) { // Same as Denys :D
            friendsId.add(f.getFriend().getId());
        }
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
        if(userImVisiting.getId() == loggedIn.getId()){ // So am i visting my own page
            return true;
        }
        else if(privacySetting == Privacy.Setting.EVERYONE){
            return true;
        }
        else if(privacySetting == Privacy.Setting.CONNECTIONS){

            if(friendsId.contains(loggedIn.getId())){
                return true;
            }
        }
        return false;
    }

    public enum ProfilePart
    {
        EDUCATION, EXPERIENCE, SKILLS
    }
}
