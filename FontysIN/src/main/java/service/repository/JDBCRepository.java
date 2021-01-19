package service.repository;

import org.glassfish.jersey.jaxb.internal.XmlRootElementJaxbProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class JDBCRepository {
    private final static Logger LOGGER = Logger.getLogger(JDBCRepository.class.getName());

    public Connection getDatabaseConnection() throws URISyntaxException {
        Properties prop = new Properties();
        String url = "";
        String username = "";
        String pass = "";
        Connection connection = null;

        try {
            prop.load(XmlRootElementJaxbProvider.App.class.getClassLoader().getResourceAsStream("app.properties"));

            url = prop.getProperty("host");
            username = prop.getProperty("username");
            pass = prop.getProperty("pass");

            connection = DriverManager.getConnection(url, username, pass);

            connection.setAutoCommit(false);

        }
        catch (SQLException ex) {
            throw new IllegalStateException("JDBC driver failed to connect to the database " + url + " " + username + " " + pass + ".", ex);
        }
        catch (FileNotFoundException ex) {
            LOGGER.info(ex.getMessage()); // Compliant
        }
        catch (IOException ex) {
            LOGGER.info(ex.getMessage()); // Compliant
        }

        return connection;
//
//        Connection connection;
//        try {
//            //connection = DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local:3306/dbi431685", "dbi431685", "password");
//
////            Server: sql2.freemysqlhosting.net
////            Name: sql2386626
////            Username: sql2386626
////            Password: rH4!rV5%
////                    Port number: 3306
//            connection = DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2386626", "sql2386626", "rH4!rV5%");
//            connection.setAutoCommit(false);
//            System.out.println("Conn " + connection);
//        } catch (SQLException e) {
//            throw new IllegalStateException("Driver failed ");
//        }
//
//        return connection;
    }
}
