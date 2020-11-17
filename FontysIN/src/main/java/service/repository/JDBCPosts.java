package service.repository;

import service.PersistenceController;
import service.model.Contact;
import service.model.Posts;
import service.model.User;
import service.model.dto.ContactDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCPosts extends JDBCRepository {

    public Collection<Posts> getPosts() throws DatabaseException {
        List<Posts> posts = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM posts";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read students from the database.",throwable);
        }
        return posts;
    }

    public Collection<Posts> getNewsfeed(int id) throws DatabaseException {
        List<Posts> posts = new ArrayList<>();
        List<ContactDTO> friends = new ArrayList<>();
        PersistenceController persistenceController = new PersistenceController();
        String sql = "SELECT * FROM posts WHERE userId IN (";
        friends = persistenceController.getAllContactsDTO(id);
        for (ContactDTO f: friends) {
            sql += (Integer.toString(f.getFriend().getId()) + ", ");
        }
        sql += (Integer.toString(id)+") ORDER BY date DESC");
        Connection connection = this.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read students from the database.",throwable);
        }
        return posts;
    }

    public Collection<Posts> getPostsByUserId(int uId) throws DatabaseException {
        List<Posts> posts = new ArrayList<>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM posts WHERE userId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, uId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            connection.close();
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
        return posts;
    }

    public Posts getPost(int postId) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Posts with id " + postId + " cannot be found");
            } else {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                connection.close();
                return post;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }

    public boolean addPosts(Posts post) throws DatabaseException{
        Connection connection = this.getDatabaseConnection();
        boolean exist = false;
        String sql = "INSERT INTO posts(`userId`, `content`, `image`) VALUES (?,?,?)";
        try {
            if(!exist){
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, post.getUserId());
                statement.setString(2,post.getContent());
                statement.setBlob(3,post.getImage());

                statement.executeUpdate();

                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1,1);
                connection.setAutoCommit(false);
                connection.commit();
                connection.close();
                return true;
            } else  {
                connection.close();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updatePost(Posts post) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `posts` SET `userId`=?,`content`=?,`date`=?,`image`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getUserId());
            statement.setString(2, post.getContent());
            statement.setTimestamp(3, (Timestamp) post.getDate());
            statement.setBlob(4, post.getImage());
            statement.setInt(5, post.getId());
            statement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,1);
            statement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
            connection.close();


            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deletePost(Posts post) throws DatabaseException{
        Connection connection = this.getDatabaseConnection();
        String sql = "DELETE FROM posts WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,post.getId());

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,1);
            statement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
            connection.close();


            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
