package service.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.model.Like;
import service.model.dto.ConversationDTO;
import service.repository.DatabaseException;
import service.repository.MessagesRepository;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConversationControllerTest {

    @InjectMocks
    MessageController messageController;

    @Mock
    MessagesRepository messagesRepository;

//    @Test
//    void AddNewConversationTest() throws DatabaseException, URISyntaxException {
//
//        ConversationDTO dto =new ConversationDTO(1,1);
//
//        when(messagesRepository.startConversation(dto)).thenReturn(1);
//
//        boolean startedConversation = messageController.startNewConversation(dto);
//
//        Assert.assertEquals(true,startedConversation);
//
//    }
//
//    @Test
//    void DeleteExistingConversation(){
//
//        when(messageController.DeleteConversation(1, 1)).thenReturn(true);
//
//        boolean result = messageController.DeleteConversation(1, 1);
//
//        Assert.assertEquals(true, result);
//
//    }

}
