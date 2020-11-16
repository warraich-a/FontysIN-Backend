package service.resources;

import service.PersistenceController;
import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;
import service.repository.DatabaseException;
import service.repository.FakeDataProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.json.simple.JSONObject;


@Path("users")
public class UsersResources {
	private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
	@Context
	private UriInfo uriInfo;

	/*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */


	@GET //GET at http://localhost:XXXX/users/1/contacts
	@Path("{id}/contacts")
	public Response getContacts(@PathParam("id") int id) { // returns users list or contacts list?

		List<ContactDTO> contacts = fakeDataProfile.getAllContactsDTO(id);

		GenericEntity<List<ContactDTO>> entity = new GenericEntity<>(contacts) { };

		return Response.ok(entity).build();
	}

	@GET //GET at http://localhost:XXXX/users/1/acceptedContacts
	@Path("{id}/acceptedContacts")
	public Response getAcceptedContacts(@PathParam("id") int id) { // returns users list or contacts list?

		List<ContactDTO> contacts = fakeDataProfile.getContactsDTO(id);

		GenericEntity<List<ContactDTO>> entity = new GenericEntity<>(contacts) { };

		return Response.ok(entity).build();
	}

	@GET //GET at http://localhost:XXXX/users/1/requests
	@Path("{id}/requests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactsRequests(@PathParam("id") int id) { // returns users list or contacts list?
		List<ContactDTO> requests = fakeDataProfile.getContactsRequestsDTO(id);

		GenericEntity<List<ContactDTO>> entity = new GenericEntity<>(requests) { };

		return Response.ok(entity).build();
	}

	@POST //POST at http://localhost:XXXX/users/1
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/contacts")
	public Response createContact(@PathParam("userId") int userId, ContactDTO contact) {

		int contactId = fakeDataProfile.createContact(contact);
		if (contactId < 0){ // already friends
			//String entity =  "You and user with id " + contact.getFriendId() + " are already connected.";
			String entity =  "You and user with id " + contact.getFriend().getId() + " are already connected.";

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

	// Update contact (used to Accept contact)
	@PATCH //PATCH at http://localhost:XXXX/users/1/contacts/2
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/contacts/{contactId}")
	public Response updateContact(@PathParam("userId") int userId, @PathParam("contactId") int contactId, Contact contact) {
		fakeDataProfile.updateContact(contactId, contact);

		return Response.noContent().build();
	}

	@GET
	@Path("{userId}")
	public Response getUser(@PathParam("userId") int userId) {
		UserDTO user = fakeDataProfile.getUserDTO(userId);

		if(user != null){
			return Response.ok(user).build(); // Status ok 200, return user
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user id").build();
		}
	}
	/*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */


	@GET //GET at http://localhost:XXXX/profile/experiences
	@Path("{userId}/profiles/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetProfile(@PathParam("userId") int userId) {
		PersistenceController persistenceController = new PersistenceController();

		List<Profile> foundProfiles = persistenceController.getProfile(userId); // getting the profile by userid

		if (foundProfiles == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<Profile>> entity = new GenericEntity<>(foundProfiles) {
			};

			return Response.ok(entity).build();

		}
	}

	@GET //GET at http://localhost:XXXX/users/1/profiles/1/experiences/1
	@Path("{userId}/profiles/{profileId}/experiences/{experienceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetExperience(@PathParam("userId") int userId, @PathParam("profileId") int profileId
			, @PathParam("experienceId") int experienceId) {

		List<Experience> foundExperiences = fakeDataProfile.GetExperienceByProfileId(profileId); // getting the experience by profile id

		if(foundExperiences == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid profile id.").build();
		}
		else {

			for (Experience e: foundExperiences){
				if(e.getId() == experienceId){
					List<Experience> experienceByProfileId = fakeDataProfile.GetExperiencesByProfileID(userId,profileId);

					// to combine different types of lists into 1
					List<Object> combined = new ArrayList<>();
					combined.add(experienceByProfileId);

					// to show a list correctly
					GenericEntity<List<Object>> entity = new GenericEntity<>(combined) {
					};

					return Response.ok(entity).build();
				}
			}

			return Response.status(Response.Status.BAD_REQUEST).entity("Experience id does not exist.").build();

		}
	}

	@GET //GET at http://localhost:XXXX/users/1/profiles/1/educations/1
	@Path("{userId}/profiles/{profileId}/educations/{educationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetEducation(@PathParam("userId") int userId, @PathParam("profileId") int profileId
			, @PathParam("educationId") int educationId) {

		PersistenceController controller = new PersistenceController();
		List<Education> foundEducations = controller.getEducations(userId, profileId); // getting the education by profile id

		if(foundEducations == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid profile id.").build();
		}
		else {

			for (Education e: foundEducations){
				if(e.getId() == educationId){
					List<Education> educationByProfileId = controller.getEducations(userId,profileId);

					// to combine different types of lists into 1
					List<Object> combined = new ArrayList<>();
					combined.add(educationByProfileId);

					// to show a list correctly
					GenericEntity<List<Object>> entity = new GenericEntity<>(combined) {
					};

					return Response.ok(entity).build();
				}
			}

			return Response.status(Response.Status.BAD_REQUEST).entity("Education id does not exist.").build();

		}
	}

	@GET //GET at http://localhost:XXXX/users/1/profiles/1/skills/1
	@Path("{userId}/profiles/{profileId}/skills/{skillId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetSkill(@PathParam("userId") int userId, @PathParam("profileId") int profileId
			, @PathParam("skillId") int skillId) {



		List<Skill> foundSkills = fakeDataProfile.GetSkillsByProfileId(profileId); // getting the education by profile id

		if(foundSkills == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid profile id.").build();
		}
		else {

			for (Skill s: foundSkills){
				if(s.getId() == skillId){
					List<Skill> skillByProfileId = fakeDataProfile.GetSkillsByProfileId(profileId);

					// to combine different types of lists into 1
					List<Object> combined = new ArrayList<>();
					combined.add(skillByProfileId);

					// to show a list correctly
					GenericEntity<List<Object>> entity = new GenericEntity<>(combined) {
					};

					return Response.ok(entity).build();
				}
			}

			return Response.status(Response.Status.BAD_REQUEST).entity("Skill id does not exist.").build();

		}
	}


	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/experiences")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetExperiences(@PathParam("userId") int userId, @PathParam("profileId") int profileId, @HeaderParam("visitorId") int visitorId) {

		PersistenceController persistenceController = new PersistenceController();
		List<Experience> experienceByProfileId = persistenceController.getExperience(userId, profileId);

		boolean AllowToSee = fakeDataProfile.AllowedToSee(userId, visitorId, FakeDataProfile.ProfilePart.EXPERIENCE);

		if(AllowToSee){
			if (experienceByProfileId == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
			} else {
				GenericEntity<List<Experience>> entity = new GenericEntity<>(experienceByProfileId) {
				};
				return Response.ok(entity).build();
			}
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("SOrry not sorry").build();
		}

	}
//
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/educations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  GetEducations(@PathParam("userId") int userId, @PathParam("profileId") int profileId, @HeaderParam("visitorId") int visitorId) {
		PersistenceController persistenceController = new PersistenceController();
		List<Education> educations = persistenceController.getEducations(userId, profileId);
		boolean AllowToSee = fakeDataProfile.AllowedToSee(userId, visitorId, FakeDataProfile.ProfilePart.EDUCATION);
		if(AllowToSee){
		if (educations == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		} else {
			GenericEntity<List<Education>> entity = new GenericEntity<>(educations) {
			};
			return Response.ok(entity).build();
		}
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("SOrry not sorry").build();
		}
	}
	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("{userId}/profiles/{profileId}/abouts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  GetAbouts(@PathParam("userId") int userId, @PathParam("profileId") int profileId) {
		PersistenceController persistenceController = new PersistenceController();

		List<About> abouts = persistenceController.getAbout(userId, profileId);

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
	public Response GetSkills(@PathParam("userId") int userId, @PathParam("profileId") int profileId, @HeaderParam("visitorId") int visitorId) {
		PersistenceController persistenceController = new PersistenceController();

		List<Skill> skills = persistenceController.getSkills(userId, profileId);
		boolean AllowToSee = fakeDataProfile.AllowedToSee(userId, visitorId, FakeDataProfile.ProfilePart.SKILLS);
		if(AllowToSee){
			if (skills == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
			} else {
				GenericEntity<List<Skill>> entity = new GenericEntity<>(skills) {
				};
				return Response.ok(entity).build();
			}
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("SOrry not sorry").build();
		}

	}

	// to add a new experience
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/profiles/{profileId}/experiences/new")
	public Response CreateExperience(@PathParam("userId") int userId, @PathParam("profileId") int profileId, Experience e) {
		PersistenceController persistenceController = new PersistenceController();

		if (!persistenceController.addExperience(e))
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

		PersistenceController persistenceController = new PersistenceController();
		if (!persistenceController.addEducation(e))
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

	//delete user's experince with specific id
	@DELETE //DELETE at http://localhost:9099/users/1/profiles/1/experiences/1/
	@Path("{userId}/profiles/{profileID}/experiences/{experinceID}")
	public Response deleteUserExperience(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
										@PathParam("experinceID") int experinceID) {

		PersistenceController controller = new PersistenceController();
		controller.DeleteExperience(userId,profileID,experinceID);

		return Response.noContent().build();
	}

	// DELETE 1//2/3///

	@DELETE //DELETE at http://localhost:9090/users/3/profiles/2/educations/1
	@Path("{userId}/profiles/{profileID}/educations/{educationID}")
	public Response deleteUserEducation(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
										@PathParam("educationID") int educationID) {

		PersistenceController controller = new PersistenceController();
		controller.DeleteEducation(userId,profileID,educationID);

		return Response.noContent().build();
	}


	@DELETE //DELETE at http://localhost:9090/users/1/profiles/1/skills/1
	@Path("{userId}/profiles/{profileID}/skills/{skillId}")
	public Response deleteUserSkill(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
										@PathParam("skillId") int skillId) {

		PersistenceController controller = new PersistenceController();
		controller.DeleteSkill(userId,profileID,skillId);

		return Response.noContent().build();
	}

	// to add a new about
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/profiles/{profileId}/abouts/new")
	public Response CreateAbout(@PathParam("userId") int userId, @PathParam("profileId") int profileId, About a) {

		PersistenceController persistenceController = new PersistenceController();
		if (!persistenceController.addAbout(a))
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

		PersistenceController persistenceController = new PersistenceController();
		if (!persistenceController.addSkill(s, userId))
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

	@GET
	@Path("profile/education/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEducationById(@PathParam("id") int id) {
		Education e = fakeDataProfile.getEducationID(id);
		if (e == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid education id.").build();
		} else {
			return Response.ok(e).build();
		}
	}
	@GET
	@Path("profile/experience/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExperienceById(@PathParam("id") int id) {
		Experience e = fakeDataProfile.getExperienceID(id);
		if (e == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid experience id.").build();
		} else {
			return Response.ok(e).build();
		}
	}
	@GET
	@Path("profile/about/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAboutById(@PathParam("id") int id) {
		About a = fakeDataProfile.GetAboutById(id);
		if (a == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(a).build();
		}
	}

	@GET
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") int id) {
		User u = fakeDataProfile.GetUserById(id);
		if (u == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(u).build();
		}
	}
	@GET
	@Path("address/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddressById(@PathParam("id") int id) {
		Address a = fakeDataProfile.GetAddressById(id);
		if (a == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(a).build();
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
	@PUT //PUT at http://localhost:XXXX/profile/id
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
	@PUT //PUT at http://localhost:9099/users/address/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/address/{id}")
	public Response updateAddress(@PathParam("id") int id, Address a) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (fakeDataProfile.updateAddress(id, a)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid address.").build();
		}
	}
	@GET
	@Path("privacy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrivacyById(@PathParam("id") int id) {
		Privacy a = fakeDataProfile.GetPrivacyById(id);
		if (a == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(a).build();
		}
	}
	@PUT //PUT at http://localhost:9099/users/address/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/privacy/{id}")
	public Response updatePrivacy(@PathParam("id") int id, Privacy p) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (fakeDataProfile.updatePrivacy(id, p)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid address.").build();
		}
	}

	@POST //POST at http://localhost:XXXX/profile/experience
	@Path("{userId}/profiles/new")
	public Response AddProfile(@PathParam("userId") int userId, Profile p) throws DatabaseException, SQLException {

		PersistenceController persistenceController = new PersistenceController();
		int id = persistenceController.addProfile(p, userId);
		if (id == 0)
		{
			String entity =  "Profile Exists";
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			return Response.ok(id).build();
		}
	}

	//filter users by user type department, location, start study year and start work year (searching by filter)
	@GET //GET at http://localhost:9090/users?type= Or ?department= Or ?location= Or ?studyYear= Or ?workingYear=
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFilteredUsers(@QueryParam("type") UserType type, @QueryParam("department") int depId,
									 @QueryParam("location") int locId, @QueryParam("studyYear") int year,
									 @QueryParam("workingYear") int workYear, @QueryParam("firstName") String name) {

		PersistenceController controller = new PersistenceController();

		List<UserDTO> users;

		if (uriInfo.getQueryParameters().containsKey("firstName") && uriInfo.getQueryParameters().containsKey("location")
				&& uriInfo.getQueryParameters().containsKey("department") && uriInfo.getQueryParameters().containsKey("type")) { //filter by user type, location and department and name
			users = controller.UserFilteLocationDepartmentTypeAndName(name,locId, depId, type);
		}
		 else if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("workingYear")
				&& uriInfo.getQueryParameters().containsKey("location") && uriInfo.getQueryParameters().containsKey("department") ) { //filter by user type, location, department and start work year
			users = controller.UserFilterByTypeLocationDepartmentAndStartWorkyearFontysStaff(type, workYear, locId, depId);
		}
		else if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("studyYear")
				&& uriInfo.getQueryParameters().containsKey("location") && uriInfo.getQueryParameters().containsKey("department") ) { //filter by user type, location, department and start study year
			users = controller.UserFilterByTypeLocationDepartmentAndStartSudyYear(type, year, locId, depId);
		}
		else if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("location")
		&& uriInfo.getQueryParameters().containsKey("department")) { //filter by user type, location and department
			users = controller.UserFilterByTypeLocationAndDepartment(type, locId, depId);
		}
		else if (uriInfo.getQueryParameters().containsKey("type")) { //filter by user type
			users = controller.UserFilteredWithType(type);
		}
		else if (uriInfo.getQueryParameters().containsKey("department")){ //filter by department
			users = controller.UserFilteredWithDepartment(depId);
		}
		else if (uriInfo.getQueryParameters().containsKey("location")){  //filter by fontys location
			users = controller.UserFilteredWithLocation(locId);
		}
		else if (uriInfo.getQueryParameters().containsKey("studyYear")){  //filter by start study year at fontys
			users = controller.UserFilteredWithStartStudyYear(year);
		}
		else if (uriInfo.getQueryParameters().containsKey("workingYear")){ //filter by start work year at fontys
			users = controller.UserFilteredWithStartWorkYear(workYear);
		}
		else if (uriInfo.getQueryParameters().containsKey("firstName")){ //filter by name using chars
			users = controller.UserFilterByFirstNameChars(name);
		}
		else {
			users = controller.GetAllUsers();
		}
		GenericEntity<List<UserDTO>> entity = new GenericEntity<>(users) {
		};
		return Response.ok(entity).build();
	}
}
