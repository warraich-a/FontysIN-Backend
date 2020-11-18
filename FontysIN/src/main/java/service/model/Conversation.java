package service.model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private int id;
    private List<Message> messages;

    public Conversation(int id, List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    public Conversation(int id) {
        this.id = id;
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", messages=" + messages +
                '}';
    }
}
