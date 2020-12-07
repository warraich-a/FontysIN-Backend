package service.repository;

import service.model.*;
import service.model.dto.UserDTO;

import javax.sql.rowset.JdbcRowSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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
    public Experience getExperienceById(int expId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM experiences WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, expId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Experience with id " + expId + " cannot be found");
            } else {
                int Id = resultSet.getInt("id");
                int profileId = resultSet.getInt("profileId");
                String title = resultSet.getString("title");
                String company = resultSet.getString("company");
                String 	location= resultSet.getString("location");
                String content = resultSet.getString("title");
                String empType = resultSet.getString("employmentType");
                int startDate = resultSet.getInt("startDate");
                int endDate = resultSet.getInt("endDate");
                String description = resultSet.getString("description");
                EmplymentType r = EmplymentType.FullTime;
                if (empType == "FullTime")
                {
                    r = EmplymentType.FullTime;
                }
                else if (empType == "PartTime")
                {
                    r = EmplymentType.PartTime;
                }
                else  if (empType == "FreeLancer")
                {
                    r = EmplymentType.FreeLancer;
                }
                Experience e = new Experience(Id, profileId, title, company, r, location,  startDate, endDate, description);
                connection.close();
                return e;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }
    public boolean updateExperience(Experience ex) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `experiences` SET `title`=?,`company`=?,`location`=?,`employmentType`=?,`startDate`=?,`endDate`=?,`description`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, ex.getTitle());
            statement.setString(2, ex.getCompany());
            statement.setString(3, ex.getLocation());
            statement.setString(4, String.valueOf(ex.getEmploymentType()));
            statement.setInt(5, ex.getStartDateExperience());
            statement.setInt(6, ex.getEndDateExperience());
            statement.setString(7, ex.getDescriptionExperience());
            statement.setInt(8, ex.getId());
            statement.executeUpdate();
            connection.commit();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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
    public Education getEducationById(int eduId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM educations WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, eduId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Experience with id " + eduId + " cannot be found");
            } else {
                int id = resultSet.getInt("id");
                int profileId = resultSet.getInt("profileId");
                String school = resultSet.getString("school");
                int startYear = resultSet.getInt("startYear");
                int endYear = resultSet.getInt("endYear");
                String degree = resultSet.getString("degree");
                String fieldStudy = resultSet.getString("fieldStudy");
                String description = resultSet.getString("description");

                Education e = new Education(id, profileId, school, startYear, endYear, degree, fieldStudy, description);
                connection.close();
                return e;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }
    public boolean updateEducation(Education education) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `educations` SET `school`=?,`startYear`=?,`endYear`=?,`degree`=?,`fieldStudy`=?,`description`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setString(1, education.getSchool());
            statement.setInt(2, education.getStartYearEducation());
            statement.setInt(3, education.getEndYearEducation());
            statement.setString(4,  education.getDegreeEducation());
            statement.setString(5,  education.getFieldStudy());
            statement.setString(6,  education.getDescriptionEducation());
            statement.setInt(7, education.getId());

            statement.executeUpdate();
            connection.commit();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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
    public About getAboutById(int aboId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM about WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aboId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Experience with id " + aboId + " cannot be found");
            } else {
                int id = resultSet.getInt("id");
                int profileId = resultSet.getInt("profileId");
                String content = resultSet.getString("content");

                About e = new About(id, profileId, content);
                connection.close();
                return e;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }

    public boolean updateAbout(About about) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `about` SET `content`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, about.getContent());
            statement.setInt(2, about.getId());



            statement.executeUpdate();
            connection.commit();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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

    public List<User> getUsers() throws DatabaseException, SQLException {
        List<User> allUsers = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
//                String phoneNumber = resultSet.getString("phoneNr");
//                int addressId = resultSet.getInt("addressId");
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

                User u = new User(id, firstName, lastName, r, email, password, locationId, departmentId,  userNumber, image);
                allUsers.add(u);

            }
            statement.close();
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read data from the database.", throwable);
        }
        finally {
            if  (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return allUsers;
    }


    public User getUserById(int userId) throws DatabaseException, SQLException {
        JDBCPrivacyRepository privacyRepository = new JDBCPrivacyRepository();
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
//                String phoneNumber = resultSet.getString("phoneNr");
//                int addressId = resultSet.getInt("addressId");
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

                user = new User(id, firstName, lastName, r, email, password, locationId, departmentId,  userNumber, image);
                Privacy privacy = privacyRepository.getPrivacyByUser(user);
                user.setPrivacy(privacy);
            }
            connection.setAutoCommit(false);

            connection.commit();
            statement.close();
          connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read data from the database.", throwable);
        }
        finally {
            if  (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return user;
    }



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
//        PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        try {
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();

            connection.setAutoCommit(false);


            connection.commit();
            preparedStatement.close();

            connection.close();
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
                statement.close();
                connection.close();

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
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read students from the database.", throwable);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return fontysDepartments;
    }

    public Address getAddressById(int aId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM addresses WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Experience with id " + aId + " cannot be found");
            } else {
                int id = resultSet.getInt("id");
                String 	streetName = resultSet.getString("streetName");
                String houseNumber = resultSet.getString("houseNumber");
                String city = resultSet.getString("city");
                String zipcode = resultSet.getString("zipcode");

                Address a = new Address(id, streetName,houseNumber,city,zipcode);
                connection.close();
                return a;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }


    // ----------------------------------------- Privacy

    // ----------------------------------------- Privacy ^
    /******************Ranim******************Deleting data in profile page*********************/

    // Delete education in profile page
    public void deleteEducation(int userId, int profileId, int educationId) throws DatabaseException {

        Connection connection = this.getDatabaseConnection();

        String sql = "Delete FROM educations where id = ? AND profileId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,educationId);
            preparedStatement.setInt(2,profileId);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException throwable){
            throw  new DatabaseException("Cannot delete education.", throwable);
        }

    }

    // Delete experience in profile page
    public void deleteExperience(int userId, int profileId, int experienceId) throws DatabaseException {

        Connection connection = this.getDatabaseConnection();

        String sql = "Delete FROM experiences where id = ? AND profileId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,experienceId);
            preparedStatement.setInt(2,profileId);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException throwable){
            throw  new DatabaseException("Cannot delete experience.", throwable);
        }

    }

    // Delete skill in profile page
    public void deleteSkill(int userId, int profileId, int skillId) throws DatabaseException {

        Connection connection = this.getDatabaseConnection();

        String sql = "Delete FROM skills where id = ? AND profileId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,skillId);
            preparedStatement.setInt(2,profileId);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException throwable){
            throw  new DatabaseException("Cannot delete skill.", throwable);
        }

    }

    /**************Ranim******************************Filter users**************************/
    //get all users with the given user type from data base
    public List<UserDTO> getUsersByType(UserType type) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId WHERE u.userType = ? GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type.name()); // set user type parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByLocation(int lId) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId WHERE u.locationId = ? GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lId); // set location id parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByDepartment(int bId) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId WHERE u.departmentId = ? GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bId); // set user fontys location parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByStartStudyYear(int year) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((educations INNER JOIN profiles p ON educations.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) WHERE school = 'Fontys'" +
                "AND u.userType = 'Student' AND startYear = ? GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, year); // set user start study year parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByStartWorkYear(int year) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();


        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((experiences INNER JOIN profiles p ON experiences.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) WHERE company = 'Fontys'" +
                "AND u.userType != 'Student' AND startDate = ? GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, year); // set user start study year parameter
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByUserTypeAndLocationAndDepartment(UserType type, int lId, int dId) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM profiles p " +
                "LEFT JOIN users u ON u.id = p.userId WHERE userType = ? " +
                "AND locationId = ? AND departmentId = ? GROUP BY u.id";
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
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByUserTypeAndStartStudyYearAndDepartmentAndLocation(UserType type, int year, int lId, int dId) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((educations INNER JOIN profiles p ON educations.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) WHERE school = 'Fontys'" +
                "AND u.userType = ? AND u.locationId = ? AND u.departmentId = ? AND startYear = ? GROUP BY u.id";
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
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByUserTypeAndStartWorkYearAndDepartmentAndLocationFontysStaff(UserType type, int year, int lId, int dId) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, u.image, p.id AS profileId " +
                "FROM ((experiences INNER JOIN profiles p ON experiences.profileId = p.id) " +
                "INNER JOIN users u ON p.userId = u.id) WHERE company = 'Fontys'" +
                "AND u.userType = ? AND u.locationId = ? AND u.departmentId = ? AND startDate = ? GROUP BY u.id";
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
                String image = resultSet.getString("image");
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
    public List<User> getAllUsers() throws DatabaseException {

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
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersDTO() throws DatabaseException {

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
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByUserTypeLocationDeoartmentAndName(String chars, int lId, int dId, UserType type) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM users u " +
                "INNER JOIN profiles p ON u.id = p.userId WHERE u.firstName " +
                "LIKE CONCAT ('%', ? ,'%') AND u.locationId = ? AND u.departmentId = ? AND userType = ? GROUP BY u.id";
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
                String image = resultSet.getString("image");
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
    public List<UserDTO> getUsersByFirstNameChars(String chars) throws DatabaseException {

        List<UserDTO> filtered = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, image, p.id AS profileId FROM users u " +
                "INNER JOIN profiles p ON u.id = p.userId WHERE u.firstName " +
                "LIKE CONCAT ('%', ? ,'%') GROUP BY u.id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chars); // set characters

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
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
        JDBCPrivacyRepository privacyRepository = new JDBCPrivacyRepository();
        boolean exist;
        exist = false;
        for (User u: getUsers()) {
            if(u.getEmail().equals(user.getEmail())){
                exist = true;
//                deleteAdrress(u.getAddressId());
            } else if(u.getFirstName().equals(user.getFirstName())
                    && u.getUserNumber().equals(user.getUserNumber())){
                exist = true;
            }
        }
        Connection connection = this.getDatabaseConnection();

        if(!exist) {

        ResultSet rs = null;
        int userId = 9999;

            String sql = "INSERT INTO users (firstName, lastName,  userType, email, password, image, locationId, departmentId, userNumber) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            try {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, String.valueOf(user.getUserType()));
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getPassword());
//                preparedStatement.setString(6, user.getPhoneNumber());
//                preparedStatement.setInt(7, user.getAddressId());
                preparedStatement.setString(6, user.getImg());
                preparedStatement.setInt(7, user.getLocationId());
                preparedStatement.setInt(8, user.getDepartmentId());
                preparedStatement.setString(9, user.getUserNumber());

                preparedStatement.executeUpdate();


//            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
//                id = rs.getInt(1);
//            }

//                connection.setAutoCommit(false);
//                connection.commit();
//                preparedStatement.close();
//                connection.close();
                rs = preparedStatement.getGeneratedKeys();
                if(rs != null && rs.next()){
                    System.out.println("Generated Emp Id: "+rs.getInt(1));
                    userId = rs.getInt(1);
                }
                connection.setAutoCommit(false);
                connection.commit();
                preparedStatement.close();
                connection.close();

                Privacy p = new Privacy(userId);
                privacyRepository.createPrivacy(p);

                return true;
            } catch (SQLException throwable) {
                throw new DatabaseException("Something Wrong with query", throwable);
            } finally {

                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            }

        }
        return false;

    }

}
