package service.repository;

import service.model.User;
import service.model.UserType;
import service.model.dto.UserDTO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends JDBCRepository {

    /**************Ranim******************************Filter users**************************/
    //get all users with the given user type from data base
    public List<UserDTO> getUsersByType(UserType type) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE u.userType = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type.name()); // set user type parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given location id from data base
    public List<UserDTO> getUsersByLocation(int lId) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE u.locationId = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lId); // set location id parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given department id from data base
    public List<UserDTO> getUsersByDepartment(int bId) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE u.departmentId = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bId); // set user fontys location parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given start study year from data base
    public List<UserDTO> getUsersByStartStudyYear(int year) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((educations INNER JOIN profiles p ON educations.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) " +
                "LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE school = 'Fontys' AND u.userType = 'Student' " +
                "AND startYear = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, year); // set user start study year parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given start work year from data base
    public List<UserDTO> getUsersByStartWorkYear(int year) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();


        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((experiences INNER JOIN profiles p ON experiences.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE company = 'Fontys' AND u.userType != 'Student' AND startDate = ? " +
                "AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, year); // set user start study year parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given user type, location and department from data base
    public List<UserDTO> getUsersByUserTypeAndLocationAndDepartment(UserType type, int lId, int dId) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId " +
                "FROM profiles p LEFT JOIN users u ON u.id = p.userId " +
                "LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE userType = ? AND locationId = ? " +
                "AND departmentId = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type.name()); // set user start study year parameter
            statement.setInt(2, lId); // set user location id parameter
            statement.setInt(3, dId); // set user department id parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given user type, location and department from data base
    public List<UserDTO> getUsersByUserTypeAndStartStudyYearAndDepartmentAndLocation(UserType type, int year, int lId, int dId) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((educations INNER JOIN profiles p ON educations.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) " +
                "LEFT JOIN privacy pr ON pr.userId = u.id WHERE school = 'Fontys' " +
                "AND u.userType = ? AND u.locationId = ? AND u.departmentId = ? " +
                "AND startYear = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, type.name()); // set user start study year parameter
            statement.setInt(2, lId); // set user location id parameter
            statement.setInt(3, dId); // set user department id parameter
            statement.setInt(4, year); // set user start study year parameter

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given user type, location and department from data base
    public List<UserDTO> getUsersByUserTypeAndStartWorkYearAndDepartmentAndLocationFontysStaff(UserType type, int year, int lId, int dId) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((experiences INNER JOIN profiles p ON experiences.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE company = 'Fontys' AND u.userType = ? AND u.locationId = ? " +
                "AND u.departmentId = ? AND startDate = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, type.name()); // set user start study year parameter
            statement.setInt(2, lId); // set user location id parameter
            statement.setInt(3, dId); // set user department id parameter
            statement.setInt(4, year); // set user start study year parameter

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }
    /***********************RANIM****************************Norrmal searching*******************************/

    //get all users withing fontys
    public List<User> getAllUsers() throws DatabaseException, URISyntaxException {

        List<User> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                UserType userType = UserType.valueOf(resultSet.getString("userType"));
                String password = resultSet.getString("password");
//                String phoneNumber = resultSet.getString("phoneNr");
//                int addressId = resultSet.getInt("addressId");
                String image = "assets/" + resultSet.getString("image");
                int locationId = resultSet.getInt("locationId");
                int departmentId = resultSet.getInt("departmentId");
                String userNumber = resultSet.getString("userNumber");

                User u = new User(id, firstName, lastName, userType, email, password,  locationId, departmentId,  userNumber, image);
                filtered.add(u);

            }


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    /***************************************List User DTO**************************************/
    //get all users to make use of userdto
    public List<UserDTO> getUsersDTO() throws DatabaseException, URISyntaxException {

        List<UserDTO> users = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles AS p " +
                "LEFT JOIN users u ON u.id = p.userId GROUP BY u.id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                users.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return users;
    }

    /****************************************Using search box in the filter page*********************************************************/
    //get all users with the given user type, location, department and user name from data base
    public List<UserDTO> getUsersByUserTypeLocationDeoartmentAndName(String chars, int lId, int dId, UserType type) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId " +
                "FROM users u INNER JOIN profiles p ON u.id = p.userId " +
                "LEFT JOIN privacy pr ON pr.userId = u.id WHERE u.firstName LIKE CONCAT ('%', ? ,'%') " +
                "AND u.locationId = ? AND u.departmentId = ? AND userType = ? AND pr.hideFromSearch = 0 GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chars); // set characters
            statement.setInt(2, lId); // set user location id parameter
            statement.setInt(3, dId); // set user department id parameter
            statement.setString(4, type.name()); // set user type parameter

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }

    //get all users with the given first name chars
    public List<UserDTO> getUsersByFirstNameChars(String chars) throws DatabaseException, URISyntaxException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM users u " +
                "INNER JOIN profiles p ON u.id = p.userId LEFT JOIN privacy pr ON pr.userId = u.id " +
                "WHERE pr.hideFromSearch = 0 AND u.firstName LIKE CONCAT ('%', ? ,'%') GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chars); // set characters

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = "assets/" + resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");

                UserDTO userDTO= new UserDTO(id, profileId, firstName, lastName, image);

                filtered.add(userDTO);

            }

            connection.close();


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return filtered;
    }
}
