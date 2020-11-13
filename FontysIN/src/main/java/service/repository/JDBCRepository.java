package service.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JDBCRepository {

    protected Connection getDatabaseConnection() throws DatabaseException {
//        String url = "jdbc:mysql://studmysql01.fhict.local:3306/dbi431685";
//        String username = "dbi431685";
//        String password = "password";

        String url = "jdbc:mysql://localhost:3306/fontysIn";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Driver failed " + url+".",e);
        }
    }
}
