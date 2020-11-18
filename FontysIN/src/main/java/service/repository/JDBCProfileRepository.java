package service.repository;

import service.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCProfileRepository extends JDBCRepository {

    public List<Profile> getProfile(int givenUserId) throws DatabaseException, SQLException {
        List<Profile> foundProfiles = new ArrayList<>();


        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM profiles where userId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setInt(1, givenUserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String language = resultSet.getString("language");


                Profile e = new Profile(id, userId, language);
                foundProfiles.add(e);
            }

            connection.setAutoCommit(false);

            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read profiles from the database.",throwable);
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return foundProfiles;
    }

    public List<Experience> getExperiences(int userId, int givenProfileId) throws DatabaseException, SQLException {
        List<Experience> foundExperiences = new ArrayList<>();
        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId && p.getId() == givenProfileId) {
                Connection connection = this.getDatabaseConnection();
                String sql = "SELECT * FROM experiences where profileId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                try {
                    statement.setInt(1, givenProfileId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int profileId = resultSet.getInt("profileId");
                        String title = resultSet.getString("title");
                        String company = resultSet.getString("company");
                        String location = resultSet.getString("location");
                        String emplymentType = resultSet.getString("employmentType");
                        int startDate = resultSet.getInt("startDate");
                        int endDate = resultSet.getInt("endDate");
                        String description = resultSet.getString("description");
                        EmplymentType r = EmplymentType.FullTime;
                        if (emplymentType == "FullTime")
                        {
                            r = EmplymentType.FullTime;
                        }
                        else if (emplymentType == "PartTime")
                        {
                            r = EmplymentType.PartTime;
                        }
                        else  if (emplymentType == "FullTime")
                        {
                            r = EmplymentType.PartTime;
                        }

                        Experience e = new Experience(id, profileId, title, company, r, location,  startDate, endDate, description);
                        foundExperiences.add(e);
                    }
                    connection.setAutoCommit(false);

                    connection.commit();
                    statement.close();
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.",throwable);
                }
                finally {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                }
                return foundExperiences;
            }
        }
        return null;
    }

    public List<Education> getEducations(int userId, int givenProfileId) throws DatabaseException, SQLException {
        List<Education> foundEducations = new ArrayList<>();

        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId && p.getId() == givenProfileId) {
                Connection connection = this.getDatabaseConnection();
                String sql = "SELECT * FROM educations where profileId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                try {
                    statement.setInt(1, givenProfileId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int profileId = resultSet.getInt("profileId");
                        String school = resultSet.getString("school");
                        int startYear = resultSet.getInt("startYear");
                        int endYear = resultSet.getInt("endYear");
                        String degree = resultSet.getString("degree");
                        String fieldStudy = resultSet.getString("fieldStudy");
                        String description = resultSet.getString("description");

                        Education e = new Education(id, profileId, school, startYear, endYear, degree, fieldStudy, description);
                        foundEducations.add(e);
                    }
                    connection.setAutoCommit(false);

                    connection.commit();
                    statement.close();
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.", throwable);
                } finally {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                }
                return foundEducations;
            }
        }
        return null;
    }

    public List<About> getAbout(int userId, int givenProfileId) throws DatabaseException, SQLException {
        List<About> foundAbout = new ArrayList<>();

        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId && p.getId() == givenProfileId) {
                Connection connection = this.getDatabaseConnection();
                String sql = "SELECT * FROM about where profileId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                try {
                    statement.setInt(1, givenProfileId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int profileId = resultSet.getInt("profileId");
                        String content = resultSet.getString("content");

                        About e = new About(id, profileId, content);
                        foundAbout.add(e);
                    }
                    connection.setAutoCommit(false);

                    connection.commit();
                    statement.close();
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.", throwable);
                } finally {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                }
                return foundAbout;
            }
        }
        return null;
    }

    public List<Skill> getSkills(int userId, int givenProfileId) throws DatabaseException, SQLException {
        List<Skill> foundSkill = new ArrayList<>();

        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId && p.getId() == givenProfileId) {
                Connection connection = this.getDatabaseConnection();
                String sql = "SELECT * FROM skills where profileId = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                try {
                    statement.setInt(1, givenProfileId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int profileId = resultSet.getInt("profileId");
                        String name = resultSet.getString("name");

                        Skill e = new Skill(id, profileId, name);
                        foundSkill.add(e);
                    }
                    connection.setAutoCommit(false);

                    connection.commit();
                    statement.close();
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.", throwable);
                } finally {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();

                }
                return foundSkill;
            }
        }
        return null;
    }

    public User getUser(int userId) throws DatabaseException, SQLException {
        User user = null;

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM users where id =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNr");
                int addressId = resultSet.getInt("addressId");
                String image = resultSet.getString("image");
                int locationId = resultSet.getInt("locationId");
                int departmentId = resultSet.getInt("departmentId");
                String userNumber = resultSet.getString("userNumber");
                UserType r = UserType.Teacher;
                if (userType == "student")
                {
                    r = UserType.Student;
                }
                else if (userType == "employee")
                {
                    r = UserType.Teacher;
                }
                else  if (userType == "admin")
                {
                    r = UserType.FontysStaff;
                }

                user = new User(id, firstName, lastName, r, email, password, phoneNumber, addressId, locationId, departmentId,  userNumber, image);

            }
            connection.setAutoCommit(false);

            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read data from the database.", throwable);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();

        }
        return user;
    }

//    public User getUser(int userId) throws DatabaseException, SQLException {
//
//        for (User u: getUsers()) {
//            if (u.getId() == userId) {
//                return u;
//            }
//        }
//        return null;
//    }

    public boolean createExperience(Experience experience) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        Boolean exist;
        exist = false;

        String sql = "INSERT INTO experiences ( profileId, title, company, location, employmentType, startDate, endDate, description) VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        try {
                preparedStatement.setInt(1, experience.getProfileId());
                preparedStatement.setString(2, experience.getTitle());
                preparedStatement.setString(3, experience.getCompany());
                preparedStatement.setString(4, experience.getLocation());
                preparedStatement.setString(5, String.valueOf(experience.getEmploymentType()));
                preparedStatement.setInt(6,  experience.getStartDateExperience());
                preparedStatement.setInt(7,  experience.getEndDateExperience());
                preparedStatement.setString(8,  experience.getDescriptionExperience());
                preparedStatement.executeUpdate();

//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, "value");
                connection.setAutoCommit(false);
//                ps.close();
                connection.commit();
                preparedStatement.close();
                connection.close();



        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new experience.", throwable);
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();

        }
        return true;

    }

    public boolean createEducation(Education education) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        Boolean exist;
        exist = false;

        String sql = "INSERT INTO educations ( profileId, school, startYear, endYear, degree, fieldStudy, description) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);

        try {
            preparedStatement.setInt(1, education.getProfileId());
            preparedStatement.setString(2, education.getSchool());
            preparedStatement.setInt(3, education.getStartYearEducation());
            preparedStatement.setInt(4, education.getEndYearEducation());
            preparedStatement.setString(5,  education.getDegreeEducation());
            preparedStatement.setString(6,  education.getFieldStudy());
            preparedStatement.setString(7,  education.getDescriptionEducation());
            preparedStatement.executeUpdate();

//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, "value");
            connection.setAutoCommit(false);
//            ps.close();
            connection.commit();
            preparedStatement.close();
            connection.close();



        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new education.", throwable);
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return true;
    }

    public boolean createSkill(Skill skill, int userId) throws DatabaseException, SQLException {


        for (Skill p: getSkills(userId, skill.getProfileId())) {
          if (p.getName().equals(skill.getName())) {
              return false;
          }
        }
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO skills ( profileId, name) VALUES (?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);

        try {
            preparedStatement.setInt(1, skill.getProfileId());
            preparedStatement.setString(2, skill.getName());

            preparedStatement.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();
            preparedStatement.close();
            connection.close();
            return true;

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new skill.", throwable);
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

    }


    public int createProfile(Profile newProfile, int userId) throws DatabaseException, SQLException {

        int id = 0;
        boolean exist;
        exist = false;

        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId) {
                if (p.getLanguage().equals(newProfile.getLanguage())) {
                   exist = true;
                    return 0;
                }
            }
        }
        Connection connection = this.getDatabaseConnection();
        ResultSet rs = null;
        if(!exist) {
            String sql = "INSERT INTO profiles (userId, language) VALUES (?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            try {
                preparedStatement.setInt(1, newProfile.getUserId());
                preparedStatement.setString(2, newProfile.getLanguage());

                preparedStatement.executeUpdate();

                rs = preparedStatement.getGeneratedKeys();
                if(rs != null && rs.next()){
                    System.out.println("Generated Emp Id: "+rs.getInt(1));
                    id = rs.getInt(1);
                }
                
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, "id");
                connection.setAutoCommit(false);
//                ps.close();

                connection.commit();
                preparedStatement.close();
                connection.close();
                return id;


            } catch (SQLException throwable) {
                throw new DatabaseException("Cannot create new profile.", throwable);
            } finally {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            }
        }
        return 0;
    }

    public boolean createAbout(About about) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        String sql = "INSERT INTO about ( profileId, content) VALUES (?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        try {
            preparedStatement.setInt(1, about.getProfileId());
            preparedStatement.setString(2, about.getContent());

            preparedStatement.executeUpdate();

            connection.setAutoCommit(false);


            connection.commit();
            preparedStatement.close();
            connection.close();
            return true;


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new about.", throwable);
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

    }


    public boolean uploadImage(int userId, String path) throws DatabaseException, SQLException, IOException {
        Connection connection = this.getDatabaseConnection();

        String sql = "update users set image=? where id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);


        try {
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

            connection.setAutoCommit(false);


            connection.commit();
            connection.close();
            preparedStatement.close();
            return true;


        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot upload image.", throwable);
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    public List<Location> getFontysLocation() throws DatabaseException, SQLException {
        List<Location> fontysLocations = new ArrayList<>();

            Connection connection = this.getDatabaseConnection();
            String sql = "SELECT * FROM fontysLocations";
            PreparedStatement statement = connection.prepareStatement(sql);
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String streetName = resultSet.getString("streetName");
                    String buildingNumber = resultSet.getString("buildingNumber");
                    String city = resultSet.getString("city");
                    String zipcode = resultSet.getString("zipcode");

                    Location e = new Location(id, streetName, buildingNumber, city, zipcode);
                    fontysLocations.add(e);
                }
                connection.setAutoCommit(false);
                connection.commit();

            } catch (SQLException throwable) {
                throw new DatabaseException("Cannot read students from the database.", throwable);
            } finally {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            }

        return fontysLocations;
    }

    public List<Department> getFontysDepartments() throws DatabaseException, SQLException {
        List<Department> fontysDepartments = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM departments";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Department e = new Department(id, name, description);
                fontysDepartments.add(e);
            }
            connection.setAutoCommit(false);
            statement.close();
            connection.commit();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read students from the database.", throwable);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return fontysDepartments;
    }


    public int createAddress(Address address) throws DatabaseException, SQLException {
        int id = 0;
        Connection connection = this.getDatabaseConnection();
        ResultSet rs = null;

        String sql = "INSERT INTO addresses (streetName, houseNumber, city, zipcode) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        try {
            preparedStatement.setString(1, address.getStreetName());
            preparedStatement.setString(2, address.getHouseNumber());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getZipCode());

            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();
            if(rs != null && rs.next()){
                System.out.println("Generated Emp Id: "+rs.getInt(1));
                id = rs.getInt(1);
            }
            connection.setAutoCommit(false);
            connection.commit();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new experience.", throwable);
        }
        finally {

            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();

        }
        return id;
    }

    public boolean createUser(User user) throws DatabaseException, SQLException {

        Connection connection = this.getDatabaseConnection();

        String sql = "INSERT INTO users (firstName, lastName,  userType, email, password, phoneNr, addressId, image, locationId, departmentId, userNumber) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        try {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, String.valueOf(user.getUserType()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setInt(7, user.getAddressId());
            preparedStatement.setString(8, user.getImg());
            preparedStatement.setInt(9, user.getLocationId());
            preparedStatement.setInt(10, user.getDepartmentId());
            preparedStatement.setString(11, user.getUserNumber());

            preparedStatement.executeUpdate();

//            rs = preparedStatement.getGeneratedKeys();
//            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
//                id = rs.getInt(1);
//            }
            connection.setAutoCommit(false);
            preparedStatement.close();
            connection.commit();

        } catch (SQLException throwable) {
            throw new DatabaseException("Something Wrong with query", throwable);
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();

        }
        return true;
    }
}
