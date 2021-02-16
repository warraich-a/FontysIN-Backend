package service.model;

import service.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Conversation() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return id == that.id &&
                Objects.equals(firstUser, that.firstUser) &&
                Objects.equals(secondUser, that.secondUser) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstUser, secondUser, messages);
    }
}
