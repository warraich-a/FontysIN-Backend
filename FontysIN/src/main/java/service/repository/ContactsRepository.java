package service.repository;

import service.model.Contact;
import service.model.User;
import service.model.UserType;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository extends JDBCRepository {
    public int createContact(ContactDTO createdContactDTO) throws  DatabaseException  {
        Connection connection = super.getDatabaseConnection();

        String sql = "INSERT INTO contacts (userId, friendId, isAccepted) VALUES (?, ?, false)";

        try {
            int contactId = -1;

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, createdContactDTO.getUser().getId());
            statement.setInt(2, createdContactDTO.getFriend().getId());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet != null && resultSet.next()) {
                contactId = resultSet.getInt(1);
            }

            connection.commit();
            connection.close();
            statement.close();

            return contactId;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot create the contact");
        }
    }

    // Delete or Reject
    public boolean deleteContact(int userId, int contactId) throws  DatabaseException {
        Connection connection = super.getDatabaseConnection();

        String sql = "DELETE FROM contacts WHERE id = ? AND (userId = ? OR friendId = ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, contactId);
            statement.setInt(2, userId);
            statement.setInt(3, userId);

            statement.executeUpdate();

            connection.commit();
            statement.close();
            connection.close();

            return true;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot delete the contact", throwable);
        }
    }


    public List<Contact> getAllContacts(int id) throws DatabaseException {
        List<Contact> contacts = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "users.id AS userId, users.firstName AS userFirstName, users.lastName AS userLastName, users.userType, users.email AS userEmail, users.password AS userPassword, " +
                "users.phoneNr AS userPhoneNr, users.addressId AS userAddressId, users.image AS userImage, users.locationId AS userLocationId, users.departmentId AS userDepartmentId, users.userNumber " +
                ", friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.userType AS friendType, friend.email AS friendEmail, friend.password AS friendPassword, " +
                "friend.phoneNr AS friendPhoneNr, friend.addressId AS friendAddressId, friend.image AS friendImage, friend.locationId AS friendLocationId, friend.departmentId AS friendDepartmentId, friend.userNumber AS friendNumber " +
                "FROM contacts " +
                "INNER JOIN users ON users.id = contacts.userId " +
                "INNER JOIN users friend ON friend.id = contacts.friendId " +
                "WHERE contacts.userId = ? OR contacts.friendId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");

                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("userEmail");
                String password = resultSet.getString("userPassword");
                String phoneNr = resultSet.getString("userPhoneNr");
                int addressId = resultSet.getInt("userAddressId");
                String image = resultSet.getString("userImage");
                int locationId = resultSet.getInt("userLocationId");
                int departmentId = resultSet.getInt("userDepartmentId");
                String userNumber = resultSet.getString("userNumber");

                UserType type = UserType.Teacher;
                if (userType == "student")
                {
                    type = UserType.Student;
                }
                else if (userType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (userType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                User user = new User(id, firstName, lastName, type, email, password, phoneNr, addressId, locationId, departmentId, userNumber, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendType = resultSet.getString("friendType");
                String friendEmail = resultSet.getString("friendEmail");
                String friendPassword = resultSet.getString("friendPassword");
                String friendPhoneNr = resultSet.getString("friendPhoneNr");
                int friendAddressId = resultSet.getInt("friendAddressId");
                String friendImage = resultSet.getString("friendImage");
                int friendLocationId = resultSet.getInt("friendLocationId");
                int friendDepartmentId = resultSet.getInt("friendDepartmentId");
                String friendNumber = resultSet.getString("friendNumber");

                type = UserType.Teacher;
                if (friendType == "student")
                {
                    type = UserType.Student;
                }
                else if (friendType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (friendType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                User friend = new User(friendId, friendFirstName, friendLastName, type, friendEmail, friendPassword, friendPhoneNr, friendAddressId, friendLocationId, friendDepartmentId, friendNumber, friendImage);

                Contact contact = new Contact(contactId, user, friend);

                contacts.add(contact);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
        return contacts;
    }



    public List<ContactDTO> getAllContactsDTO(int id) throws DatabaseException {
        // All contacts of current user
        List<Contact> allContacts = getAllContacts(id);

        List<ContactDTO> allContactsDTO = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId, " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId " +
                "FROM contacts " +
                "LEFT JOIN users USER ON contacts.userId = user.id " +
                "LEFT JOIN users friend ON contacts.friendId = friend.id " +
                "LEFT JOIN " +
                "  (SELECT id AS userProfileId, userId " +
                "FROM profiles " +
                "GROUP BY userId) p1 ON p1.userId = user.id " +
                "LEFT JOIN " +
                "  (SELECT id AS friendProfileId, userId " +
                "   FROM profiles " +
                "   GROUP BY userId) p2 ON p2.userId = friend.id " +
                "WHERE (user.id = ? " +
                "       OR friend.id = ?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");


                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");


                UserDTO friend = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);

                ContactDTO contact = new ContactDTO(contactId, user, friend, isAccepted);

                allContactsDTO.add(contact);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
        return allContactsDTO;
    }

    // Accepted contacts
    public List<Contact> getContacts(int id) throws DatabaseException {
        List<Contact> acceptedContacts = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "users.id AS userId, users.firstName AS userFirstName, users.lastName AS userLastName, users.userType, users.email AS userEmail, users.password AS userPassword, " +
                "users.phoneNr AS userPhoneNr, users.addressId AS userAddressId, users.image AS userImage, users.locationId AS userLocationId, users.departmentId AS userDepartmentId, users.userNumber " +
                ", friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.userType AS friendType, friend.email AS friendEmail, friend.password AS friendPassword, " +
                "friend.phoneNr AS friendPhoneNr, friend.addressId AS friendAddressId, friend.image AS friendImage, friend.locationId AS friendLocationId, friend.departmentId AS friendDepartmentId, friend.userNumber AS friendNumber " +
                "FROM contacts " +
                "INNER JOIN users ON users.id = contacts.userId " +
                "INNER JOIN users friend ON friend.id = contacts.friendId " +
                "WHERE (contacts.userId = ? OR contacts.friendId = ?) AND isAccepted = true";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");

                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("userEmail");
                String password = resultSet.getString("userPassword");
                String phoneNr = resultSet.getString("userPhoneNr");
                int addressId = resultSet.getInt("userAddressId");
                String image = resultSet.getString("userImage");
                int locationId = resultSet.getInt("userLocationId");
                int departmentId = resultSet.getInt("userDepartmentId");
                String userNumber = resultSet.getString("userNumber");

                UserType type = UserType.Teacher;
                if (userType == "student")
                {
                    type = UserType.Student;
                }
                else if (userType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (userType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                User user = new User(id, firstName, lastName, type, email, password, phoneNr, addressId, locationId, departmentId, userNumber, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendType = resultSet.getString("friendType");
                String friendEmail = resultSet.getString("friendEmail");
                String friendPassword = resultSet.getString("friendPassword");
                String friendPhoneNr = resultSet.getString("friendPhoneNr");
                int friendAddressId = resultSet.getInt("friendAddressId");
                String friendImage = resultSet.getString("friendImage");
                int friendLocationId = resultSet.getInt("friendLocationId");
                int friendDepartmentId = resultSet.getInt("friendDepartmentId");
                String friendNumber = resultSet.getString("friendNumber");

                type = UserType.Teacher;
                if (friendType == "student")
                {
                    type = UserType.Student;
                }
                else if (friendType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (friendType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                User friend = new User(friendId, friendFirstName, friendLastName, type, friendEmail, friendPassword, friendPhoneNr, friendAddressId, friendLocationId, friendDepartmentId, friendNumber, friendImage);

                Contact contact = new Contact(contactId, user, friend);

                acceptedContacts.add(contact);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
        return acceptedContacts;
    }


    public List<ContactDTO> getAcceptedContactsDTO(int id) throws DatabaseException {
        List<ContactDTO> acceptedContacts = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.* , " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.* " +
                "FROM contacts " +
                "LEFT JOIN users user ON contacts.userId = user.id " +
                "LEFT JOIN users friend ON contacts.friendId = friend.id " +
                "LEFT JOIN (SELECT id AS userProfileId, userId FROM profiles GROUP BY userId) p1 ON p1.userId = user.id " +
                "LEFT JOIN (SELECT id AS friendProfileId, userId FROM profiles GROUP BY userId) p2 ON p2.userId = friend.id " +
                "   WHERE (user.id = ? OR friend.id = ?) AND contacts.isAccepted = true";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");


                UserDTO friend = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);

                ContactDTO contact = new ContactDTO(contactId, user, friend, isAccepted);

                acceptedContacts.add(contact);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
        return acceptedContacts;
    }


    // Get requests
    public List<Contact> getContactsRequests(int id) throws DatabaseException{
        List<Contact> requests = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "users.id AS userId, users.firstName AS userFirstName, users.lastName AS userLastName, users.userType, users.email AS userEmail, users.password AS userPassword, " +
                "users.phoneNr AS userPhoneNr, users.addressId AS userAddressId, users.image AS userImage, users.locationId AS userLocationId, users.departmentId AS userDepartmentId, users.userNumber " +
                ", friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.userType AS friendType, friend.email AS friendEmail, friend.password AS friendPassword, " +
                "friend.phoneNr AS friendPhoneNr, friend.addressId AS friendAddressId, friend.image AS friendImage, friend.locationId AS friendLocationId, friend.departmentId AS friendDepartmentId, friend.userNumber AS friendNumber " +
                "FROM contacts " +
                "INNER JOIN users ON users.id = contacts.userId " +
                "INNER JOIN users friend ON friend.id = contacts.friendId " +
                "   WHERE contacts.friendId = ? AND isAccepted = false";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");

                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("userEmail");
                String password = resultSet.getString("userPassword");
                String phoneNr = resultSet.getString("userPhoneNr");
                int addressId = resultSet.getInt("userAddressId");
                String image = resultSet.getString("userImage");
                int locationId = resultSet.getInt("userLocationId");
                int departmentId = resultSet.getInt("userDepartmentId");
                String userNumber = resultSet.getString("userNumber");

                UserType type = UserType.Teacher;
                if (userType == "student")
                {
                    type = UserType.Student;
                }
                else if (userType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (userType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                User user = new User(id, firstName, lastName, type, email, password, phoneNr, addressId, locationId, departmentId, userNumber, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendType = resultSet.getString("friendType");
                String friendEmail = resultSet.getString("friendEmail");
                String friendPassword = resultSet.getString("friendPassword");
                String friendPhoneNr = resultSet.getString("friendPhoneNr");
                int friendAddressId = resultSet.getInt("friendAddressId");
                String friendImage = resultSet.getString("friendImage");
                int friendLocationId = resultSet.getInt("friendLocationId");
                int friendDepartmentId = resultSet.getInt("friendDepartmentId");
                String friendNumber = resultSet.getString("friendNumber");

                type = UserType.Teacher;
                if (friendType == "student")
                {
                    type = UserType.Student;
                }
                else if (friendType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (friendType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                User friend = new User(friendId, friendFirstName, friendLastName, type, friendEmail, friendPassword, friendPhoneNr, friendAddressId, friendLocationId, friendDepartmentId, friendNumber, friendImage);

                Contact contact = new Contact(contactId, user, friend);

                requests.add(contact);
            }

            connection.commit();
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts requests from the database.", throwable);
        }
        return requests;

    }

    public List<ContactDTO> getContactsRequestsDTO(int id) throws DatabaseException {
        List<ContactDTO> requests = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.* , " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.* " +
                "FROM contacts " +
                "LEFT JOIN users user ON contacts.userId = user.id " +
                "LEFT JOIN users friend ON contacts.friendId = friend.id " +
                "LEFT JOIN (SELECT id AS userProfileId, userId FROM profiles GROUP BY userId) p1 ON p1.userId = user.id " +
                "LEFT JOIN (SELECT id AS friendProfileId, userId FROM profiles GROUP BY userId) p2 ON p2.userId = friend.id " +
                "   WHERE (friend.id = ?) AND contacts.isAccepted = false";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");


                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");


                UserDTO friend = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);

                ContactDTO contact = new ContactDTO(contactId, user, friend, isAccepted);

                requests.add(contact);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts requests from the database.", throwable);
        }
        return requests;
    }


    public void updateContact(int contactId, Contact updatedContact) throws DatabaseException {
        Connection connection = super.getDatabaseConnection();

        String sql = "UPDATE contacts SET isAccepted = ? WHERE (userId = ? AND friendId = ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, updatedContact.getIsAccepted());
            statement.setInt(2, updatedContact.getUser().getId());
            statement.setInt(3, updatedContact.getFriend().getId());

            statement.executeUpdate();

            connection.commit();
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot update the contact", throwable);
        }
    }


    public UserDTO getUserDTO(int id) throws DatabaseException {
        String project_path =System.getProperty("user.dir");
        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, p.id AS profileId, image FROM users AS u " +
                "INNER JOIN profiles p ON p.userId = u.id " +
                "WHERE u.id = ? " +
                "LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                connection.close();
                throw new DatabaseException("User with user id " + id + " cannot be found");
            }
            else {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");
                int profileId = resultSet.getInt("profileId");
                statement.close();
                connection.close();

                return new UserDTO(id, profileId, firstName, lastName, image);
            }

        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read user from the database.", throwable);
        }
    }


    public User getUser(int id) throws DatabaseException {
        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT * FROM user WHERE userId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                connection.close();
                throw new DatabaseException("User with user id " + id + " cannot be found");
            }
            else {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String userType = resultSet.getString("userType");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNr = resultSet.getString("phoneNr");
                int addressId = resultSet.getInt("addressId");
                String image = resultSet.getString("image");
                int locationId = resultSet.getInt("locationId");
                int departmentId = resultSet.getInt("departmentId");
                String userNumber = resultSet.getString("userNumber");



                UserType type = UserType.Teacher;
                if (userType == "student")
                {
                    type = UserType.Student;
                }
                else if (userType == "employee")
                {
                    type = UserType.Teacher;
                }
                else  if (userType == "admin")
                {
                    type = UserType.FontysStaff;
                }

                statement.close();
                connection.close();

                return new User(id, firstName, lastName, type, email, password, phoneNr, addressId, locationId, departmentId, userNumber, image);
            }

        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read users from the database.", throwable);
        }
    }




}
