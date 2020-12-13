package service.resources;


import service.controller.CommentController;
import service.model.Comments;

import javax.ws.rs. * ;
import javax.ws.rs.core. * ;
import java.net.URI;
import java.util.List;

@Path("/comments")
public class CommentResources {@Context
private UriInfo uriInfo;

    CommentController persistenceController = new CommentController();

    @GET@Path("")@Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        GenericEntity < List < Comments >> entity = new GenericEntity < >(persistenceController.getCommets()) {};
        return Response.ok(entity).build();
    }@GET@Path("post/{id}")@Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommentsByPostId(@PathParam("id") int stNr) {
        GenericEntity < List < Comments >> entity = new GenericEntity < >(persistenceController.getCommetsByPostId(stNr)) {};
        return Response.ok(entity).build();
    }@GET@Path("{id}")@Produces(MediaType.APPLICATION_JSON)
    public Response getCommentPath(@PathParam("id") int stNr) {

        Comments comment = persistenceController.getCommet(stNr);
        if (comment == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid comment id.").build();
        } else {
            return Response.ok(comment).build();
        }
    }

    @GET@Path("{id}/content")@Produces(MediaType.APPLICATION_JSON)
    public Response getCommentContent(@PathParam("id") int stNr) {

        Comments comment = persistenceController.getCommet(stNr);
        if (comment == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid comment id.").build();
        } else {
            return Response.ok(comment.getContent()).build();
        }
    }

    @DELETE@Path("{id}")
    public Response deleteComment(@PathParam("id") int stNr) {
        persistenceController.deleteComment(persistenceController.getCommet(stNr));

        return Response.noContent().build();
    }

    @POST@Path("")@Consumes({
            MediaType.APPLICATION_JSON
    })@Produces({
            MediaType.TEXT_PLAIN
    })
    public Response createComment(Comments comm) {
        if (!persistenceController.addComment(comm)) {
            String entity = "comm with same comm id " + comm.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + comm.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT@Path("{id}")@Consumes(MediaType.APPLICATION_JSON)@Produces({
            MediaType.TEXT_PLAIN
    })
    public Response updateComment(Comments comm) {
        if (persistenceController.updateComment(comm)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid comm id.").build();
        }
    }
}