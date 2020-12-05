package service.controller;

import service.model.Comments;
import service.repository.DatabaseException;
import service.repository.JDBCComments;

import java.util.List;

public class CommentController {
    public List<Comments> getCommets(){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getComments();



            return comments;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comments> getCommetsByPostId(int pId){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getCommentsByPostId(pId);



            return comments;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Comments getCommet(int Id){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            Comments comm = (Comments) commentsRepository.getComment(Id);



            return comm;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addComment(Comments comm){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            return commentsRepository.addComm(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateComment(Comments comm){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            return commentsRepository.updateComm(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(Comments comm){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            return commentsRepository.deleteComment(comm);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
