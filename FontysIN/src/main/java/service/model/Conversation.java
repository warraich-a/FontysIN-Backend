package service.model;

import service.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private int id;
    private UserDTO firstUser;
    private UserDTO secondUser;
    private List<Message> messages;

    public Conversation(int id, List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    public Conversation(int id, UserDTO firstUser, UserDTO secondUser) {
        this.id = id;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(UserDTO firstUser) {
        this.firstUser = firstUser;
    }

    public UserDTO getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(UserDTO secondUser) {
        this.secondUser = secondUser;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                ", messages=" + messages +
                '}';
    }
}
