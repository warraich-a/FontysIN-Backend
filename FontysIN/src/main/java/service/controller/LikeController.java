package service.controller;

import service.model.Like;
import service.repository.DatabaseException;
import service.repository.JDBCLikeRepository;

import java.util.List;

public class LikeController {
    public List<Like> getLikes(){
        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            List<Like> likes = (List<Like>) likeRepository.getLikes();

            return likes;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Like> getLikesByPost(int id){
        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            List<Like> likes = (List<Like>) likeRepository.getLikesByPost(id);

            return likes;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Like getPostLikesByUser(int id,int userId){
        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            Like like = (Like) likeRepository.getPostLikeByUSer(id,userId);

            return like;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLike (Like like){
        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            return likeRepository.addLike(like);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLike(Like like){
        JDBCLikeRepository likeRepository = new JDBCLikeRepository();

        try {
            return likeRepository.deleteLike(like);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
