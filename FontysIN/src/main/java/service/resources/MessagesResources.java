package service.resources;

import service.PersistenceController;
import service.controller.MessageController;
import service.model.Message;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("users/{id}")
public class MessagesResources {
    private final MessageController messageController = new MessageController();
    @Context
    private UriInfo uriInfo;

    @POST //POST at http://localhost:XXXX/users/1
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/messages")
    public Response createMessage(@PathParam("id") int userId, Message message) {
        PersistenceController controller = new PersistenceController();
        int messageId = messageController.createMessage(message);

        String url = uriInfo.getAbsolutePath() + "/" + messageId; // url of the created message
        URI uri = URI.create(url);
        return Response.created(uri).build();
    }

}
