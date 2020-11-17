package service.repository;

import service.model.Conversation;
import service.model.Message;
import service.model.dto.UserDTO;

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


    //     public UserConversationDTO(int id, int profileId, String firstName, String lastName, String image) {
    //     public Conversation(int id, List<Message> messages) {
    //     public Message(int id, int conversationId, UserDTO sender, UserDTO receiver, String content, LocalDateTime dataTime) {
    public List<Conversation> getConversations(int id) throws DatabaseException {
        // 1. Get all messages for a conversation
        // 2. Create a conversation
        // 3. Add conversation to conversations list
        // 4. Repeat
        //Conversation conversation = new Conversation(id, messages);

        Connection connection = super.getDatabaseConnection();

        String sql = "SELECT m.id AS messageId, m.conversationId, m.senderId, m.receiverId, m.content, m.dateو " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId, " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId " +
                "FROM conversations AS c " +
                "INNER JOIN messages AS m ON (m.conversationId = c.id) " +
                "LEFT JOIN users USER ON m.senderId = user.id " +
                "LEFT JOIN users friend ON m.receiverId = friend.id " +
                "LEFT JOIN " +
                "  (SELECT id AS userProfileId, userId " +
                "   FROM profiles " +
                "   GROUP BY userId) p1 ON p1.userId = user.id " +
                "LEFT JOIN " +
                "  (SELECT id AS friendProfileId, userId " +
                "   FROM profiles " +
                "   GROUP BY userId) p2 ON p2.userId = friend.id " +
                "WHERE (c.firstUserId = ?) OR (c.secondUserId = ?) " +
                "ORDER BY conversationId";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            List<Conversation> conversations = new ArrayList<>();
            List<Message> messages = new ArrayList<>();
            UserDTO currentUser = null;

            int lastConversationId = -1;
            while(resultSet.next()) {
                //  m.id AS messageId, m.conversationId, m.senderId, m.receiverId, m.content, m.dateو
                int messageId = resultSet.getInt("messageId");
                int conversationId = resultSet.getInt("conversationId");
                int senderId = resultSet.getInt("senderId");
                int receiverId = resultSet.getInt("receiverId");
                String content = resultSet.getString("content");
                Timestamp dateTime = resultSet.getTimestamp("date");

                // user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId,
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");

//                if(currentUser == null && id == senderId) {
//                    currentUser = new UserDTO(userId, )
//                }

               // UserConversationDTO -> container user's details (UserDTO) and list of conversations) NOT NEEDED
                // Conversation -> contains id and a list of messages
                // Message -> contains id, conversationId, UserDTO sender, UserDTO receiver, content, dateTime

                // New conversation?
                if(conversationId != lastConversationId) {
                    Conversation conversation = new Conversation(conversationId, messages);

                    conversations.add(conversation);

                    lastConversationId = conversationId;

                    messages = new ArrayList<>();
                }

                // Create message
                UserDTO sender = new UserDTO(userId, profileId, firstName, lastName, image);
                UserDTO receiver = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);

                Message message = new Message(messageId, conversationId, sender, receiver, content, dateTime);
                messages.add(message);
            }

            connection.close();

            return conversations;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
    }
}
