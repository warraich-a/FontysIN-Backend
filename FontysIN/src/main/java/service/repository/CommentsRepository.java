package service.repository;

import service.model.Comments;

import java.sql. * ;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommentsRepository extends JDBCRepository {
    public Collection < Comments > getComments() throws DatabaseException {
        List < Comments > comments = new ArrayList < >();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM comments";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int postId = resultSet.getInt("postId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");

                Comments comm = new Comments(Id, userId, postId, content, date);
                comments.add(comm);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read comments from the database.", throwable);
        }
        return comments;
    }

    public Collection < Comments > getCommentsByPostId(int pId) throws DatabaseException {
        List < Comments > comments = new ArrayList < >();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM comments WHERE postId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int postId = resultSet.getInt("postId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");

                Comments comm = new Comments(Id, userId, postId, content, date);
                comments.add(comm);
            }
            connection.setAutoCommit(false);
            connection.close();
        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read comments from the database.", throwable);
        }
        return comments;
    }

    public Comments getComment(int commId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM comments WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, commId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                connection.close();
                throw new DatabaseException("Comment with id " + commId + " cannot be found");
            } else {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int postId = resultSet.getInt("postId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");

                Comments comm = new Comments(Id, userId, postId, content, date);
                connection.close();
                return comm;
            }
        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read comments from the database.", throwable);
        }
    }

    public boolean addComm(Comments comm) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        boolean exist = false;
        String sql = "INSERT INTO comments(`userId`,`postId`, `content`) VALUES (?,?,?)";
        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, comm.getUserId());
            statement.setInt(2, comm.getPostId());
            statement.setString(3, comm.getContent());

            statement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1);
            connection.setAutoCommit(false);
            connection.commit();
            connection.close();
            return true;

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateComm(Comments comm) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `comments` SET `userId`=?,`postId`=?,`content`=?,`date`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, comm.getUserId());
            statement.setInt(2, comm.getPostId());
            statement.setString(3, comm.getContent());
            statement.setTimestamp(4, (Timestamp) comm.getDate());
            statement.setInt(5, comm.getId());
            statement.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();
            connection.close();

            return true;
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(Comments comm) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "DELETE FROM comments WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, comm.getId());

            statement.executeUpdate();

            connection.setAutoCommit(false);
            connection.commit();
            connection.close();

            return true;
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}