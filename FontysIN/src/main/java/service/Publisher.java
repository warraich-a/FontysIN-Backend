package service;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketEngine;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import service.resources.MessagesResources;
import service.resources.UsersResources;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class Publisher {


   // private static final URI BASE_URI = URI.create("http://0.0.0.0:9090/");
    private static final URI BASE_URI = URI.create("http://localhost:9090/");

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9090).build();
        ResourceConfig resourceConfig = new ResourceConfig(UsersResources.class);

        resourceConfig.packages("service");

        try {
            CustomApplicationConfig customApplicationConfig = new CustomApplicationConfig();
            // create and start a grizzly server


            HttpServer webSocketServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, customApplicationConfig, false);


//            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig,false);

            // setup static file handler so that we can also serve html pages.
            StaticHttpHandler staticHandler = new StaticHttpHandler("static");
            staticHandler.setFileCacheEnabled(false);
            webSocketServer.getServerConfiguration().addHttpHandler(staticHandler,"/static/");

            // Create websocket addon
            WebSocketAddOn webSocketAddOn = new WebSocketAddOn();
            webSocketServer.getListeners().forEach(listener -> { listener.registerAddOn(webSocketAddOn);});

            // register my websocket app
            MyWebSocketApp webSocketApp = new MyWebSocketApp();
            WebSocketEngine.getEngine().register("/ws", "/demo", webSocketApp);

            // Now start the server
            webSocketServer.start();

            // prevent the app from closing
            System.out.println("Press enter to stop the server...");
            System.in.read();

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
