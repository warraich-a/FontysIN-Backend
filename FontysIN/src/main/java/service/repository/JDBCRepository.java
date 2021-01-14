package service.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCRepository {

    public Connection getDatabaseConnection() throws URISyntaxException {
//        URL res = getClass().getClassLoader().getResource("app.properties");
//        File configFile = Paths.get(res.toURI()).toFile();
//
//        String url = "";
//        String username = "";
//        String pass = "";
//        Connection connection = null;
//
//        try(FileReader reader = new FileReader(configFile)) {
//            Properties properties = new Properties();
//            properties.load(reader);
//
//            url = properties.getProperty("host");
//            username = properties.getProperty("username");
//            pass = properties.getProperty("pass");
//
//            connection = DriverManager.getConnection(url, username, pass);
//            connection.setAutoCommit(false);
//
//        } catch (SQLException | FileNotFoundException e) {
//            throw new IllegalStateException("Driver failed " + url + ".", e);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return connection;

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local:3306/dbi431685", "dbi431685", "password");
            connection.setAutoCommit(false);
            System.out.println("Conn " + connection);
        } catch (SQLException e) {
            throw new IllegalStateException("Driver failed ");
        }

        return connection;
    }
}
