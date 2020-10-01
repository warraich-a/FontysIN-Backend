package service.resources;

import service.model.Education;
import service.model.Experience;
import service.model.Profile;
import service.repository.FakeDataProfile;
import service.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("user")
public class UserResources {
    private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
    @Context
    private UriInfo uriInfo;


    //to get all the experiences
    @GET //GET at http://localhost:XXXX/profile/experiences
    @Path("{userId}/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetProfile(@PathParam("userId") int userId) {

        Profile p = fakeDataProfile.GetProfileByUserId(userId);//studentsRepository.get(stNr);
        List<Education> educationByProfileId = fakeDataProfile.GetEducationsByProfileId(p.getId());
        List<Experience> experienceByProfileId = fakeDataProfile.GetExperiencesByProfileID(p.getId());

        if (p == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid student number.").build();
        } else {
            // to combine different types of lists into 1
            List<Object> combined = new ArrayList<>();
            combined.addAll(educationByProfileId);
            combined.addAll(experienceByProfileId);

            // to show a list correctly
            GenericEntity<List<Object>> entity = new GenericEntity<>(combined) {
            };

            return Response.ok(entity).build();
        }
    }

    // to add a new experience
    @POST //POST at http://localhost:XXXX/profile/experience
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}/profile/experience/new")
    public Response CreateExperience(@PathParam("userId") int userId, Experience e) {

        if (!fakeDataProfile.AddExperience(e,  userId))
        {
            String entity =  "Experience Exists";
            // throw new Exception(Response.Status.CONFLICT, "This topic already exists");
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + e.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }
    // to add a new experience
    @POST //POST at http://localhost:XXXX/profile/experience
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}/profile/education/new")
    public Response CreateEducation(@PathParam("userId") int userId, Education e) {

        if (!fakeDataProfile.AddEducation(e, userId))
        {
            String entity =  "Education Exists";
            // throw new Exception(Response.Status.CONFLICT, "This topic already exists");
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + e.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }


}
