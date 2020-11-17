package service.repository;

import service.model.Conversation;
import service.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MessagesRepository extends JDBCRepository {
    /***
     *
     * @param message
     * @return created message id
     * @throws DatabaseException
     */
    public int createMessage(Message message) throws  DatabaseException  {
        // 1. Get conversation id
        // 2. Add message to db
        Connection connection = super.getDatabaseConnection();

        String sql = "INSERT INTO messages (conversationId, senderId, receiverId, content, date) VALUES (?, ?, ?, ?, ?)";

        try {
            int messageId = -1;

            Date date = new Date();

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, message.getConversationId());
            statement.setInt(2, message.getSender().getId());
            statement.setInt(3, message.getReceiver().getId());
            statement.setString(4, message.getContent());
            statement.setTimestamp(5, new Timestamp(date.getTime()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet != null && resultSet.next()) {
                messageId = resultSet.getInt(1);
            }

            connection.commit();
            connection.close();

            return messageId;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot create the message");
        }
    }



    public List<Conversation> getConversations(int id) throws DatabaseException {
        List<Conversation> conversations = new ArrayList<>();

        Connection connection = super.getDatabaseConnection();

        //     public Message(int id, int conversationId, UserDTO sender, UserDTO receiver, String content, LocalDateTime dataTime) {
        //     public Conversation(int id, List<Message> messages) { // db : id, firstUserId, isDeletedFirstUser, secondUserId, isDeletedSecondUser
        //     public UserConversationDTO(int id, int profileId, String firstName, String lastName, String image) {
        String sql = "SELECT u.id AS userId, u.firstName, u.lastName, u.image, " +
                            "m.id AS messageId, m.conversationId, m.senderId, m.receiverId, m.content, m.date FROM users AS u " +
                            "INNER JOIN conversations AS c ON (u.id = c.firstUserId) OR (u.id = secondUserId) " +
                            "INNER JOIN messages AS m ON (m.conversationId = c.id) " +
                            "WHERE u.id = ?";

        // 1. Get all messages for a conversation
        // 2. Create a conversation
        // 3. Add conversation to conversations list
        // 4. Repeat
        //Conversation conversation = new Conversation(id, messages);

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
//            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String image = resultSet.getString("image");

                int messageId = resultSet.getInt("messageId");
                int conversationId = resultSet.getInt("conversationId");
                int senderId = resultSet.getInt("messageId");
                int receiverId = resultSet.getInt("receiverId");
                String content = resultSet.getString("content");
                Timestamp dateTime = resultSet.getTimestamp("date");

//                User user = new User(id, firstName, lastName, type, email, password, phoneNr, addressId, locationId, departmentId, userNumber);


//                Contact contact = new Contact(contactId, user, friend);

//                contacts.add(contact);
            }

            connection.close();
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
        return conversations;
    }
}
