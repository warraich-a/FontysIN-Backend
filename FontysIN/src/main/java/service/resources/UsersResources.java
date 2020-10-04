package service.resources;

import service.model.User;
import service.repository.FakeDataProfile;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("users")
public class UsersResources {
	private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
	@Context
	private UriInfo uriInfo;


	@GET //GET at http://localhost:XXXX/users/1/contacts
	@Path("{id}/contacts")
	public Response getContacts(@PathParam("id") int id) {
		List<User> contacts = fakeDataProfile.getContacts(id);
		
		GenericEntity<List<User>> entity = new GenericEntity<>(contacts) { };

		return Response.ok(entity).build();
	}

	@POST //POST at http://localhost:XXXX/users/1/2
	@Path("{userId}/{friendId}")
	public Response createContact(@PathParam("userId") int userId, @PathParam("friendId") int friendId) {
		int contactId = fakeDataProfile.createContact(userId, friendId);
		if (contactId < 0){ // already friends
			String entity =  "You and user with id " + friendId + " are already connected.";
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + contactId; // url of the created contact
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}

	@DELETE //DELETE at http://localhost:XXXX/users/1/2
	@Path("{userId}/{friendId}")
	public Response deleteContact(@PathParam("userId") int userId, @PathParam("friendId") int friendId) {
		fakeDataProfile.deleteContact(userId, friendId);

		return Response.noContent().build();
	}

	// Accept contact
	@PATCH //PATCH at http://localhost:XXXX/users/1/2
	@Path("{friendId}/{userId}")
	public Response acceptContact(@PathParam("friendId") int friendId, @PathParam("userId") int userId) {
		fakeDataProfile.acceptContact(friendId, userId);

		return Response.noContent().build();
	}



}
