package service.repository;

import service.model.Posts;

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
                Date date = resultSet.getDate("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            statement.close();
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
                Date date = resultSet.getDate("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            statement.close();
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
                Date date = resultSet.getDate("date");
                Blob image = resultSet.getBlob("image");
                Posts post = new Posts(Id,userId,content,date);
                statement.close();
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
        String sql = "INSERT INTO posts(`userId`, `content`, `date`, `image`) VALUES (?,?,?,?)";
        try {
            if(!exist){
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, post.getUserId());
                statement.setString(2,post.getContent());
                statement.setDate(3, (Date) post.getDate());
                statement.setBlob(4,post.getImage());

                statement.executeUpdate();

                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1,1);
                connection.setAutoCommit(false);
                connection.commit();
                statement.close();
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
            statement.setDate(3, (Date) post.getDate());
            statement.setBlob(4, post.getImage());
            statement.setInt(5, post.getId());
            statement.executeUpdate();
            statement.close();
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

            statement.executeUpdate();
            statement.close();
            connection.close();


            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
