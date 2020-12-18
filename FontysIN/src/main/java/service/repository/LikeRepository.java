package service.repository;

import service.model.Like;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeRepository extends JDBCRepository {

    JDBCRepository jdbcRepository;

    public LikeRepository() {
        this.jdbcRepository = new JDBCRepository();
    }

    public List < Like > getLikes() throws DatabaseException, URISyntaxException {
        List < Like > likes = new ArrayList < >();

        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM likes";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int postId = resultSet.getInt("postId");
                int userId = resultSet.getInt("likerId");

                Like like = new Like(Id, postId, userId);
                likes.add(like);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read likes from the database.", throwable);
        }
        return likes;
    }

    public List < Like > getLikesByPost(int id) throws DatabaseException, URISyntaxException {
        List < Like > likes = new ArrayList < >();
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM likes WHERE postId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int postId = resultSet.getInt("postId");
                int userId = resultSet.getInt("likerId");

                Like like = new Like(Id, postId, userId);
                likes.add(like);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read likes from the database.", throwable);
        }
        return likes;
    }

    public Like getPostLikeByUSer(int id, int userId) throws DatabaseException, URISyntaxException {
        Like like = null;
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "SELECT * FROM likes WHERE postId = ? AND likerId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int postId = resultSet.getInt("postId");
                int likerId = resultSet.getInt("likerId");

                like = new Like(Id, postId, likerId);

            }
            connection.setAutoCommit(false);
            connection.close();

        } catch(SQLException throwable) {
            throw new DatabaseException("Cannot read likes from the database.", throwable);
        }
        return like;
    }

    public boolean addLike(Like like) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();
        boolean exist = false;
        String sql = "INSERT INTO likes(`postId`, `LikerId`) VALUES (?,?)";
        try {
            if (!exist) {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, like.getPostId());
                statement.setInt(2, like.getLikerId());

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

    public boolean deleteLike(Like like) throws DatabaseException, URISyntaxException {
        Connection connection = jdbcRepository.getDatabaseConnection();
        String sql = "DELETE FROM likes WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, like.getId());

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
}