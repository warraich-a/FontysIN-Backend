package service.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Conversation;
import service.model.Message;
import service.model.dto.ConversationDTO;
import service.model.dto.UserDTO;
import service.repository.DatabaseException;
import service.repository.MessagesRepository;

import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConversationControllerTest {

    @InjectMocks
    MessageController messageController;

    @Mock
    MessagesRepository messagesRepository;

    @Test
    public void getMessages() throws DatabaseException, URISyntaxException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        List<Message> messages = Arrays.asList(new Message(1, 1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(6, 0, "Kalina", "Petrova", "assets/kalina image"),"content",timestamp));

        Conversation conversation = new Conversation(1,messages);
        when(messagesRepository.getConversations(1)).thenReturn(Arrays.asList(conversation));

        List<Conversation> conversationList = messageController.getConversations(1);

        Assert.assertEquals(messages.size(), conversationList.size());
    }

    @Test
    public void StartConversation() throws DatabaseException, URISyntaxException {

        ConversationDTO dto = new ConversationDTO(1,1,4);

        when(messagesRepository.startConversation(dto)).thenReturn(1);

        int conId = messageController.startNewConversation(dto);

        assertEquals(1,conId);

    }

    @Test
    void DeleteExistingConversation(){

        when(messageController.DeleteConversation(1, 1)).thenReturn(true);

        boolean result = messageController.DeleteConversation(1, 1);

        Assert.assertEquals(true, result);

    }

    @Test
    void CreateMessage() throws DatabaseException, URISyntaxException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Message message = new Message(1, 1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(6, 0, "Kalina", "Petrova", "assets/kalina image"),"content",timestamp);

        when(messagesRepository.createMessage(message)).thenReturn(1);

        int msgId = messageController.createMessage(message);

        assertEquals(1,msgId);
    }

    @Test
    void GetConversationById() throws DatabaseException, URISyntaxException {

        Conversation conversation = new Conversation(1, new UserDTO(1, 1, "Rawan", "Abou Dehn", "assets/rawan image"), new UserDTO(6, 0, "Kalina", "Petrova", "assets/kalina image"));
        when(messagesRepository.getConversation(1)).thenReturn(conversation);

        Conversation conversation1 = messageController.getConversation(1);

        assertEquals(conversation,conversation1);
    }

}
