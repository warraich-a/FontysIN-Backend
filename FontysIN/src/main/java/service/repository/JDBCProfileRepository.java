package service.repository;

import service.model.*;

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

            statement.setInt(2, about.getId());
            statement.setString(1, about.getContent());


            statement.executeUpdate();
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
    public boolean updateAddress(Address a) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `addresses` SET `streetName`=?,`houseNumber`=?,`city`=?,`zipcode`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(5, a.getId());
            statement.setString(1, a.getStreetName());
            statement.setString(2, a.getHouseNumber());
            statement.setString(3, a.getCity());
            statement.setString(4, a.getZipCode());

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean updatePhone(User u) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `users` SET `phoneNr`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, u.getPhoneNumber());
            statement.setInt(2, u.getId());



            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // ----------------------------------------- Privacy

//    public boolean createPrivacy(Privacy p) throws DatabaseException{
//        Connection connection = this.getDatabaseConnection();
//        boolean exist = false;
//        String sql = "INSERT INTO privacy(`userId`,`educationSetting`, `experienceSetting`, `skillSetting`) VALUES (?,?,?,?)";
//        try {
//            if(!exist){
//                PreparedStatement statement = connection.prepareStatement(sql);
//
//                statement.setInt(1, comm.getUserId());
//                statement.setString(3,comm.getContent());
//                statement.setDate(4, (Date) comm.getDate());
//
//
//                statement.executeUpdate();
//
//                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//                ps.setInt(1,1);
//                connection.setAutoCommit(false);
//                connection.commit();
//                connection.close();
//                return true;
//            } else  {
//                connection.close();
//                return false;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }

    public List<Privacy> getPrivacyList() throws DatabaseException {
        List<Privacy> privacyList = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM privacy";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String educationSetting = resultSet.getString("educationSetting");
                String experienceSetting = resultSet.getString("experienceSetting");
                String skillSetting = resultSet.getString("skillSetting");

                Privacy.Setting edu = Privacy.Setting.EVERYONE;
                if (educationSetting == "EVERYONE")
                {
                    edu = Privacy.Setting.EVERYONE;
                }
                else if (educationSetting == "CONNECTIONS")
                {
                    edu = Privacy.Setting.CONNECTIONS;
                }
                else  if (educationSetting == "ONLYME")
                {
                    edu = Privacy.Setting.ONLYME;
                }
                Privacy.Setting exp = Privacy.Setting.EVERYONE;
                if (experienceSetting == "EVERYONE")
                {
                    exp = Privacy.Setting.EVERYONE;
                }
                else if (experienceSetting == "CONNECTIONS")
                {
                    exp = Privacy.Setting.CONNECTIONS;
                }
                else  if (experienceSetting == "ONLYME")
                {
                    exp = Privacy.Setting.ONLYME;
                }
                Privacy.Setting ski = Privacy.Setting.EVERYONE;
                if (skillSetting == "EVERYONE")
                {
                    ski = Privacy.Setting.EVERYONE;
                }
                else if (skillSetting == "CONNECTIONS")
                {
                    ski = Privacy.Setting.CONNECTIONS;
                }
                else  if (skillSetting == "ONLYME")
                {
                    ski = Privacy.Setting.ONLYME;
                }

                Privacy a = new Privacy(id, userId, edu, exp, ski);
                privacyList.add(a);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read comments from the database.",throwable);
        }
        return privacyList;
    }

    public Privacy getPrivacyById(int pId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM privacy WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Privacy with id " + pId + " cannot be found");
            } else {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String educationSetting = resultSet.getString("educationSetting");
                String experienceSetting = resultSet.getString("experienceSetting");
                String skillSetting = resultSet.getString("skillSetting");

                Privacy.Setting edu = Privacy.Setting.EVERYONE;
                if (educationSetting == "EVERYONE")
                {
                    edu = Privacy.Setting.EVERYONE;
                }
                else if (educationSetting == "CONNECTIONS")
                {
                    edu = Privacy.Setting.CONNECTIONS;
                }
                else  if (educationSetting == "ONLYME")
                {
                    edu = Privacy.Setting.ONLYME;
                }
                Privacy.Setting exp = Privacy.Setting.EVERYONE;
                if (experienceSetting == "EVERYONE")
                {
                    exp = Privacy.Setting.EVERYONE;
                }
                else if (experienceSetting == "CONNECTIONS")
                {
                    exp = Privacy.Setting.CONNECTIONS;
                }
                else  if (experienceSetting == "ONLYME")
                {
                    exp = Privacy.Setting.ONLYME;
                }
                Privacy.Setting ski = Privacy.Setting.EVERYONE;
                if (skillSetting == "EVERYONE")
                {
                    ski = Privacy.Setting.EVERYONE;
                }
                else if (skillSetting == "CONNECTIONS")
                {
                    ski = Privacy.Setting.CONNECTIONS;
                }
                else  if (skillSetting == "ONLYME")
                {
                    ski = Privacy.Setting.ONLYME;
                }

                Privacy a = new Privacy(id, userId, edu, exp, ski);
                connection.close();
                return a;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }
    public boolean updatePrivacy(Privacy a) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `privacy` SET `educationSetting`=?,`experienceSetting`=?,`skillSetting`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(4, a.getId());
            statement.setString(1, String.valueOf(a.getEducationSetting()));
            statement.setString(2, String.valueOf(a.getExperienceSetting()));
            statement.setString(3, String.valueOf(a.getSkillSetting()));

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
