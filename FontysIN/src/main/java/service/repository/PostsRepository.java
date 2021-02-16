package service.repository;


import service.controller.*;
import service.model.Posts;
import service.model.dto.ContactDTO;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostsRepository extends JDBCRepository {

    JDBCRepository jdbcRepository;

    public PostsRepository() {
        this.jdbcRepository = new JDBCRepository();
    }

    public List < Posts > getPosts() throws DatabaseException, URISyntaxException {
        List < Posts > posts = new ArrayList < >();

        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM posts";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                String image = resultSet.getString("image");
                Posts post = new Posts(Id, userId, content, date, image);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read students from the database.", throwable);
        }
        return posts;
    }

    public List  < Posts > getPostsByDate() throws DatabaseException, URISyntaxException {
        List < Posts > posts = new ArrayList < >();

        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM posts ORDER BY date DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                String image = resultSet.getString("image");
                Posts post = new Posts(Id, userId, content, date, image);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read posts from the database.", throwable);
        }
        return posts;
    }

    public List < Posts > getNewsfeed(int id) throws DatabaseException {
        List < Posts > posts = new ArrayList < >();

        List < Posts > allPosts = new ArrayList < >();
        List < ContactDTO > friends = new ArrayList < >();
        PostController persistenceController = new PostController();
        ContactController contactController = new ContactController();
        List < Integer > friendsId = new ArrayList < >();
        friends = contactController.getAcceptedContactsDTO(id);
        allPosts = persistenceController.getPostsByDate();
        for (ContactDTO f: friends) {
            friendsId.add(f.getFriend().getId());
        }
        if (!friendsId.contains(id)) {
            friendsId.add(id);
        }

        for (Posts post: allPosts) {
            for (Integer i: friendsId) {
                if (post.getUserId() == i) {
                    posts.add(post);
                }
            }
        }

        return posts;
    }

    public List < Posts > getPostsByUserId(int uId) throws DatabaseException, URISyntaxException {
        List < Posts > posts = new ArrayList < >();
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM posts WHERE userId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, uId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                String image = resultSet.getString("image");
                Posts post = new Posts(Id, userId, content, date, image);
                posts.add(post);
            }
            connection.setAutoCommit(false);
            connection.close();
        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.", throwable);
        }
        return posts;
    }

    public Posts getPost(int postId) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                connection.close();
                throw new DatabaseException("Posts with id " + postId + " cannot be found");
            } else {
                int Id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String content = resultSet.getString("content");
                Timestamp date = resultSet.getTimestamp("date");
                String image = resultSet.getString("image");
                Posts post = new Posts(Id, userId, content, date, image);
                connection.close();
                return post;
            }
        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.", throwable);
        }
    }

    public boolean addPosts(Posts post) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();
        boolean exist = false;
        String sql = "INSERT INTO posts(`userId`, `content`, `image`) VALUES (?,?,?)";
        try {
            if (!exist) {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, post.getUserId());
                statement.setString(2, post.getContent());
                statement.setString(3, post.getImage());

                statement.executeUpdate();

                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, 1);
                connection.setAutoCommit(false);
                connection.commit();
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updatePost(Posts post) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "UPDATE `posts` SET `userId`=?,`content`=?,`image`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getUserId());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getImage());
            statement.setInt(4, post.getId());
            statement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1);
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

    public boolean deletePost(Posts post) throws DatabaseException,
            SQLException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "DELETE FROM likes WHERE postId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getId());

            statement.executeUpdate();

            connection.commit();

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "DELETE FROM comments WHERE postId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getId());

            statement.executeUpdate();

            connection.commit();

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "DELETE FROM posts WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getId());

            statement.executeUpdate();

            connection.commit();

            connection.close();
            return true;
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
        return false;
    }
}
