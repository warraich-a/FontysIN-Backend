package service.repository;

import service.model.*;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository extends JDBCRepository {
    JDBCRepository jdbcRepository;

    public ProfileRepository() {
        this.jdbcRepository = new JDBCRepository();
    }

    public List<Profile> getProfile(int givenUserId) throws DatabaseException, URISyntaxException, SQLException {
        List<Profile> foundProfiles = new ArrayList<>();


        Connection connection = jdbcRepository.getDatabaseConnection();
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

    public List<Experience> getExperiences(int userId, int givenProfileId) throws DatabaseException, URISyntaxException, SQLException {
        List<Experience> foundExperiences = new ArrayList<>();
        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId && p.getId() == givenProfileId) {
                Connection connection = jdbcRepository.getDatabaseConnection();
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
                        if (emplymentType.equals("FullTime"))
                        {
                            r = EmplymentType.FullTime;
                        }
                        else if (emplymentType.equals("PartTime"))
                        {
                            r = EmplymentType.PartTime;
                        }
                        else  if (emplymentType.equals("FreeLancer"))
                        {
                            r = EmplymentType.FreeLancer;
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
    public Experience getExperienceById(int expId) throws DatabaseException, URISyntaxException {
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
                if (empType.equals("FullTime"))
                {
                    r = EmplymentType.FullTime;
                }
                else if (empType.equals("PartTime"))
                {
                    r = EmplymentType.PartTime;
                }
                else  if (empType.equals("FreeLancer"))
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
    public boolean updateExperience(Experience ex) throws DatabaseException, URISyntaxException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `experiences` SET `title`=?,`company`=?,`location`=?,`employmentType`=?,`startDate`=?,`endDate`=?,`description`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, ex.getTitle());
            statement.setString(2, ex.getCompany());
            statement.setString(3, ex.getLocation());
            statement.setString(4, ex.getEmploymentType().toString());
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

    public List<Education> getEducations(int userId, int givenProfileId) throws DatabaseException, SQLException, URISyntaxException {
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
    public Education getEducationById(int eduId) throws DatabaseException, URISyntaxException {
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
    public boolean updateEducation(Education education) throws DatabaseException, URISyntaxException {
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

    public List<About> getAbout(int userId, int givenProfileId) throws DatabaseException, SQLException, URISyntaxException {
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
    public About getAboutById(int aboId) throws DatabaseException, URISyntaxException {
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

    public boolean updateAbout(About about) throws DatabaseException, URISyntaxException {
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

    public List<Skill> getSkills(int userId, int givenProfileId) throws DatabaseException, SQLException, URISyntaxException {
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

    public List<User> getUsers() throws DatabaseException, SQLException, URISyntaxException {
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

    PrivacyRepository privacyRepository = new PrivacyRepository();
    public User getUserById(int userId) throws DatabaseException, SQLException, URISyntaxException {

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



    public boolean createExperience(Experience experience) throws DatabaseException, SQLException, URISyntaxException {
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

    public boolean createEducation(Education education) throws DatabaseException, SQLException, URISyntaxException {
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

    public boolean createSkill(Skill skill, int userId) throws DatabaseException, SQLException, URISyntaxException {


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


    public int createProfile(Profile newProfile, int userId) throws DatabaseException, SQLException, URISyntaxException {

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

    public boolean createAbout(About about) throws DatabaseException, SQLException, URISyntaxException {
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


    public boolean uploadImage(int userId, String path) throws DatabaseException, URISyntaxException, SQLException {
        Connection connection = this.getDatabaseConnection();

        String sql = "update users set image=? where id = ? ";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        try {
            System.out.println("Path " + path);

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

    public List<Location> getFontysLocation() throws DatabaseException, SQLException, URISyntaxException {
        List<Location> fontysLocations = new ArrayList<>();

            Connection connection = this.getDatabaseConnection();
            String sql = "SELECT * FROM fontyslocations";
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

    public List<Department> getFontysDepartments() throws DatabaseException, SQLException, URISyntaxException {
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


    /******************Ranim******************Deleting data in profile page*********************/

    // Delete education in profile page
    public void deleteEducation(int userId, int profileId, int educationId) throws DatabaseException, URISyntaxException {

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
    public void deleteExperience(int userId, int profileId, int experienceId) throws DatabaseException, URISyntaxException {

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
    public void deleteSkill(int userId, int profileId, int skillId) throws DatabaseException, URISyntaxException {

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


    public boolean createUser(User user) throws DatabaseException, SQLException, URISyntaxException {
        PrivacyRepository privacyRepository = new PrivacyRepository();
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
                preparedStatement.setString(6, user.getImg());
                preparedStatement.setInt(7, user.getLocationId());
                preparedStatement.setInt(8, user.getDepartmentId());
                preparedStatement.setString(9, user.getUserNumber());

                preparedStatement.executeUpdate();

                System.out.println(preparedStatement);


//            if(rs != null && rs.next()){
//                System.out.println("Generated Emp Id: "+rs.getInt(1));
//                id = rs.getInt(1);
//            }

                rs = preparedStatement.getGeneratedKeys();
                if(rs != null && rs.next()){
                    System.out.println("Generated Emp Id: "+rs.getInt(1));
                    userId = rs.getInt(1);
                }
                //connection.setAutoCommit(false);
                connection.commit();
//                preparedStatement.close();
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



    public User getUser() throws DatabaseException, SQLException, URISyntaxException {

        User user = null;
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM users where id = 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            ///statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");


                user = new User(id, firstName, lastName,  email, password);

            }//
            // connection.setAutoCommit(false);

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

}
