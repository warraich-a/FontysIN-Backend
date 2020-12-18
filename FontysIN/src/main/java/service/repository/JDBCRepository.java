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

    protected Connection getDatabaseConnection() throws DatabaseException, URISyntaxException {
        URL res = getClass().getClassLoader().getResource("app.properties");
        File configFile = Paths.get(res.toURI()).toFile();

        String url = "";
        String username = "";
        String pass = "";
        Connection connection = null;


        try(FileReader reader = new FileReader(configFile)) {
            Properties properties = new Properties();
            properties.load(reader);

            url = properties.getProperty("host");
            username = properties.getProperty("username");
            pass = properties.getProperty("pass");

            connection = DriverManager.getConnection(url, username, pass);
            connection.setAutoCommit(false);

        } catch (SQLException | FileNotFoundException e) {
            throw new IllegalStateException("Driver failed " + url + ".", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
