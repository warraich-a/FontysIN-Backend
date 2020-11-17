package service.resources;

import service.controller.MessageController;
import service.model.Conversation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("users/{id}/messages")
public class MessagesResources {
    MessageController messageController = new MessageController();
    @Context
    private UriInfo uriInfo;
    
    @GET //GET at http://localhost:XXXX/users/1/contacts
    public Response getConversations(@PathParam("id") int id) { // returns users list or contacts list?
        List<Conversation> conversations = messageController.getConversations(id);

        GenericEntity<List<Conversation>> entity = new GenericEntity<>(conversations) { };

        return Response.ok(entity).build();
    }
}
