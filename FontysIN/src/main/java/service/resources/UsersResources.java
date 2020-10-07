package service.resources;

import service.model.*;
import service.repository.FakeDataProfile;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("users")
public class UsersResources {
	private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
	@Context
	private UriInfo uriInfo;

	// CONTACTS

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


	//to get all the experiences
	@GET //GET at http://localhost:XXXX/profile/experiences
	@Path("{userId}/profiles/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetProfile(@PathParam("userId") int userId, @PathParam("profileId") int profileId) {

		List<Profile> foundProfiles = fakeDataProfile.GetProfileByUserId(userId); // getting the profile by userid

		if (foundProfiles == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid student number.").build();
		} else {

			for (Profile p: foundProfiles){
				if(p.getId() == profileId){
					List<Education> educationByProfileId = fakeDataProfile.GetEducationsByProfileId(profileId);
					List<Experience> experienceByProfileId = fakeDataProfile.GetExperiencesByProfileID(profileId);
					List<About> aboutByProfileId = fakeDataProfile.GetAboutByProfileID(profileId);
					List<Skill> skillByProfileId = fakeDataProfile.GetSkillByProfileID(profileId);

					// to combine different types of lists into 1
					List<Object> combined = new ArrayList<>();
					combined.add(educationByProfileId);
					combined.add(experienceByProfileId);
					combined.add(aboutByProfileId);
					combined.add(skillByProfileId);

					// to show a list correctly
					GenericEntity<List<Object>> entity = new GenericEntity<>(combined) {
					};

					return Response.ok(entity).build();
				}
			}

			return Response.status(Response.Status.BAD_REQUEST).entity("Profile does not exist.").build();

		}
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/experiences")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetExperiences(@PathParam("userId") int userId, @PathParam("profileId") int profileId1) {
		List<Experience> experiences = fakeDataProfile.GetExperiencesByProfileID(profileId1);

		GenericEntity<List<Experience>> entity = new GenericEntity<>(experiences) {
		};
		return Response.ok(entity).build();
	}
//
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/educations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  GetEducations(@PathParam("userId") int userId, @PathParam("profileId") int profileId1) {
		List<Education> educations = fakeDataProfile.GetEducationsByProfileId(profileId1);

		GenericEntity<List<Education>> entity = new GenericEntity<>(educations) {
		};
		return Response.ok(entity).build();
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/abouts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  GetAbouts(@PathParam("userId") int userId, @PathParam("profileId") int profileId1) {
		List<About> abouts = fakeDataProfile.GetAboutByProfileID(profileId1);

		GenericEntity<List<About>> entity = new GenericEntity<>(abouts) {
		};
		return Response.ok(entity).build();
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/skills")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetSkills(@PathParam("userId") int userId, @PathParam("profileId") int profileId1) {
		List<Skill> skills = fakeDataProfile.GetSkillByProfileID(profileId1);

		GenericEntity<List<Skill>> entity = new GenericEntity<>(skills) {
		};
		return Response.ok(entity).build();
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

//	//delete user's experince with specific id
//	@DELETE //DELETE at http://localhost:9090/users/1/profiles/1/experiences/1/
	// DELETE 1//2/3/
//	@Path("{userId}/{profileID}/{experinceID}") // userId'/profiles/profileId/experiences/experienceId
//	public Response deleteUserExperience(@PathParam("userId") int userId ,@PathParam("profileID") int profileID, @PathParam("experinceID") int experinceID) {
//		fakeDataProfile.deleteExperience(userId, profileID, experinceID);
//
//		return Response.noContent().build();
//	}
//
	// DELETE 1//2/3///
//	//delete user's education with specific id
//	@DELETE //DELETE at http://localhost:9090/users/1/profiles/2/educations/1
//	@Path("{userId}/{profileID}/{educationID}")
//	public Response deleteUserEducation(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
//										@PathParam("educationID") int educationID) {
//		fakeDataProfile.deleteEducation(userId, profileID, educationID);
//
//		return Response.noContent().build();
//	}
//
//	//delete user's skill with specific id
//	@DELETE //DELETE at http://localhost:9090/users/1/profile/1/skill/1
//	@Path("{userId}/{profileID}/{skillID}")
//	public Response deleteUserSkill(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
//									@PathParam("skillID") int skillID) {
//		fakeDataProfile.deleteSkill(userId, profileID, skillID);
//
//		return Response.noContent().build();
//	}

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
	@PUT //PUT at http://localhost:XXXX/users/profile/about/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/profile/about/{id}")
	public Response updateAbout(@PathParam("id") int id, About a) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (fakeDataProfile.updateAbout(id, a)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid about.").build();
		}
	}

	@PUT //PUT at http://localhost:XXXX/users/profile/education/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/profile/education/{id}")
	public Response updateEducation(@PathParam("id") int id, Education e) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (fakeDataProfile.updateEducation(id, e)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid education.").build();
		}
	}
	@PUT //PUT at http://localhost:XXXX/users/profile/experience/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/profile/experience/{id}")
	public Response updateExperience(@PathParam("id") int id, Experience e) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (fakeDataProfile.updateExperience(id, e)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid experience.").build();
		}
	}
	@PUT //PUT at http://localhost:XXXX/profile/information
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}")
	public Response updateUser(@PathParam("userId") int id, User user) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (fakeDataProfile.updateUser(id, user)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid id.").build();
		}
	}
}
