package service.controller;

import service.model.Conversation;
import service.model.Message;
import service.model.dto.ConversationDTO;
import service.repository.DatabaseException;
import service.repository.MessagesRepository;

import java.net.URISyntaxException;
import java.util.List;

public class MessageController {

    MessagesRepository messagesRepository = new MessagesRepository();
    /**
     *
     * @param message
     * @return the id of the created message
     */
    public int createMessage(Message message) {

        int messageId = -1;
        try {
            messageId =  messagesRepository.createMessage(message);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return messageId;
    }

    /***
     *
     * @param id
     * @return list of conversations of a specific user
     */
    public List<Conversation> getConversations(int id) {

        List<Conversation> conversations = null;
        try {
            conversations =  messagesRepository.getConversations(id);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return conversations;
    }

    /***
     *
     * @param id of conversation
     * @return conversation with specified id
     */
    public Conversation getConversation(int id) {

        Conversation conversation = null;
        try {
            conversation =  messagesRepository.getConversation(id);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
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

        try {
            return messagesRepository.deleteConversation(userId, conversationId);
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param conversationDTO
     * @return the id of the created conversation
     */
    public int startNewConversation(ConversationDTO conversationDTO) {

        int conversationId = -1;
        try {
            if (!messagesRepository.restartNewConversation(conversationDTO)){
                conversationId = messagesRepository.startConversation(conversationDTO);
            }
            else{
                conversationId = messagesRepository.startConversation(conversationDTO);
            }

        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return conversationId;
    }


}
