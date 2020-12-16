package service.controller;

import service.model.Like;
import service.repository.DatabaseException;
import service.repository.LikeRepository;

import java.net.URISyntaxException;
import java.util.List;

public class LikeController {
    LikeRepository likeRepository = new LikeRepository();
    public List<Like> getLikes(){


        try {
            List<Like> likes = likeRepository.getLikes();

            return likes;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Like> getLikesByPost(int id){


        try {
            List<Like> likes =  likeRepository.getLikesByPost(id);

            return likes;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Like getPostLikesByUser(int id,int userId){


        try {
            Like like =  likeRepository.getPostLikeByUSer(id,userId);

            return like;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLike (Like like){


        try {
            return likeRepository.addLike(like);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }


}
