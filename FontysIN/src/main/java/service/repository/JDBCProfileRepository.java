package service.repository;

import service.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read profiles from the database.",throwable);
        }
        finally {
            statement.close();
            connection.close();
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
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.",throwable);
                }
                finally {
                    statement.close();
                    connection.close();
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
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.", throwable);
                } finally {
                    statement.close();
                    connection.close();
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
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.", throwable);
                } finally {
                    statement.close();
                    connection.close();
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
                    connection.close();

                } catch (SQLException throwable) {
                    throw new DatabaseException("Cannot read students from the database.", throwable);
                } finally {
                    statement.close();
                    connection.close();
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

                User u = new User(id, firstName, lastName, r, email, password, phoneNumber, addressId, locationId, departmentId,  userNumber);
                allUsers.add(u);

            }
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read data from the database.", throwable);
        } finally {
            statement.close();
            connection.close();
        }
        return allUsers;
    }

    public User getUser(int userId) throws DatabaseException, SQLException {

        for (User u: getUsers()) {
            if (u.getId() == userId) {
                return u;
            }
        }
        return null;
    }

    public boolean createExperience(Experience experience) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        Boolean exist;
        exist = false;

        String sql = "INSERT INTO experiences ( profileId, title, company, location, startDate, endDate, description) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        try {
                preparedStatement.setInt(1, experience.getProfileId());
                preparedStatement.setString(2, experience.getTitle());
                preparedStatement.setString(3, experience.getCompany());
                preparedStatement.setString(4, experience.getLocation());
                preparedStatement.setInt(5,  experience.getStartDateExperience());
                preparedStatement.setInt(6,  experience.getEndDateExperience());
                preparedStatement.setString(7,  experience.getDescriptionExperience());
                preparedStatement.executeUpdate();

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, "value");
                connection.setAutoCommit(false);
                ps.close();
                connection.commit();
                connection.close();

                return true;

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new experience.", throwable);
        }
        finally {
            preparedStatement.close();
            connection.close();
        }

    }

    public boolean createEducation(Education education) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        Boolean exist;
        exist = false;

        String sql = "INSERT INTO educations ( profileId, school, startYear, endYear, degree, fieldStudy, description) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, education.getProfileId());
            preparedStatement.setString(2, education.getSchool());
            preparedStatement.setInt(3, education.getStartYearEducation());
            preparedStatement.setInt(4, education.getEndYearEducation());
            preparedStatement.setString(5,  education.getDegreeEducation());
            preparedStatement.setString(6,  education.getFieldStudy());
            preparedStatement.setString(7,  education.getDescriptionEducation());
            preparedStatement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "value");
            connection.setAutoCommit(false);
            ps.close();
            connection.commit();
            connection.close();

            return true;

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new experience.", throwable);
        }
        finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public boolean createSkill(Skill skill, int userId) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        for (Skill p: getSkills(userId, skill.getProfileId())) {
          if(p.getId() == skill.getProfileId()){
              if (p.getName().equals(skill.getName())) {
                  return false;
              }
          }
        }
        String sql = "INSERT INTO skills ( profileId, name) VALUES (?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, skill.getProfileId());
            preparedStatement.setString(2, skill.getName());

            preparedStatement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "value");
            connection.setAutoCommit(false);
            ps.close();
            connection.commit();
            connection.close();

            return true;

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new experience.", throwable);
        }
        finally {
            preparedStatement.close();
            connection.close();
        }
    }


    public int createProfile(Profile newProfile, int userId) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();
        int id = 0;
        boolean exist;
        exist = false;
        String generatedColumns[] = { "ID" };

        for (Profile p: getProfile(userId)) {
            if (p.getUserId() == userId) {
                if (p.getLanguage().equals(newProfile.getLanguage())) {
                   exist = true;
                    return 0;
                }
            }
        }
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
                connection.close();
                return id;


            } catch (SQLException throwable) {
                throw new DatabaseException("Cannot create new profile.", throwable);
            } finally {
                preparedStatement.close();
                connection.close();
            }
        }
        return 0;
    }

    public boolean createAbout(About about) throws DatabaseException, SQLException {
        Connection connection = this.getDatabaseConnection();

        String sql = "INSERT INTO about ( profileId, content) VALUES (?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, about.getProfileId());
            preparedStatement.setString(2, about.getContent());

            preparedStatement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "value");
            connection.setAutoCommit(false);
            ps.close();
            connection.commit();
            connection.close();

            return true;

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot create new about.", throwable);
        }
        finally {
            preparedStatement.close();
            connection.close();
        }
    }


    /********************************RANIM*******DELETE DATA IN PROFILE PAGE********************************/

    //get profiles
    public List<Profile> getProfiles() throws DatabaseException {

        List<Profile> profiles = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT * FROM profiles";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int uId = resultSet.getInt("userId");
                String language = resultSet.getString("language");

                Profile p = new Profile(id, uId,language);
                profiles.add(p);
            }

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read profiles from the database.",throwable);
        }
        return profiles;
    }

    //get users
    public List<User> getUsersList() throws DatabaseException {

        List<User> users = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String userType = resultSet.getString("userType");
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

                User u = new User(id, firstName, lastName, r, email, password, phoneNumber, addressId, locationId, departmentId,  userNumber);

                users.add(u);
            }

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.",throwable);
        }
        return users;
    }

    public List<Education> getEducationsList() throws DatabaseException {

        List<Education> educations = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT * FROM educations";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int eId = resultSet.getInt("id");
                int pId = resultSet.getInt("profileId");
                String school = resultSet.getString("school");
                int startYear = resultSet.getInt("startYear");
                int endYear = resultSet.getInt("endYear");
                String degree = resultSet.getString("degree");
                String fieldStudy = resultSet.getString("fieldStudy");
                String description = resultSet.getString("description");

                Education e = new Education(eId,pId,school,startYear,endYear, degree, fieldStudy, description);
                educations.add(e);
            }

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read educations from the database.",throwable);
        }
        return educations;
    }

    //get education by ID
    public Education GetEducationById(int id) throws DatabaseException{

        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT * FROM educations WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id); // set id parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Education with id " + id + " cannot be found");
            } else {
                int eId = resultSet.getInt("id");
                int pId = resultSet.getInt("profileId");
                String school = resultSet.getString("school");
                int startYear = resultSet.getInt("startYear");
                int endYear = resultSet.getInt("endYear");
                String degree = resultSet.getString("degree");
                String fieldStudy = resultSet.getString("fieldStudy");
                String description = resultSet.getString("description");

                connection.close();

                return new Education(eId,pId,school,startYear,endYear, degree, fieldStudy, description);
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read education from the database.",throwable);
        }

    }

    // Delete education in profile page
    public void deleteEducation(int userId, int profileId, int educationId) throws DatabaseException {

        Connection connection = this.getDatabaseConnection();

//        for (User u: getUsersList()){
//            for (Profile p: getProfiles()){
//                for (Education e: getEducationsList()){
//
//                    if (u.getId()==userId && p.getId()==profileId && e.getId()==educationId){
                        String sql = "Delete FROM educations where id = ? AND profileId = ?";
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setInt(1,educationId);
                            preparedStatement.setInt(2,profileId);

                            preparedStatement.executeUpdate();
                            connection.commit();
                        }
                        catch (SQLException throwable){
                            throw  new DatabaseException("Cannot delete education.", throwable);
                        }
//                    }
//
//                }
//            }
//        }

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
        }
        catch (SQLException throwable){
            throw  new DatabaseException("Cannot delete experience.", throwable);
        }

    }
}
