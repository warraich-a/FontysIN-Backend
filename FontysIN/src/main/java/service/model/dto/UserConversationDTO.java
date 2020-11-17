package service.model.dto;

import service.model.Conversation;

import java.util.List;

public class UserConversationDTO {
    private int id;
    private int profileId;
    private String firstName;
    private String lastName;
    private String image;
    private List<Conversation> conversations;

    public UserConversationDTO(int id, int profileId, String firstName, String lastName, String image, List<Conversation> conversations) {
        this.id = id;
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.conversations = conversations;
    }

    public UserConversationDTO() {
    }

    public int getId() {
        return id;
    }

    public int getProfileId() {
        return profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @Override
    public String toString() {
        return "UserConversationDTO{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", conversations=" + conversations +
                '}';
    }
}
