package service.controller;

import service.model.Comments;
import service.repository.DatabaseException;
import service.repository.CommentsRepository;

import java.net.URISyntaxException;
import java.util.List;

public class CommentController {
    CommentsRepository commentsRepository = new CommentsRepository();
    public List<Comments> getCommets(){


        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getComments();



            return comments;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comments> getCommetsByPostId(int pId){
        List<Comments> comments;

        try {
            comments=  commentsRepository.getCommentsByPostId(pId);

            return comments;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Comments getCommet(int Id){


        try {
            Comments comm = commentsRepository.getComment(Id);



            return comm;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addComment(Comments comm){


        try {
            return commentsRepository.addComm(comm);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateComment(Comments comm){


        try {
            return commentsRepository.updateComm(comm);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(Comments comm){


        try {
            return commentsRepository.deleteComment(comm);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
