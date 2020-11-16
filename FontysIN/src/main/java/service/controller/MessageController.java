package service.controller;

import service.model.Message;
import service.repository.DatabaseException;
import service.repository.MessagesRepository;

public class MessageController {
    /**
     *
     * @param message
     * @return Create new message
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
}
