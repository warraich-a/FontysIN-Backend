package service.resources;

import service.PersistenceController;
import service.model.Comments;
import service.model.Like;
import service.model.Posts;
import service.repository.FakeDataPostComm;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/posts")
public class PostResources {
    @Context
    private UriInfo uriInfo;

    PersistenceController persistenceController = new PersistenceController();;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        GenericEntity<List<Posts>> entity = new GenericEntity<>(persistenceController.getPosts()) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostPath(@PathParam("id") int stNr) {

        Posts post = persistenceController.getPost(stNr);
        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid post id.").build();
        } else {
            return Response.ok(post).build();
        }
    }
    @GET
    @Path("newsfeed/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsfeed(@PathParam("id") int stNr) {

        GenericEntity<List<Posts>> entity = new GenericEntity<>(persistenceController.getNewsfeed(stNr)) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostByUser(@PathParam("id") int stNr) {

        GenericEntity<List<Posts>> entity = new GenericEntity<>(persistenceController.getPostByUserId(stNr)) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}/content")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostContent(@PathParam("id") int stNr) {

        Posts post = persistenceController.getPost(stNr);
        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid post id.").build();
        } else {
            return Response.ok(post.getContent()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") int stNr) {
        persistenceController.deletePost(persistenceController.getPost(stNr));

        return Response.noContent().build();
    }

    @POST
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response createPost(Posts post) {
        if (!persistenceController.addPost(post)){
            String entity =  "Post with same post id " + post.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + post.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public Response updatePost(Posts post) {
        if (persistenceController.updatePost(post)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid post id.").build();
        }
    }

    @GET
    @Path("{id}/likes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostLikes(@PathParam("id") int stNr) {

        GenericEntity<List<Like>> entity = new GenericEntity<>(persistenceController.getLikesByPost(stNr)) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}/likes/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPostLikesCount(@PathParam("id") int stNr) {

        List<Like> entity = persistenceController.getLikesByPost(stNr);

        return Response.ok(entity.size()).build();
    }

    @POST
    @Path("{id}/likes")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response createLike(Like like) {
        if (!persistenceController.addLike(like)){
            String entity =  "Like with same post id " + like.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + like.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }




}
