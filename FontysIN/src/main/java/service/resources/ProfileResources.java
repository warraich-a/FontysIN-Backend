package service.resources;

import service.model.Education;
import service.model.Experience;
import service.model.User;
import service.repository.FakeDataProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("profile")
public class ProfileResources {
    private final FakeDataProfile fakeDataProfile = FakeDataProfile.getInstance();
    @Context
    private UriInfo uriInfo;

    //to get all the experiences
    @GET //GET at http://localhost:XXXX/profile/experiences
    @Path("experiences")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetExperiences() {
        List<Experience> experiences = fakeDataProfile.GetExperiences();

        GenericEntity<List<Experience>> entity = new GenericEntity<>(experiences) {
        };
        return Response.ok(entity).build();
    }
    // to add a new experience
    /*@POST //POST at http://localhost:XXXX/profile/experience
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("experience")
    public Response CreateExperience(Experience e) {
        if (!fakeDataProfile.AddExperience(e)) // In this addPatient it adds the new object in this if statement and return true or false since that method is boolean
        {
            String entity =  "Experience Exists";
            // throw new Exception(Response.Status.CONFLICT, "This topic already exists");
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + e.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }*/

    //Education
    //to get all the Educations
    @GET //GET at http://localhost:XXXX/profile/educations
    @Path("educations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetEducation() {
        List<Education> educations = fakeDataProfile.GetEducations();

        GenericEntity<List<Education>> entity = new GenericEntity<>(educations) {
        };
        return Response.ok(entity).build();
    }

    // to add a new education
 /*   @POST //POST at http://localhost:XXXX/profile/education
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("education")
    public Response CreateEducation(Education e) {
        if (!fakeDataProfile.AddEducation(e)) // In this addPatient it adds the new object in this if statement and return true or false since that method is boolean
        {
            String entity =  "Experience Exists";
            // throw new Exception(Response.Status.CONFLICT, "This topic already exists");
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + e.getId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }*/

   /* @PUT //PUT at http://localhost:XXXX/profile/education
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("education")
    public Response updateEducation(Education e) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataProfile.updateEducation(e)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid education.").build();
        }
    }*/
    @PUT //PUT at http://localhost:XXXX/profile/experience
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("experience")
    public Response updateExperience(Experience e) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataProfile.updateExperience(e)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid experience.").build();
        }
    }
    @PUT //PUT at http://localhost:XXXX/profile/information
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("information")
    public Response updateUser(User user) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataProfile.updateUser(user)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid id.").build();
        }
    }
}
