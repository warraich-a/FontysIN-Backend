package service.resources;

import service.model.Comments;
import service.model.Posts;
import service.repository.FakeDateBase;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/comments")
public class CommentResources {
    @Context
    private UriInfo uriInfo;

    private static final FakeDateBase fakeDataStore = new FakeDateBase();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        GenericEntity<List<Comments>> entity = new GenericEntity<>(fakeDataStore.getCommentsList()) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentPath(@PathParam("id") int stNr) {

        Comments comment = fakeDataStore.getComment(stNr);
        if (comment == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid comment id.").build();
        } else {
            return Response.ok(comment).build();
        }
    }

    @GET
    @Path("{id}/content")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentContent(@PathParam("id") int stNr) {

        Comments comment = fakeDataStore.getComment(stNr);
        if (comment == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid comment id.").build();
        } else {
            return Response.ok(comment.getContent()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteComment(@PathParam("id") int stNr) {
        fakeDataStore.deleteComment(stNr);

        return Response.noContent().build();
    }

    @POST
    @Path("")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response createComment(Comments comment) {
        if (!fakeDataStore.addComment(comment)){
            String entity =  "Post with same comment id " + comment.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + comment.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateComment(Comments comment) {
        if (fakeDataStore.updateComment(comment)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid comment id.").build();
        }
    }
}
