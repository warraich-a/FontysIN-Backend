package service.resources;

import service.model.Experience;
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

    //to get all the pharmacists
    @GET //GET at http://localhost:XXXX/patients/
    @Path("experiences")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPharmacists() {
        List<Experience> experiences = fakeDataProfile.GetExperiences();

        GenericEntity<List<Experience>> entity = new GenericEntity<>(experiences) {
        };
        return Response.ok(entity).build();
    }
    // to add a new experience
    @POST //POST at http://localhost:XXXX/patient/
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("experience")
    public Response createStudent(Experience e) {
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
    }

}
