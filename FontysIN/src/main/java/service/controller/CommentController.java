package service.controller;

import service.model.Comments;
import service.repository.DatabaseException;
import service.repository.CommentsRepository;

import java.util.List;

public class CommentController {
    public List<Comments> getCommets(){
        CommentsRepository commentsRepository = new CommentsRepository();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getComments();



            return comments;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comments> getCommetsByPostId(int pId){
        CommentsRepository commentsRepository = new CommentsRepository();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getCommentsByPostId(pId);



            return comments;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Comments getCommet(int Id){
        CommentsRepository commentsRepository = new CommentsRepository();

        try {
            Comments comm = (Comments) commentsRepository.getComment(Id);



            return comm;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addComment(Comments comm){
        CommentsRepository commentsRepository = new CommentsRepository();

        try {
            return commentsRepository.addComm(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateComment(Comments comm){
        CommentsRepository commentsRepository = new CommentsRepository();

        try {
            return commentsRepository.updateComm(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(Comments comm){
        CommentsRepository commentsRepository = new CommentsRepository();

        try {
            return commentsRepository.deleteComment(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
