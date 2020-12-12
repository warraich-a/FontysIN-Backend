package service.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import service.PersistenceController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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

    @PermitAll
    @PUT
    @Path("{userId}/uploadPicture")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                  @PathParam("userId") int id) throws Exception {
        PersistenceController persistenceController = new PersistenceController();
        String UPLOAD_PATH = "src/images/";
        String project_path =System.getProperty("user.dir");
//        String UPLOAD_PATH = "C:/Users/anasw/fontysin-semester-3-client/FontyIN-Client/src/assets/";
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));

        File file = getFileName(new File(UPLOAD_PATH + fileMetaData.getFileName()));
//        try
//        {
            int read = 0;
            byte[] bytes = new byte[1024];
            String completePath = file.toString(); //+id;
            OutputStream out = new FileOutputStream(completePath);

            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
//            if(persistenceController.uploadPicture(id, fileMetaData.getFileName()+id)){
                if(persistenceController.uploadPicture(id, file.getName())){

                    out.flush();
                out.close();

                return file.getName(); //+id;
            }
            else {
                return "Error";
            }
//        } catch (IOException e)
//        {
//            throw new WebApplicationException("Error while uploading file. Please try again !!");
//        }
    }

    private File getFileName(File file) {
        if (file.exists()){
            String newFileName = file.getName();
            String simpleName = file.getName().substring(0,newFileName.indexOf("."));
            String strDigit="";

            try {
                simpleName = (Integer.parseInt(simpleName)+1+"");
                File newFile = new File(file.getParent()+"/"+simpleName+getExtension(file.getName()));
                return getFileName(newFile);
            }catch (Exception e){}

            for (int i=simpleName.length()-1;i>=0;i--){
                if (!Character.isDigit(simpleName.charAt(i))){
                    strDigit = simpleName.substring(i+1);
                    simpleName = simpleName.substring(0,i+1);
                    break;
                }
            }

            if (strDigit.length()>0){
                simpleName = simpleName+(Integer.parseInt(strDigit)+1);
            }else {
                simpleName+="1";
            }

            File newFile = new File(file.getParent()+"/"+simpleName+getExtension(file.getName()));
            return getFileName(newFile);
        }
        return file;
    }

    private String getExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

//    @GET
//    @Path("picture")
//    @Produces("image/jpg")
//    public Response getProfilePicture() throws Exception {
//       File file  = new File("C:\\Users\\anasw\\fontysin-semester-3\\FontysIN\\src\\images\\IMG_20160827_163306.jpg");
//        Response.ResponseBuilder response = Response.ok(file);
//        response.header("Content-Disposition", "attachment; filename=DisplayName-IMG_20160827_163306.jpg");
//        return response.build();
//    }
}