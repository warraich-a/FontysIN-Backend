package Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Conversation;
import service.model.User;
import service.model.dto.UserDTO;

public class ConversationTest {

    @Test //check whether user info are correct
    void NewConversationTest(){

        UserDTO u1 = new UserDTO(1, 1, "Ranim", "Alayoubi", "image123.png");
        UserDTO u2 = new UserDTO(2, 2, "Jwana", "Alayoubi", "image321.png");

        Conversation conversation = new Conversation(1,u1,u2);

        assertEquals(u1, conversation.getFirstUser());
        assertEquals(u2, conversation.getSecondUser());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // first user null
    void getFirstUserWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First user must not be null");

        Conversation c = new Conversation();
        c.setFirstUser(null);
    }

    @Test // second user null
    void getSecondUserWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Second user must not be null");

        Conversation c = new Conversation();
        c.setSecondUser(null);
    }


}
