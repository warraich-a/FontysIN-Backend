package service.repository;

import service.model.Conversation;
import service.model.Message;
import service.model.dto.ConversationDTO;
import service.model.dto.UserDTO;

import java.net.URISyntaxException;
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
    public int createMessage(Message message) throws DatabaseException, URISyntaxException {
        // 1. Get conversation id
        // 2. Add message to db
        Connection connection = super.getDatabaseConnection();

        System.out.println("message from front end");

        System.out.println(message);

        String sql = "INSERT INTO messages (conversationId, senderId, receiverId, content, date) VALUES (?, ?, ?, ?, ?)";

        try {
            System.out.println("TRY");
            int messageId = -1;

            Date date = new Date();

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("1 " + message.getConversationId());
            System.out.println("2 " + message.getSender().getId());
            System.out.println("3 " + message.getReceiver().getId());
            System.out.println("4 " + message.getContent());
            System.out.println("5 " + new Timestamp(date.getTime()));

            statement.setInt(1, message.getConversationId());
            statement.setInt(2, message.getSender().getId());
            statement.setInt(3, message.getReceiver().getId());
            statement.setString(4, message.getContent());
            statement.setTimestamp(5, new Timestamp(date.getTime()));

            System.out.println(statement);
            statement.executeUpdate();

            System.out.println("After Execute");

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet != null && resultSet.next()) {
                messageId = resultSet.getInt(1);
                System.out.println("Message id " + messageId);
            }

            System.out.println("Done");

            connection.commit();
            connection.close();

            return messageId;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot create the message");
        }
    }


    /***
     *
     * @param id conversationId
     * @return a conversation with a specific id
     * @throws DatabaseException
     */
    public Conversation getConversation(int id) throws DatabaseException, URISyntaxException {
        Connection connection = super.getDatabaseConnection();

//        String sql = "SELECT c.id AS conversationId, c.firstUserId, c.secondUserId, " +
//                "m.id AS messageId, m.conversationId AS mConversationId, m.senderId, m.receiverId, m.content, m.date, " +
//                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId, " +
//                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId " +
//                "FROM conversations AS c " +
//                "LEFT JOIN messages AS m ON (m.conversationId = c.id)" +
//                "LEFT JOIN users user ON m.senderId = user.id OR c.firstUserId = user.id " +
//                "LEFT JOIN users friend ON m.receiverId = friend.id OR c.secondUserId = friend.id " +
//                "LEFT JOIN " +
//                " (SELECT id AS userProfileId, userId " +
//                " FROM profiles " +
//                " GROUP BY userId) p1 ON p1.userId = user.id " +
//                "LEFT JOIN " +
//                " (SELECT id AS friendProfileId, userId " +
//                " FROM profiles " +
//                " GROUP BY userId) p2 ON p2.userId = friend.id " +
//                "WHERE c.id = ? " +
//                "GROUP BY m.id";

        String sql = "SELECT c.id AS conversationId, c.firstUserId, c.secondUserId, " +
                "m.id AS messageId, m.conversationId AS mConversationId, m.senderId, m.receiverId, m.content, m.date, " +
                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId, " +
                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId " +
                "FROM conversations AS c " +
                "LEFT JOIN messages AS m ON (m.conversationId = c.id) " +
                "LEFT JOIN users user ON ( " +
                "CASE " +
                "WHEN m.senderId IS NOT NULL THEN m.senderId = user.id " +
                "ELSE c.firstUserId = user.id " +
                "END) " +
                "LEFT JOIN users friend ON ( " +
                "CASE " +
                "WHEN m.senderId IS NOT NULL THEN  m.receiverId = friend.id " +
                "ELSE c.secondUserId = friend.id " +
                "END) " +
                "LEFT JOIN " +
                "(SELECT id AS userProfileId, userId " +
                "FROM profiles " +
                "GROUP BY userId) p1 ON p1.userId = user.id " +
                "LEFT JOIN " +
                "(SELECT id AS friendProfileId, userId " +
                "FROM profiles " +
                "GROUP BY userId) p2 ON p2.userId = friend.id " +
                "WHERE c.id = ? " +
                "GROUP BY m.id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            boolean first = true;

            Conversation conversation = null;

            while(resultSet.next()) {
                // Message
                int messageId = resultSet.getInt("messageId");
                int conversationId = resultSet.getInt("conversationId");
                String content = resultSet.getString("content");
                Timestamp dateTime = resultSet.getTimestamp("date");

                // Sender
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = "assets/" + resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                // Receiver
                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = "assets/" +  resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");

                // Create message
                UserDTO sender = new UserDTO(userId, profileId, firstName, lastName, image);
                UserDTO receiver = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);

                if(first) {
                    conversation = new Conversation(id, sender, receiver);
                    first = false;
                }

                Message message = new Message(messageId, conversationId, sender, receiver, content, dateTime);
                if(message.getContent() != null) {
                    conversation.addMessage(message);
                }
            }

            connection.close();

            return conversation;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
    }


    /***
     *
     * @param id userId
     * @return a list of conversations of a specific user
     * @throws DatabaseException
     */
    public List<Conversation> getConversations(int id) throws DatabaseException, URISyntaxException {
        // 1. Get all messages for a conversation
        // 2. Create a conversation
        // 3. Add conversation to conversations list
        // 4. Repeat

        Connection connection = super.getDatabaseConnection();

//        String sql = "SELECT c.id AS conversationId, c.firstUserId, c.secondUserId, " +
//                "m.id AS messageId, m.conversationId AS mConversationId, m.senderId, m.receiverId, m.content, m.date, " +
//                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId, " +
//                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId " +
//                "FROM conversations AS c " +
//                " LEFT JOIN messages AS m ON (m.conversationId = c.id) " +
//                " LEFT JOIN users user ON m.senderId = user.id " +
//                " LEFT JOIN users friend ON m.receiverId = friend.id " +
//                " LEFT JOIN " +
//                " (SELECT id AS userProfileId, userId " +
//                " FROM profiles " +
//                " GROUP BY userId) p1 ON p1.userId = user.id " +
//                " LEFT JOIN " +
//                " (SELECT id AS friendProfileId, userId " +
//                " FROM profiles " +
//                " GROUP BY userId) p2 ON p2.userId = friend.id " +
//                "WHERE (c.firstUserId = ? AND c.isDeletedFirstUser = 0) " +
//                " OR (c.secondUserId = ? AND c.isDeletedSecondUser = 0) " +
//                "ORDER BY conversationId";

//        String sql = "SELECT c.id AS conversationId, c.firstUserId, c.secondUserId, " +
//                "m.id AS messageId, m.conversationId AS mConversationId, m.senderId, m.receiverId, m.content, m.date, " +
//                "user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId, " +
//                "friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId " +
//                "FROM conversations AS c " +
//                " LEFT JOIN messages AS m ON (m.conversationId = c.id) " +
//                " LEFT JOIN users user ON m.senderId = user.id OR c.firstUserId = user.id " +
//                " LEFT JOIN users friend ON m.receiverId = friend.id OR c.secondUserId = friend.id " +
//                " LEFT JOIN " +
//                " (SELECT id AS userProfileId, userId " +
//                " FROM profiles " +
//                " GROUP BY userId) p1 ON p1.userId = user.id " +
//                " LEFT JOIN " +
//                " (SELECT id AS friendProfileId, userId " +
//                " FROM profiles " +
//                " GROUP BY userId) p2 ON p2.userId = friend.id " +
//                "WHERE (c.firstUserId = ? AND c.isDeletedFirstUser = 0) " +
//                " OR (c.secondUserId = ? AND c.isDeletedSecondUser = 0) " +
//                "ORDER BY conversationId";


        String sql = "SELECT c.id AS conversationId, c.firstUserId, c.secondUserId,\n" +
                "       user.id AS userId, user.firstName AS userFirstName, user.lastName AS userLastName, user.image AS userImage, p1.userProfileId,\n" +
                "       friend.id AS friendId, friend.firstName AS friendFirstName, friend.lastName AS friendLastName, friend.image AS friendImage, p2.friendProfileId\n" +
                "FROM conversations AS c\n" +
                "         LEFT JOIN users user ON c.firstUserId = user.id\n" +
                "         LEFT JOIN users friend ON c.secondUserId = friend.id\n" +
                "         LEFT JOIN\n" +
                "     (SELECT id AS userProfileId, userId\n" +
                "      FROM profiles\n" +
                "      GROUP BY userId) p1 ON p1.userId = user.id\n" +
                "         LEFT JOIN\n" +
                "     (SELECT id AS friendProfileId, userId\n" +
                "      FROM profiles\n" +
                "      GROUP BY userId) p2 ON p2.userId = friend.id\n" +
                "WHERE (c.firstUserId = ? AND c.isDeletedFirstUser = 0)\n" +
                "   OR (c.secondUserId = ? AND c.isDeletedSecondUser = 0)\n" +
                "GROUP BY conversationId";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            List<Conversation> conversations = new ArrayList<>();
            Conversation conversation = null;

            int lastConversationId = -1;
            while(resultSet.next()) {
                // Message
//                int messageId = resultSet.getInt("messageId");
                int conversationId = resultSet.getInt("conversationId");

//                int senderId = resultSet.getInt("senderId");
//                int receiverId = resultSet.getInt("receiverId");
//                String content = resultSet.getString("content");
//                Timestamp dateTime = resultSet.getTimestamp("date");

                // Sender
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("userFirstName");
                String lastName = resultSet.getString("userLastName");
                String image = "assets/" + resultSet.getString("userImage");
                int profileId = resultSet.getInt("userProfileId");

                // Receiver
                int friendId = resultSet.getInt("friendId");
                String friendFirstName = resultSet.getString("friendFirstName");
                String friendLastName = resultSet.getString("friendLastName");
                String friendImage = "assets/" + resultSet.getString("friendImage");
                int friendProfileId = resultSet.getInt("friendProfileId");

                UserDTO sender = new UserDTO(userId, profileId, firstName, lastName, image);
                UserDTO receiver = new UserDTO(friendId, friendProfileId, friendFirstName, friendLastName, friendImage);
//
//                System.out.println("Sender " + sender);
//                System.out.println("Receiver " + receiver);
                // New conversation?
                if(conversationId != lastConversationId) {

                    conversation = new Conversation(conversationId, sender, receiver);

                    conversations.add(conversation);

                    lastConversationId = conversationId;
                }

                // Create message

//                Message message = new Message(messageId, conversationId, sender, receiver, content, dateTime);
//                if(message.getContent() != null) {
//                    conversation.addMessage(message);
//
////                    System.out.println("Message " + message);
//                }
            }

            System.out.println("Conversations");
            System.out.println(conversations);

            connection.close();

            return conversations;
        }
        catch (SQLException throwable) {
            throw new DatabaseException("Cannot read contacts from the database.", throwable);
        }
    }

    // Delete conversation in messaging page
    public boolean deleteConversation(int userId, int conversationId) throws DatabaseException, URISyntaxException {

        Connection connection = this.getDatabaseConnection();

        String sql = "UPDATE conversations SET " +
                "isDeletedFirstUser = (CASE WHEN firstUserId = ? THEN 1 ELSE isDeletedFirstUser end)," +
                "isDeletedSecondUser = (CASE WHEN secondUserId = ? THEN 1 ELSE isDeletedSecondUser end) where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, conversationId);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // start new conversation with new user
    public int startConversation(ConversationDTO conversation) throws DatabaseException, URISyntaxException {

        Connection connection = this.getDatabaseConnection();

        String sql = "INSERT INTO conversations (firstUserId, secondUserId) SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT secondUserId FROM conversations WHERE secondUserId = ? AND firstUserId = ?) LIMIT 1";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, conversation.getFirstUserId());
            preparedStatement.setInt(2, conversation.getSecondUserId());
            preparedStatement.setInt(3, conversation.getSecondUserId());
            preparedStatement.setInt(4, conversation.getFirstUserId());

            System.out.println("before prepared startemnet");

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            int conversationId = -1;
            if(resultSet != null && resultSet.next()) {
                conversationId = resultSet.getInt(1);
            }

            connection.commit();
            connection.close();

            System.out.println("after prepared startemnet");

            return conversationId;

        } catch (SQLException throwable) {
            throw  new DatabaseException("Cannot create new conversation.", throwable);
        }
    }

    // start new conversation with new user
    public boolean restartNewConversation(ConversationDTO conversation) throws DatabaseException, URISyntaxException {

        Connection connection = this.getDatabaseConnection();

        String sql = "UPDATE conversations SET isDeletedFirstUser = 0 WHERE firstUserId = ? and secondUserId = ? LIMIT 1";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, conversation.getFirstUserId());
            preparedStatement.setInt(2, conversation.getSecondUserId());

            System.out.println("before prepared statemnet");

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

            System.out.println("after prepared statemnet");

            return true;


        } catch (SQLException throwable) {
            throw  new DatabaseException("Cannot recreate new conversation.", throwable);
        }
    }
}
