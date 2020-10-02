/*
package service.resources;

import service.model.*;
import service.repository.FakeDataProfile;

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


    */
/*//*
/to get all the experiences
    @GET //GET at http://localhost:XXXX/profile/experiences
    @Path("{userId}/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetProfile(@PathParam("userId") int userId) {

        Profile p = fakeDataProfile.GetProfileByUserId(userId); // getting the profile by userid
        List<Education> educationByProfileId = fakeDataProfile.GetEducationsByProfileId(p.getId());
        List<Experience> experienceByProfileId = fakeDataProfile.GetExperiencesByProfileID(p.getId());
        List<About> aboutByProfileId = fakeDataProfile.GetAboutByProfileID(p.getId());
        List<Skill> skillByProfileId = fakeDataProfile.GetSkillByProfileID(p.getId());

        if (p == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid student number.").build();
        } else {
            // to combine different types of lists into 1
            List<Object> combined = new ArrayList<>();
            combined.addAll(educationByProfileId);
            combined.addAll(experienceByProfileId);
            combined.addAll(aboutByProfileId);
            combined.addAll(skillByProfileId);

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
    public Response CreateExperience(@PathParam("userId") int userId, Experience e) { // the purpose of using user id in the params is to first get the find the profile id through user id

        if (!fakeDataProfile.AddExperience(e,  userId))
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
    @Path("{userId}/profile/education/new")
    public Response CreateEducation(@PathParam("userId") int userId, Education e) {

        if (!fakeDataProfile.AddEducation(e, userId))
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
    @Path("{userId}/profile/about/new")
    public Response CreateAbout(@PathParam("userId") int userId, About a) {

        if (!fakeDataProfile.AddAbout(a,  userId))
        {
            String entity =  "About Exists";
            // throw new Exception(Response.Status.CONFLICT, "This topic already exists");
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + a.getId(); //
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }*//*


    // to add a new skill
    @POST //POST at http://localhost:XXXX/profile/experience
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}/profile/skill/new")
    public Response CreateSKill(@PathParam("userId") int userId, Skill s) {

        if (!fakeDataProfile.AddSkill(s,  userId))
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

    //delete user's experince with specific id
    @DELETE //DELETE at http://localhost:9090/users/1/profile/1/experience/1
    @Path("{userId}/{profileID}/{experinceID}")
    public Response deleteUserExperience(@PathParam("userId") int userId ,@PathParam("profileID") int profileID, @PathParam("experinceID") int experinceID) {
        fakeDataProfile.deleteExperience(userId, profileID, experinceID);

        return Response.noContent().build();
    }

    //delete user's education with specific id
    @DELETE //DELETE at http://localhost:9090/users/1/profile/2/education/1
    @Path("{userId}/{profileID}/{experinceID}")
    public Response deleteUserEducation(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
                                        @PathParam("experinceID") int experinceID) {
        fakeDataProfile.deleteEducation(userId, profileID, experinceID);

        return Response.noContent().build();
    }

    //delete user's skill with specific id
    @DELETE //DELETE at http://localhost:9090/users/1/profile/1/skill/1
    @Path("{userId}/{profileID}/{experinceID}")
    public Response deleteUserSkill(@PathParam("userId") int userId ,@PathParam("profileID") int profileID,
                                    @PathParam("experinceID") int experinceID) {
        fakeDataProfile.deleteSkill(userId, profileID, experinceID);

        return Response.noContent().build();
    }


}
*/
