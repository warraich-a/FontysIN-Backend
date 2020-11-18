package service.resources;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import service.PersistenceController;

import java.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;

/**
 * This example shows how to build Java REST web-service to upload files
 * accepting POST requests with encoding type "multipart/form-data". For more
 * details please read the full tutorial on
 * https://javatutorial.net/java-file-upload-rest-service
 *
 * @author javatutorial.net
 */
@Path("/upload")
public class FileUploadService {
    @Context
    private UriInfo uriInfo;

    @PUT
    @Path("{userId}/uploadPicture")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                  @PathParam("userId") int id) throws Exception {
        PersistenceController persistenceController = new PersistenceController();
//        String UPLOAD_PATH = "src/images/";
        String UPLOAD_PATH = "C:/Users/anasw/fontysin-semester-3-client/FontyIN-Client/src/assets/";


//        try
//        {
            int read = 0;
            byte[] bytes = new byte[1024];
            String completePath = new File(UPLOAD_PATH + fileMetaData.getFileName()).toString()+id;
            OutputStream out = new FileOutputStream(completePath);

            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            if(persistenceController.uploadPicture(id, "assets/"+fileMetaData.getFileName()+id)){
                out.flush();
                out.close();

                return "assets/"+fileMetaData.getFileName();
            }
            else {
                return "Error";
            }
//        } catch (IOException e)
//        {
//            throw new WebApplicationException("Error while uploading file. Please try again !!");
//        }
    }

}