package service.resources;

import service.model.Comments;
import service.model.Posts;
import service.repository.FakeDateBase;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/posts")
public class PostResources {
    @Context
    private UriInfo uriInfo;

    private static final FakeDateBase fakeDataStore = new FakeDateBase();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        GenericEntity<List<Posts>> entity = new GenericEntity<>(fakeDataStore.getPostsList()) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostPath(@PathParam("id") int stNr) {

        Posts post = fakeDataStore.getPost(stNr);
        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid post id.").build();
        } else {
            return Response.ok(post).build();
        }
    }

    @GET
    @Path("{id}/content")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostContent(@PathParam("id") int stNr) {

        Posts post = fakeDataStore.getPost(stNr);
        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid post id.").build();
        } else {
            return Response.ok(post.getContent()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") int stNr) {
        fakeDataStore.deletePost(stNr);

        return Response.noContent().build();
    }

    @POST
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response createPost(Posts post) {
        if (!fakeDataStore.addPost(post)){
            String entity =  "Post with same post id " + post.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + post.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updatePost(Posts post) {
        if (fakeDataStore.updatePost(post)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid post id.").build();
        }
    }

}
