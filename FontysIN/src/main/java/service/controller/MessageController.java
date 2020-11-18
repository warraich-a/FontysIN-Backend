package service.controller;

import service.model.Conversation;
import service.model.Message;
import service.repository.DatabaseException;
import service.repository.MessagesRepository;

import java.util.List;

public class MessageController {
    /**
     *
     * @param message
     * @return the id of the created message
     */
    public int createMessage(Message message) {
        MessagesRepository messagesRepository = new MessagesRepository();

        int messageId = -1;
        try {
            messageId =  messagesRepository.createMessage(message);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return messageId;
    }

    /***
     *
     * @param id
     * @return list of conversations of a specific user
     */
    public List<Conversation> getConversations(int id) {
        MessagesRepository messagesRepository = new MessagesRepository();

        List<Conversation> conversations = null;
        try {
            conversations =  messagesRepository.getConversations(id);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conversations;
    }


}
