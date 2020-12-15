package service.resources;


import io.jsonwebtoken.Claims;
import service.controller.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    // requestContext contains information about the HTTP request message
    UserController controller = new UserController();

    @Override
    public void filter(ContainerRequestContext requestContext)
    {

        Method method = resourceInfo.getResourceMethod();

        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        // if access is denied for all: deny access
        if (method.isAnnotationPresent(DenyAll.class)) {
            Response response = Response.status(Response.Status.FORBIDDEN).entity("You are not allowed to perform this action").build();
            requestContext.abortWith(response);
            return;
        }

        final String AUTHORIZATION_PROPERTY = "Authorization";
        
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


        final String token = authorization.get(0);
        Claims decoded = controller.decodeJWT(token);

        String email = decoded.getIssuer();
        String password = decoded.getSubject();


        System.out.println(email);
        System.out.println(password);
        //Check if username and password are valid (e.g., database)
        //If not valid: abort with UNAUTHORIED and stop
        if (!isValidUser(email, password)) {
            System.out.println("Invalid user");
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Invalid email and/or password.").build();
            requestContext.abortWith(response);
            return;
        }

        System.out.println("VALID USER");
    }
   private boolean isValidUser(String email, String password) {

       boolean valid;

      valid = controller.login(email, password);
      return valid;
    }

}
