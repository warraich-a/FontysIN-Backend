package service.resources;

import service.controller.MessageController;
import service.model.Conversation;
import service.model.Message;
import service.model.dto.ConversationDTO;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("users/{id}/messages")
public class MessagesResources {
    MessageController messageController = new MessageController();
    @Context
    private UriInfo uriInfo;

    @GET //GET at http://localhost:XXXX/users/1/messages
    public Response getConversations(@PathParam("id") int id) { // returns list of conversations
        List<Conversation> conversations = messageController.getConversations(id);

        GenericEntity<List<Conversation>> entity = new GenericEntity<>(conversations) { };

        return Response.ok(entity).build();
    }

    @GET //GET at http://localhost:XXXX/users/1/messages/1
    @Path("{conversationId}")
    public Response getConversation(@PathParam("id") int id, @PathParam("conversationId") int conversationId) { // returns a conversation
        Conversation conversations = messageController.getConversation(conversationId);


        return Response.ok(conversations).build();
    }

    @POST //POST at http://localhost:XXXX/users/1/messages
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(@PathParam("id") int userId, Message message) {
        int messageId = messageController.createMessage(message);

        String url = uriInfo.getAbsolutePath() + "/" + messageId; // url of the created message
        URI uri = URI.create(url);
        return Response.created(uri).build();
    }

    // delete conversation
    @DELETE //DELETE at http://localhost:XXXX/users/1/conversation/1
    @Path("user/{userId}/conversation/{conversationId}")
    @PermitAll
    public Response deleteConversation(@PathParam("userId") int userId, @PathParam("conversationId") int conversationId) {

        messageController.DeleteConversation(userId, conversationId);

        return Response.noContent().build();
    }

    //add conversation with conversationdto object
    @POST //POST at http://localhost:XXXX/users/1/messages/newConversation
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("newConversation")
    @PermitAll
    public Response StartNewConversation(ConversationDTO conversationDTO) {
        MessageController messageController = new MessageController();

//        !messageController.startNewConversation(conversationDTO)
        int id = messageController.startNewConversation(conversationDTO);
        if (id < 0){
            String entity =  "Conversation with this id is "  + id + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        }
        else {
            String url = uriInfo.getAbsolutePath() + "/"; // url of the posted conversation
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }
}
