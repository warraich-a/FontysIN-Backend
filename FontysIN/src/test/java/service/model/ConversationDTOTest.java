package service.model;

import org.junit.jupiter.api.Test;
import service.model.dto.ConversationDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversationDTOTest {

    @Test //check whether user info are correct
    void NewConversationTest(){

        ConversationDTO conversationDTO = new ConversationDTO(1,2);

        assertEquals(1, conversationDTO.getFirstUserId());
        assertEquals(2, conversationDTO.getSecondUserId());
    }
}
