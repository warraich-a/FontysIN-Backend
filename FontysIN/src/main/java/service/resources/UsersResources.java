package service.resources;

import service.model.*;
import service.repository.FakeDataProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
//import org.json.simple.JSONObject;


@Path("users")
public class UsersResources {
	private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
	@Context
	private UriInfo uriInfo;

	//to get the user data
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GeUser(@PathParam("userId") int userId) {
		User user = fakeDataProfile.getUser(userId);

		return Response.ok(user).build();
	}


	@GET //GET at http://localhost:XXXX/users/1/contacts
	@Path("{id}/contacts")
	public Response getContacts(@PathParam("id") int id) {
		List<User> contacts = fakeDataProfile.getContacts(id);
		System.out.println("Contacts" + contacts.size());


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



	@GET //GET at http://localhost:XXXX/profile/experiences
	@Path("{userId}/profiles/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetProfile(@PathParam("userId") int userId) {

		List<Profile> foundProfiles = fakeDataProfile.GetProfileByUserId(userId); // getting the profile by userid

		if (foundProfiles == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<Profile>> entity = new GenericEntity<>(foundProfiles) {
			};

			return Response.ok(entity).build();

		}
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/experiences")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetExperiences(@PathParam("userId") int userId, @PathParam("profileId") int profileId) {
		List<Experience> experienceByProfileId = fakeDataProfile.GetExperiencesByProfileID(userId, profileId);

		if (experienceByProfileId == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<Experience>> entity = new GenericEntity<>(experienceByProfileId) {
			};
			return Response.ok(entity).build();
		}
	}
//
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/educations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  GetEducations(@PathParam("userId") int userId, @PathParam("profileId") int profileId) {
		List<Education> educations = fakeDataProfile.GetEducationsByProfileId(userId, profileId);

		if (educations == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<Education>> entity = new GenericEntity<>(educations) {
			};
			return Response.ok(entity).build();
		}
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/abouts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  GetAbouts(@PathParam("userId") int userId, @PathParam("profileId") int profileId) {
		List<About> abouts = fakeDataProfile.GetAboutByProfileID(userId, profileId);

		if (abouts == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<About>> entity = new GenericEntity<>(abouts) {
			};
			return Response.ok(entity).build();
		}
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/skills")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetSkills(@PathParam("userId") int userId, @PathParam("profileId") int profileId) {
		List<Skill> skills = fakeDataProfile.GetSkillsByProfileId(userId, profileId);

		if (skills == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<Skill>> entity = new GenericEntity<>(skills) {
			};
			return Response.ok(entity).build();
		}
	}

	// to add a new experience
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/profiles/{profileId}/experiences/new")
	public Response CreateExperience(@PathParam("userId") int userId, @PathParam("profileId") int profileId, Experience e) {

		if (!fakeDataProfile.AddExperience(e,  userId, profileId))
		{
			String entity =  "Experience Exists";
			// throw new Exception(Response.Status.CONFLICT, "This topic already exists");
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + e.getId(); //
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}
	// to add a new experience
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/profiles/{profileId}/educations/new")
	public Response CreateEducation(@PathParam("userId") int userId, @PathParam("profileId") int profileId, Education e) {

		if (!fakeDataProfile.AddEducation(e, userId, profileId))
		{
			String entity =  "Education Exists";
			// throw new Exception(Response.Status.CONFLICT, "This topic already exists");
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + e.getId(); //
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}
	// to add a new about
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/profiles/{profileId}/abouts/new")
	public Response CreateAbout(@PathParam("userId") int userId, @PathParam("profileId") int profileId, About a) {

		if (!fakeDataProfile.AddAbout(a,  userId, profileId))
		{
			String entity =  "About Exists";
			// throw new Exception(Response.Status.CONFLICT, "This topic already exists");
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + a.getId(); //
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}

	// to add a new skill
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/profiles/{profileId}/skills/new")
	public Response CreateAbout(@PathParam("userId") int userId, @PathParam("profileId") int profileId, Skill s) {

		if (!fakeDataProfile.AddSkill(s,  userId, profileId))
		{
			String entity =  "SKill Exists";
			// throw new Exception(Response.Status.CONFLICT, "This topic already exists");
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + s.getId(); // url of the created student
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}

	@POST //POST at http://localhost:XXXX/profile/experience
	@Path("{userId}/profiles/new")
	public Response AddProfile(@PathParam("userId") int userId, Profile p) {


		if (!fakeDataProfile.AddProfile(userId, p))
		{
			String entity =  "Profile Exists";
			// throw new Exception(Response.Status.CONFLICT, "This topic already exists");
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			int Id = p.getId();
//			String url = uriInfo.getAbsolutePath() + "/" + p.getId(); // url of the created student
			URI uri = URI.create(String.valueOf(Id));
			return Response.ok(p.getId()).build();
			//return Response.con;
		}
	}
}
