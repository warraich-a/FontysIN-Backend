package service.repository;

import service.model.User;
import service.model.UserType;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    JDBCRepository jdbcRepository;

    public ContactsRepository() {
        this.jdbcRepository = new JDBCRepository();
    }

    public int createContact(ContactDTO createdContactDTO) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();

//        String sql = "INSERT INTO contacts (userId, friendId) " +
//                "SELECT ?, ? WHERE NOT EXISTS (SELECT userId, friendId FROM contacts " +
//                "WHERE (userId = ? AND friendId = ?) OR (userId = ? AND friendId = ?))";
        String sql = "INSERT INTO contacts (userId, friendId) " +
                        "SELECT ?, ? FROM DUAL " +
                        "WHERE NOT EXISTS ( " +
                        "SELECT userId, friendId FROM contacts WHERE (userId = ? AND friendId = ?) OR (userId = ? AND friendId = ?) LIMIT 1)";


        try {
            int contactId = -1;

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, createdContactDTO.getUser().getId());
            statement.setInt(2, createdContactDTO.getFriend().getId());
            statement.setInt(3, createdContactDTO.getUser().getId());
            statement.setInt(4, createdContactDTO.getFriend().getId());
            statement.setInt(5, createdContactDTO.getFriend().getId());
            statement.setInt(6, createdContactDTO.getUser().getId());

            int affected = statement.executeUpdate();

            if(affected <= 0) {
                throw new DatabaseException("Cannot create contact, already exists");
            }

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
            throw new DatabaseException("Cannot create the contact hoho");
        }
    }

    // Delete or Reject
    public boolean deleteContact(int userId, int contactId) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "DELETE FROM contacts WHERE id = ? AND (userId = ? OR friendId = ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, contactId);
            statement.setInt(2, userId);
            statement.setInt(3, userId);

            int affected = statement.executeUpdate();

            if(affected <= 0) {
                return false;
            }

            connection.commit();
            statement.close();
            connection.close();

            return true;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot delete the contact", throwable);
        }
    }

    public List<ContactDTO> getAllContactsDTO(int id) throws DatabaseException, URISyntaxException {
        List<ContactDTO> allContactsDTO = new ArrayList<>();

        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.id AS userProfileId, " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.id AS friendProfileId " +
                "FROM contacts " +
                    "LEFT JOIN users AS user ON contacts.userId = user.id " +
                    "LEFT JOIN users AS friend ON contacts.friendId = friend.id " +
                    "LEFT JOIN profiles AS p1 ON p1.userId = user.id " +
                    "LEFT JOIN profiles AS p2 ON p2.userId = friend.id " +
                    "WHERE (user.id = ? OR friend.id = ?) " +
                    "GROUP BY contacts.id";

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
                String image = "assets/" + resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = "assets/" + resultSet.getString("friendImage");
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
    public List<ContactDTO> getAcceptedContactsDTO(int id) throws DatabaseException, URISyntaxException {
        List<ContactDTO> acceptedContacts = new ArrayList<>();

        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.id AS userProfileId , " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.id AS friendProfileId " +
                "FROM contacts " +
                "LEFT JOIN users user ON contacts.userId = user.id " +
                "LEFT JOIN users friend ON contacts.friendId = friend.id " +
                "LEFT JOIN profiles AS p1 ON p1.userId = user.id " +
                "LEFT JOIN profiles AS p2 ON p2.userId = friend.id " +
                "WHERE (user.id = ? OR friend.id = ?) AND contacts.isAccepted = true " +
                "GROUP BY contacts.id";

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
                String image = "assets/" + resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = "assets/" + resultSet.getString("friendImage");
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
    public List<ContactDTO> getContactsRequestsDTO(int id) throws DatabaseException, URISyntaxException {
        List<ContactDTO> requests = new ArrayList<>();

        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.id AS userProfileId , " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.id AS friendProfileId " +
                "FROM contacts " +
                "LEFT JOIN users user ON contacts.userId = user.id " +
                "LEFT JOIN users friend ON contacts.friendId = friend.id " +
                "LEFT JOIN profiles AS p1 ON p1.userId = user.id " +
                "LEFT JOIN profiles AS p2 ON p2.userId = friend.id " +
                "WHERE (friend.id = ?) AND contacts.isAccepted = false " +
                "GROUP BY contacts.id";

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
                String image = "assets/" + resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = "assets/" + resultSet.getString("friendImage");
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


    public boolean updateContact(int contactId, ContactDTO updatedContact) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "UPDATE contacts SET isAccepted = ? WHERE (userId = ? AND friendId = ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBoolean(1, updatedContact.getIsAccepted());
            statement.setInt(2, updatedContact.getUser().getId());
            statement.setInt(3, updatedContact.getFriend().getId());

            int affected = statement.executeUpdate();

            if(affected <= 0) {
                return false;
            }

            connection.commit();
            statement.close();
            connection.close();

            return true;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot update the contact", throwable);
        }
    }


    public UserDTO getUserDTO(int id) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "SELECT u.id, u.firstName, u.lastName, p.id AS profileId, image FROM users AS u " +
                "LEFT JOIN profiles p ON p.userId = u.id " +
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
                String image = "assets/"+resultSet.getString("image");
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


    public User getUser(int id) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "SELECT * FROM users WHERE id = ?";

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
                String image = "assets/" + resultSet.getString("image");
                int locationId = resultSet.getInt("locationId");
                int departmentId = resultSet.getInt("departmentId");
                String userNumber = resultSet.getString("userNumber");

                UserType type = UserType.valueOf(userType);

                statement.close();
                connection.close();

                return new User(id, firstName, lastName, type, email, password,  locationId, departmentId, userNumber, image);
            }

        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read user from the database.", throwable);
        }
    }


    public ContactDTO getContactDTO(int firstUserId, int secondUserId) throws URISyntaxException, DatabaseException {
        ContactDTO contact = null;
        Connection connection = jdbcRepository.getDatabaseConnection();

        String sql = "SELECT contacts.id, contacts.isAccepted, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.id AS userProfileId , " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.id AS friendProfileId " +
                "FROM contacts " +
                "LEFT JOIN users user ON contacts.userId = user.id " +
                "LEFT JOIN users friend ON contacts.friendId = friend.id " +
                "LEFT JOIN profiles AS p1 ON p1.userId = user.id " +
                "LEFT JOIN profiles AS p2 ON p2.userId = friend.id " +
                "WHERE (user.id = ? AND friend.id = ?) OR (user.id = ? AND friend.id = ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, firstUserId);
            statement.setInt(2, secondUserId);
            statement.setInt(3, secondUserId);
            statement.setInt(4, firstUserId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int contactId = resultSet.getInt("id");
                boolean isAccepted = resultSet.getBoolean("isAccepted");
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = "assets/" + resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                UserDTO user = new UserDTO(userId, profileId, firstName, lastName, image);

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = "assets/" + resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");

                UserDTO friend = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);

                // user who send request, other user, status of request 1 (connected), 0 (not connected)
                contact = new ContactDTO(contactId, user, friend, isAccepted);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
        return contact;
    }
}