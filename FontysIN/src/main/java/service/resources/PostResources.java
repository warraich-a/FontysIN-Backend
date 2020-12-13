package service.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


import service.controller.LikeController;
import service.controller.PostController;
import service.controller.ProfileController;
import service.model.Like;
import service.model.Posts;

import javax.annotation.security.PermitAll;
import javax.ws.rs. * ;
import javax.ws.rs.core. * ;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

@Path("/posts")
public class PostResources {@Context
private UriInfo uriInfo;

    PostController persistenceController = new PostController();
    LikeController likeController = new LikeController();

    @GET@Path("")@Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        GenericEntity < List < Posts >> entity = new GenericEntity < >(persistenceController.getPosts()) {};
        return Response.ok(entity).build();
    }

    @GET@Path("{id}")@Produces(MediaType.APPLICATION_JSON)
    public Response getPostPath(@PathParam("id") int stNr) {

        Posts post = persistenceController.getPost(stNr);
        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid post id.").build();
        } else {
            return Response.ok(post).build();
        }
    }@GET@Path("newsfeed/{id}")@Produces(MediaType.APPLICATION_JSON)
    public Response getNewsfeed(@PathParam("id") int stNr) {

        GenericEntity < List < Posts >> entity = new GenericEntity < >(persistenceController.getNewsfeed(stNr)) {};
        return Response.ok(entity).build();
    }

    @GET@Path("user/{id}")@Produces(MediaType.APPLICATION_JSON)
    public Response getPostByUser(@PathParam("id") int stNr) {

        GenericEntity < List < Posts >> entity = new GenericEntity < >(persistenceController.getPostByUserId(stNr)) {};
        return Response.ok(entity).build();
    }

    @GET@Path("{id}/content")@Produces(MediaType.APPLICATION_JSON)
    public Response getPostContent(@PathParam("id") int stNr) {

        Posts post = persistenceController.getPost(stNr);
        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid post id.").build();
        } else {
            return Response.ok(post.getContent()).build();
        }
    }

    @DELETE@Path("{id}")
    public Response deletePost(@PathParam("id") int stNr) {
        persistenceController.deletePost(persistenceController.getPost(stNr));

        return Response.noContent().build();
    }

    @POST@Path("")@Consumes({
            MediaType.APPLICATION_JSON
    })@Produces({
            MediaType.TEXT_PLAIN
    })
    public Response createPost(Posts post) {
        if (!persistenceController.addPost(post)) {
            String entity = "Post with same post id " + post.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + post.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT@Path("{id}")@Consumes(MediaType.APPLICATION_JSON)@Produces({
            MediaType.TEXT_PLAIN
    })
    public Response updatePost(Posts post) {
        if (persistenceController.updatePost(post)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid post id.").build();
        }
    }

    @GET@Path("{id}/likes")@Produces(MediaType.APPLICATION_JSON)
    public Response getPostLikes(@PathParam("id") int stNr) {

        GenericEntity < List < Like >> entity = new GenericEntity < >(likeController.getLikesByPost(stNr)) {};
        return Response.ok(entity).build();
    }

    @GET@Path("{id}/likes/count")@Produces(MediaType.TEXT_PLAIN)
    public Response getPostLikesCount(@PathParam("id") int stNr) {

        List < Like > entity = likeController.getLikesByPost(stNr);

        return Response.ok(entity.size()).build();
    }

    @GET@Path("{id}/likes/user/{userId}")@Produces(MediaType.APPLICATION_JSON)
    public Response getPostLikeByUser(@PathParam("id") int stNr, @PathParam("userId") int userId) {

        Like like = likeController.getPostLikesByUser(stNr, userId);

        if (like == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("").build();
        } else {
            return Response.ok(like).build();
        }
    }

    @POST@Path("{id}/likes")@Consumes({
            MediaType.APPLICATION_JSON
    })@Produces({
            MediaType.TEXT_PLAIN
    })
    public Response createLike(Like like) {
        if (!likeController.addLike(like)) {
            String entity = "Like with same post id " + like.getId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + like.getId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PermitAll@PUT@Path("{userId}/uploadPicture")@Consumes(MediaType.MULTIPART_FORM_DATA)@Produces(MediaType.APPLICATION_JSON)
    public String uploadPdfFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition fileMetaData, @PathParam("userId") int id) throws Exception {
        ProfileController profileController = new ProfileController();

        String UPLOAD_PATH = "src/images/";
        String project_path = System.getProperty("user.dir");

        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));

        int read = 0;
        byte[] bytes = new byte[1024];
        String completePath = new File(UPLOAD_PATH + id + fileMetaData.getFileName()).toString();
        OutputStream out = new FileOutputStream(completePath);

        while ((read = fileInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        out.flush();
        out.close();

        return id + fileMetaData.getFileName();

    }

}