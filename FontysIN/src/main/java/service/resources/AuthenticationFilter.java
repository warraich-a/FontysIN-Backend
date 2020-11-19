package service.resources;


import service.PersistenceController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    // requestContext contains information about the HTTP request message


    @Override
    public void filter(ContainerRequestContext requestContext)
    {

        Method method = resourceInfo.getResourceMethod();

        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        final String AUTHORIZATION_PROPERTY = "Authorization";
        final String AUTHENTICATION_SCHEME = "Basic";

        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
        //If no authorization information present: abort with UNAUTHORIZED and stop
        if (authorization == null || authorization.isEmpty()) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Missing username and/or password.").build();
            requestContext.abortWith(response);
            return;
        }
        //Get encoded username and password
        final String encodedCredentials =
                authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        //Decode username and password into one string
        String credentials = new
                String(Base64.getDecoder().decode(encodedCredentials.getBytes()));
        //Split username and password tokens in credentials
        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        System.out.println(email);
        System.out.println(password);
        //Check if username and password are valid (e.g., database)
        //If not valid: abort with UNAUTHORIED and stop
        if (!isValidUser(email, password)) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Invalid email and/or password.").build();
            requestContext.abortWith(response);
            return;
        }
    }
   private boolean isValidUser(String email, String password) {
       PersistenceController controller = new PersistenceController();
       boolean valid;
      valid = controller.login(email, password);
      return valid;
    }

}
