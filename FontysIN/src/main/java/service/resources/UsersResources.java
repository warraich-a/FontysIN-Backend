package service.resources;

import service.model.Contact;
import service.repository.FakeDataProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("users")
public class UsersResources {
	private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
	@Context
	private UriInfo uriInfo;


	@GET //GET at http://localhost:XXXX/users/1/contacts
	@Path("{id}/contacts")
	public Response getContacts(@PathParam("id") int id) { // returns users list or contacts list?
		List<Contact> contacts = fakeDataProfile.getContacts(id);
		System.out.println("Contacts" + contacts.size());


		GenericEntity<List<Contact>> entity = new GenericEntity<>(contacts) { };

		return Response.ok(entity).build();
	}

	@POST //POST at http://localhost:XXXX/users/1
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/contacts")
	public Response createContact(@PathParam("userId") int userId, Contact contact) {
		int contactId = fakeDataProfile.createContact(contact);
		if (contactId < 0){ // already friends
			String entity =  "You and user with id " + contact.getFriendId() + " are already connected.";
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + contactId; // url of the created contact
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}

	@DELETE //DELETE at http://localhost:XXXX/users/1/contacts/2
	@Path("{userId}/contacts/{contactId}")
	public Response deleteContact(@PathParam("userId") int userId, @PathParam("contactId") int contactId) {
		fakeDataProfile.deleteContact(userId, contactId);

		return Response.noContent().build();
	}

	// Accept contact
	@PATCH //PATCH at http://localhost:XXXX/users/1/contacts/2
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/contacts/{contactId}")
	public Response acceptContact(@PathParam("userId") int userId, @PathParam("contactId") int contactId) {
		// need to check if userId is a contact
		fakeDataProfile.acceptContact(contactId);
		//fakeDataProfile.acceptContact(contactId, contactStatus);

		return Response.noContent().build();
	}


}
