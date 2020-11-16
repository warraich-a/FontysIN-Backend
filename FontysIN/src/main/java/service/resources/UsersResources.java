package service.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import service.PersistenceController;
import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;
import service.repository.DatabaseException;
import service.repository.FakeDataProfile;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
//import org.json.simple.JSONObject;


@Path("users")
public class UsersResources {
	private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
	PersistenceController persistenceController = new PersistenceController();
	@Context
	private UriInfo uriInfo;

	/*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */

	@GET //GET at http://localhost:XXXX/users/1/contacts
	@Path("{id}/contacts")
	public Response getContacts(@PathParam("id") int id) { // returns users list or contacts list?
		System.out.println("Contacts route");

//		List<ContactDTO> contacts = fakeDataProfile.getAllContactsDTO(id);
		PersistenceController controller = new PersistenceController();
		List<ContactDTO> contacts = controller.getAllContactsDTO(id);

		GenericEntity<List<ContactDTO>> entity = new GenericEntity<>(contacts) { };

		return Response.ok(entity).build();
	}

	@GET //GET at http://localhost:XXXX/users/1/acceptedContacts
	@Path("{id}/acceptedContacts")
	public Response getAcceptedContacts(@PathParam("id") int id) { // returns users list or contacts list?
		System.out.println("Accepted Contacts route");

//		List<ContactDTO> contacts = fakeDataProfile.getContactsDTO(id);
		PersistenceController controller = new PersistenceController();
		List<ContactDTO> contacts = controller.getAcceptedContactsDTO(id);

		GenericEntity<List<ContactDTO>> entity = new GenericEntity<>(contacts) { };

		return Response.ok(entity).build();
	}

	@GET //GET at http://localhost:XXXX/users/1/requests
	@Path("{id}/requests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactsRequests(@PathParam("id") int id) { // returns users list or contacts list?
//		List<ContactDTO> requests = fakeDataProfile.getContactsRequestsDTO(id);
		PersistenceController controller = new PersistenceController();
		List<ContactDTO> requests = controller.getContactsRequestsDTO(id);

		GenericEntity<List<ContactDTO>> entity = new GenericEntity<>(requests) { };

		return Response.ok(entity).build();
	}

	@POST //POST at http://localhost:XXXX/users/1
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/contacts")
	public Response createContact(@PathParam("userId") int userId, ContactDTO contact) {

//		int contactId = fakeDataProfile.createContact(contact);
		PersistenceController controller = new PersistenceController();
		int contactId = controller.createContact(contact);

		System.out.println("Contact id route " + contactId);

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
//		fakeDataProfile.deleteContact(userId, contactId);
		PersistenceController controller = new PersistenceController();
		controller.deleteContact(userId, contactId);

		return Response.noContent().build();
	}

	// Update contact (used to Accept contact)
	@PATCH //PATCH at http://localhost:XXXX/users/1/contacts/2
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}/contacts/{contactId}")
	public Response updateContact(@PathParam("userId") int userId, @PathParam("contactId") int contactId, Contact contact) {
//		fakeDataProfile.updateContact(contactId, contact);
		PersistenceController controller = new PersistenceController();
		controller.updateContact(contactId, contact);

		return Response.noContent().build();
	}

	@GET
	@Path("{userId}")
	public Response getUser(@PathParam("userId") int userId) {
//		UserDTO user = fakeDataProfile.getUserDTO(userId);
		PersistenceController controller = new PersistenceController();
		UserDTO user = controller.getUserDTO(userId);

		if(user != null){
			return Response.ok(user).build(); // Status ok 200, return user
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user id").build();
		}
	}
	/*------------------------------------------------------------------------------- Contacts ----------------------------------------------------------------------------- */

	@GET //GET at http://localhost:XXXX/profile/educations
	@Path("p/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GeUser(@PathParam("userId") int userId) {
		PersistenceController persistenceController = new PersistenceController();
		User u = persistenceController.getUser(userId);
		System.out.println("User id " + userId);
		System.out.println("Got user by id " + u);
		if (u == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(u).build();
		}
	}



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

		List<Education> foundEducations = fakeDataProfile.GetEducationsByProfileId(userId ,profileId); // getting the education by profile id

		if(foundEducations == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid profile id.").build();
		}
		else {

			for (Education e: foundEducations){
				if(e.getId() == educationId){
					List<Education> educationByProfileId = fakeDataProfile.GetEducationsByProfileId(userId,profileId);

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

		boolean AllowToSee = persistenceController.AllowedToSee(userId, visitorId, PersistenceController.ProfilePart.EXPERIENCE);

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
		boolean AllowToSee = persistenceController.AllowedToSee(userId, visitorId, PersistenceController.ProfilePart.EDUCATION);
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
		boolean AllowToSee = persistenceController.AllowedToSee(userId, visitorId, PersistenceController.ProfilePart.SKILLS);
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

	//delete user's education with specific id
	@DELETE //DELETE at http://localhost:9090/users/3/profiles/2/educations/1
	@Path("{userId}/profiles/{profileID}/educations/{educationID}")
	public Response deleteUserEducation(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
										@PathParam("educationID") int educationID) {

		PersistenceController controller = new PersistenceController();
		controller.DeleteEducation(userId,profileID,educationID);

		return Response.noContent().build();
	}

	//delete user's skill with specific id
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
		Education e = persistenceController.getEdu(id);
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
		Experience e = persistenceController.getExp(id);
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
		About a = persistenceController.getAbo(id);
		if (a == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(a).build();
		}
	}

	@GET
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") int id, @HeaderParam("Authorization") String auth) {
		if(!persistenceController.isIdAndAuthSame(id, auth)){
			return Response.status(Response.Status.UNAUTHORIZED).
					entity("Invalid email and/or password.").build();
		}else {

			User u = persistenceController.getUser(id);
			if (u == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
			} else {
				return Response.ok(u).build();
			}
		}
	}
	@GET
	@Path("address/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddressById(@PathParam("id") int id, @HeaderParam("Authorization") String auth) {

			Address a = persistenceController.getAddress(id);
			if (a == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
			} else {
				return Response.ok(a).build();
			}


	}


	@PUT //PUT at http://localhost:XXXX/users/profile/about/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/profile/about/{id}")
	public Response updateAbout(About a) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (persistenceController.updateAbo(a)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid about.").build();
		}
	}

	@PUT //PUT at http://localhost:XXXX/users/profile/education/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/profile/education/{id}")
	public Response updateEducation(Education e) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (persistenceController.updateEdu(e)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid education.").build();
		}
	}
	@PUT //PUT at http://localhost:XXXX/users/profile/experience/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/profile/experience/{id}")
	public Response updateExperience(Experience e) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (persistenceController.updateExp(e)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid experience.").build();
		}
	}
	@PUT //PUT at http://localhost:XXXX/profile/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userId}")
	public Response updateUserPhone(User user) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (persistenceController.updatePh(user)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid id.").build();
		}
	}
	@PUT //PUT at http://localhost:9099/users/address/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/address/{id}")
	public Response updateAddress(Address a) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (persistenceController.updateAd(a)) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid address.").build();
		}
	}
	@GET
	@Path("privacy/me")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrivacy(@HeaderParam("Authorization") String auth) {
		String encodedCredentials = auth.replaceFirst("Basic ", "");
		String credentials = new
				String(Base64.getDecoder().decode(encodedCredentials.getBytes()));
		//Split username and password tokens in credentials
		final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
		final String email = tokenizer.nextToken();
		User u = persistenceController.getUserByEmail(email);
		Privacy a = persistenceController.getPrivacy(u);
		if (a == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid about id.").build();
		} else {
			return Response.ok(a).build();
		}
	}
	@PUT //PUT at http://localhost:9099/users/address/id
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/privacy/{id}")
	public Response updatePrivacy(Privacy p) {
		// Idempotent method. Always update (even if the resource has already been updated before).
		if (persistenceController.updatePri(p)) {
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
									 @QueryParam("workingYear") int workYear) {

		PersistenceController controller = new PersistenceController();

		List<User> users;
		//If query parameter is missing return all users. Otherwise filter users by given user type fontys staff location and department and start work year
		if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("workingYear")
				&& uriInfo.getQueryParameters().containsKey("location") && uriInfo.getQueryParameters().containsKey("department") ) { //filter by user type, location, department and start work year
			users = controller.UserFilterByTypeLocationDepartmentAndStartWorkyearFontysStaff(type, workYear, locId, depId);
		}
		//If query parameter is missing return all users. Otherwise filter users by given user type location and department and start study year
		else if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("studyYear")
				&& uriInfo.getQueryParameters().containsKey("location") && uriInfo.getQueryParameters().containsKey("department") ) { //filter by user type, location, department and start study year
			users = controller.UserFilterByTypeLocationDepartmentAndStartSudyYear(type, year, locId, depId);
		}
		//If query parameter is missing return all users. Otherwise filter users by given user type location and department
		else if (uriInfo.getQueryParameters().containsKey("type") && uriInfo.getQueryParameters().containsKey("location")
				&& uriInfo.getQueryParameters().containsKey("department")) { //filter by user type, location and department
			users = controller.UserFilterByTypeLocationAndDepartment(type, locId, depId);
		}
		//If query parameter is missing return all users. Otherwise filter users by given user type
		else if (uriInfo.getQueryParameters().containsKey("type")) { //filter by user type
			users = controller.UserFilteredWithType(type);
		}
		else if (uriInfo.getQueryParameters().containsKey("department")){ //filter by department
			users = controller.UserFilteredWithDepartment(depId);
		}
		else if (uriInfo.getQueryParameters().containsKey("location")){  //filter by location
			users = controller.UserFilteredWithLocation(locId);
		}
		else if (uriInfo.getQueryParameters().containsKey("studyYear")){  //filter by start study year
			users = controller.UserFilteredWithStartStudyYear(year);
		}
		else if (uriInfo.getQueryParameters().containsKey("workingYear")){
			users = controller.UserFilteredWithStartWorkYear(workYear);
		}
		else {
			users = controller.GetAllUsers();
		}
		GenericEntity<List<User>> entity = new GenericEntity<>(users) {
		};
		return Response.ok(entity).build();
	}

	@POST //POST at http://localhost:XXXX/users/
	@Path("login")
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Response LoginUser(String body) {
		final StringTokenizer tokenizer = new StringTokenizer(body, ":");
		final String email = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		User user = persistenceController.getUserByEmail(email);
		if (persistenceController.login(email, password)) {
			return Response.ok(user).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid email.").build();
		}
	}
	@GET
	@PermitAll
	@Produces (MediaType.APPLICATION_JSON)
	@Path("fontysLocations")
	public Response getFontysLocations (){
		PersistenceController persistenceController = new PersistenceController();
		List<Location> locations =persistenceController.getFontysLocations();

		if (locations == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		}
		else
			{
				GenericEntity<List<Location>> entity = new GenericEntity<>(locations) {
			};
			return Response.ok(entity).build();
		}
	}

	@GET
	@PermitAll
	@Produces (MediaType.APPLICATION_JSON)
	@Path("fontysDepartments")
	public Response getFontysDepartments (){
		PersistenceController persistenceController = new PersistenceController();
		List<Department> departments =persistenceController.getFontysDepartments();

		if (departments == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid student number.").build();
		}
		else
		{
			GenericEntity<List<Department>> entity = new GenericEntity<>(departments) {
			};
			return Response.ok(entity).build();
		}
	}

	@PermitAll
	@POST //POST at http://localhost:XXXX/profile/experience
	@Path("newAddress")
	public Response createAddress(Address address) throws DatabaseException, SQLException {

		PersistenceController persistenceController = new PersistenceController();
		int id = persistenceController.createAddress(address);
		if (id == 0)
		{
			String entity =  "Address Id is zero";
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			return Response.ok(id).build();
		}
	}


	// to add a new experience
	@PermitAll
	@POST //POST at http://localhost:XXXX/profile/experience
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("new")
	public Response createUser(User e) {
		PersistenceController persistenceController = new PersistenceController();

		if (!persistenceController.addUser(e))
		{
			String entity =  "User is not added";
			// throw new Exception(Response.Status.CONFLICT, "This topic already exists");
			return Response.status(Response.Status.CONFLICT).entity(entity).build();
		} else {
			String url = uriInfo.getAbsolutePath() + "/" + e.getId(); //
			URI uri = URI.create(url);
			return Response.created(uri).build();
		}
	}
}
