package service.controller;

import service.model.Conversation;
import service.model.Message;
import service.model.dto.ConversationDTO;
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

    /***
     *
     * @param id of conversation
     * @return conversation with specified id
     */
    public Conversation getConversation(int id) {
        MessagesRepository messagesRepository = new MessagesRepository();

        Conversation conversation = null;
        try {
            conversation =  messagesRepository.getConversation(id);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conversation;
    }

    /***
     *
     * @param userId of user
     * @param conversationId of conversation
     * @return true when the raw in db is updated
     */
    public boolean DeleteConversation(int userId, int conversationId){

        MessagesRepository messagesRepository = new MessagesRepository();

        try {
            return messagesRepository.deleteConversation(userId, conversationId);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param conversationDTO
     * @return the id of the created conversation
     */
    public boolean startNewConversation(ConversationDTO conversationDTO) {

        MessagesRepository messagesRepository = new MessagesRepository();

        try {
            if (!messagesRepository.restartNewConversation(conversationDTO)){
                messagesRepository.startNewConversation(conversationDTO);
            }
            System.out.println("return true if correct");

            return true;
        } catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
    }


}
