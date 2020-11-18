package service.resources;

import service.controller.MessageController;
import service.model.Conversation;
import service.model.Message;

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
    public Response getConversations(@PathParam("id") int id) { // returns users list or contacts list?
        List<Conversation> conversations = messageController.getConversations(id);

        GenericEntity<List<Conversation>> entity = new GenericEntity<>(conversations) { };

        return Response.ok(entity).build();
    }

    @POST //POST at http://localhost:XXXX/users/1/messages
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(@PathParam("id") int userId, Message message) {
        int messageId = messageController.createMessage(message);

        String url = uriInfo.getAbsolutePath() + "/" + messageId; // url of the created message
        URI uri = URI.create(url);
        return Response.created(uri).build();
    }
}
