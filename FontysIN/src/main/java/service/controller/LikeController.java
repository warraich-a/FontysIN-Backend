package service.controller;

import service.model.Like;
import service.repository.DatabaseException;
import service.repository.LikeRepository;

import java.net.URISyntaxException;
import java.util.List;

public class LikeController {
    public List<Like> getLikes(){
        LikeRepository likeRepository = new LikeRepository();

        try {
            List<Like> likes = (List<Like>) likeRepository.getLikes();

            return likes;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Like> getLikesByPost(int id){
        LikeRepository likeRepository = new LikeRepository();

        try {
            List<Like> likes = (List<Like>) likeRepository.getLikesByPost(id);

            return likes;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Like getPostLikesByUser(int id,int userId){
        LikeRepository likeRepository = new LikeRepository();

        try {
            Like like = (Like) likeRepository.getPostLikeByUSer(id,userId);

            return like;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLike (Like like){
        LikeRepository likeRepository = new LikeRepository();

        try {
            return likeRepository.addLike(like);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLike(Like like){
        LikeRepository likeRepository = new LikeRepository();

        try {
            return likeRepository.deleteLike(like);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
