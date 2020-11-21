package service.model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private int id;
    private Message message;
    private List<Message> messages;

    public Conversation(int id, List<Message> messages) {
        this.id = id;
        this.messages = messages;
        this.message = new Message();
    }

    public Conversation(int id) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.message = new Message();
    }

    public Conversation(int id, Message message) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.message = new Message();
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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", messages=" + messages +
                '}';
    }
}
