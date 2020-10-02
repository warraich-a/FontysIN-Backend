package service;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Publisher {

    private static final URI BASE_URI = URI.create("http://localhost:9090/");

    public static void main(String[] args) {

        try {
            CustomApplicationConfig customApplicationConfig = new CustomApplicationConfig();
            // create and start a grizzly server
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, customApplicationConfig, true);

            System.out.println("Hosting resources at " + BASE_URI.toURL());

            System.out.println("Try the following GET operations in your internet browser: ");
            String[] getOperations = {
                    BASE_URI.toURL() + "students/hello",
                    BASE_URI.toURL() + "students/2",
                    BASE_URI.toURL() + "students",
                    BASE_URI.toURL() + "students?country=BG"
            };
            for (String getOperation : getOperations) {
                System.out.println(getOperation);
            }

        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
