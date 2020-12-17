package service.repository;

import org.h2.tools.RunScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;

public class JDBCRepositoryTest {
    public static void generateData() {
        Connection conn = null;
        Statement stmt = null;
        String sql;
        try {
            Class.forName("org.h2.Driver");

            //STEP 1: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:h2:mem:~/test");

            System.out.println("TEST CONN " + conn);

            RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
//            URL res = JDBCRepositoryTest.class.getClassLoader().getResource("data.sql");
//        File configFile = Paths.get(res.toURI()).toFile();
//
//            System.out.println("FILE " + configFile);
//
////        Connection connection = DriverManager.getConnection("jdbc:h2:mem:~/test", "", "");
////        RunScript.execute(conn, new FileReader(configFile));
//            System.out.println("WORKED ");
//
////            STEP 2: Execute a query
//            System.out.println("Creating table in given database...");
//            stmt = conn.createStatement();
//            sql = "CREATE TABLE departments " +
//                    "(id          INTEGER AUTO_INCREMENT, " +
//                    "name        varchar(150)   NOT NULL, " +
//                    "description varchar(250)   NOT NULL, " +
//                    "PRIMARY KEY ( id ))";
//            stmt.executeUpdate(sql);
//
//            System.out.println("Created table in given database...");
//            sql = "CREATE TABLE fontyslocations " +
//                    "(id int AUTO_INCREMENT, " +
//                    "streetName varchar(150)     NOT NULL, " +
//                    "buildingNumber varchar(50)  NOT NULL, " +
//                    "city varchar(50)            NOT NULL, " +
//                    "zipcode varchar(20)         NOT NULL, " +
//                    "PRIMARY KEY ( id ))";
//            stmt.executeUpdate(sql);
//
//            System.out.println("Created table in given database...");
//            sql = "CREATE TABLE users " +
//                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
//                    "firstName    VARCHAR(255)      NOT NULL, " +
//                    "lastName     VARCHAR(255)      NOT NULL, " +
//                    "userType     VARCHAR(255)      NOT NULL, " +
//                    "email        VARCHAR(255)      NOT NULL, " +
//                    "password     VARCHAR(255)      NOT NULL, " +
//                    "image        VARCHAR(255)      NOT NULL, " +
//                    "locationId   INTEGER           NOT NULL, " +
//                    "departmentId INTEGER           NOT NULL, " +
//                    "userNumber   VARCHAR(255)      NOT NULL, " +
//                    "PRIMARY KEY ( id ), " +
//                    "FOREIGN KEY (departmentId) REFERENCES departments (id), " +
//                    "FOREIGN KEY (locationId) REFERENCES fontyslocations (id))";
//            stmt.executeUpdate(sql);
//
//            sql = "CREATE TABLE profiles " +
//                    "(id INTEGER AUTO_INCREMENT, " +
//                    "userId INTEGER       NOT NULL, " +
//                    "language VARCHAR(50) NOT NULL, " +
//                    "PRIMARY KEY ( id ), " +
//                    "FOREIGN KEY (userId) REFERENCES users (id))";
//            stmt.executeUpdate(sql);
//
//
//            sql = "CREATE TABLE contacts " +
//                    "(id INTEGER AUTO_INCREMENT, " +
//                    "userId     INTEGER        NOT NULL, " +
//                    "friendId   INTEGER        NOT NULL, " +
//                    "isAccepted tinyint(1)     NOT NULL, " +
//                    "PRIMARY KEY ( id ), " +
//                    "FOREIGN KEY (userId) REFERENCES users (id), " +
//                    "FOREIGN KEY (friendId) REFERENCES users (id))";
//            stmt.executeUpdate(sql);
//
//            //        INSERT INTO departments (name, description) VALUES ('ICT', 'ICT General');
////        INSERT INTO departments (name, description) VALUES ('Pedagogy', 'Pedagogy General');
////        INSERT INTO departments (name, description) VALUES ('Buisness', 'Buisness General');
//
//            sql = "INSERT INTO departments (name, description) VALUES ('ICT', 'ICT General')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO departments (name, description) VALUES ('Pedagogy', 'Pedagogy General')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO departments (name, description) VALUES ('Buisness', 'Buisness General')";
//            stmt.executeUpdate(sql);
//
//            //        INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('RachelsMolen', 'R10', 'Eindhoven', '2647KR');
////        INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Tegelseweg', 'R1', 'Eindhoven', '1234AB');
////        INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Beatrixlaan', 'R3', 'Tilburg', '5643LP');
//
//            sql = "INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('RachelsMolen', 'R10', 'Eindhoven', '2647KR')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Tegelseweg', 'R1', 'Eindhoven', '1234AB')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Beatrixlaan', 'R3', 'Tilburg', '5643LP')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Rawan', 'Abou Dehn', 'Student', 'rawan@student.fontys.nl', '1234', 'rawan image', 1, 1, '123456')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Anas', 'Ahmad', 'Student', 'anas@student.fontys.nl', '12345', 'anas image', 1, 2, '123456')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Beatrice', 'Forslund', 'Student', 'beatrice@student.fontys.nl', '123456', 'bea image', 1, 3, '123456')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Ranim', 'Alayoubi', 'Student', 'ranim@student.fontys.nl', '1234567', 'ranim image', 2, 1, '123456')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Denys', 'Sytnyk', 'Student', 'denys@student.fontys.nl', '12345678', 'denys image', 3, 1, '123456')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Kalina', 'Petrova', 'Teacher', 'kalina@student.fontys.nl', '123456789', 'kalina image', 1, 1, '123456')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 2, 1)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 3, 1)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 4, 0)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (5, 1, 0)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (6, 1, 0)";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (2, 5, 1)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO profiles (userId, language) VALUES (1, 'English')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO profiles (userId, language) VALUES (2, 'Urdu')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO profiles (userId, language) VALUES (3, 'Swedish')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO profiles (userId, language) VALUES (4, 'Arabic')";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO profiles (userId, language) VALUES (1, 'Arabic')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "SELECT * FROM users";
//            ResultSet resultSet = stmt.executeQuery(sql);
//            resultSet.next();
//            System.out.println(resultSet.getString("firstName"));
//            System.out.println("DONE DB");
//
//
////             STEP 3: Clean-up environment
//            stmt.close();
            conn.close();
//            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } //end finally try
        }
    }
}












//        INSERT INTO departments (name, description) VALUES ('ICT', 'ICT General');
//        INSERT INTO departments (name, description) VALUES ('Pedagogy', 'Pedagogy General');
//        INSERT INTO departments (name, description) VALUES ('Buisness', 'Buisness General');
//
//        INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('RachelsMolen', 'R10', 'Eindhoven', '2647KR');
//        INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Tegelseweg', 'R1', 'Eindhoven', '1234AB');
//        INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Beatrixlaan', 'R3', 'Tilburg', '5643LP');
//
//        INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Rawan', 'Abou Dehn', 'Student', 'rawan@student.fontys.nl', '1234', 'rawan image', 1, 1, '123456');
//        INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Anas', 'Ahmad', 'Student', 'anas@student.fontys.nl', '12345', 'anas image', 1, 2, '123456');
//        INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Beatrice', 'Forslund', 'Student', 'beatrice@student.fontys.nl', '123456', 'bea image', 1, 3, '123456');
//        INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Ranim', 'Alayoubi', 'Student', 'ranim@student.fontys.nl', '1234567', 'ranim image', 2, 1, '123456');
//        INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Denys', 'Sytnyk', 'Student', 'denys@student.fontys.nl', '12345678', 'denys image', 3, 1, '123456');
//        INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Kalina', 'Petrova', 'Teacher', 'kalina@student.fontys.nl', '123456789', 'kalina image', 1, 1, '123456');
//
//        INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 2, 1);
//        INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 3, 1);
//        INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 4, 0);
//        INSERT INTO contacts (userId, friendId, isAccepted) VALUES (5, 1, 0);
//        INSERT INTO contacts (userId, friendId, isAccepted) VALUES (6, 1, 0);
//        INSERT INTO contacts (userId, friendId, isAccepted) VALUES (2, 5, 1);
//
//        INSERT INTO profiles (userId, language) VALUES (1, 'English');
//        INSERT INTO profiles (userId, language) VALUES (2, 'Urdu');
//        INSERT INTO profiles (userId, language) VALUES (3, 'Swedish');
//        INSERT INTO profiles (userId, language) VALUES (4, 'Arabic');
//        INSERT INTO profiles (userId, language) VALUES (1, 'Arabic');
