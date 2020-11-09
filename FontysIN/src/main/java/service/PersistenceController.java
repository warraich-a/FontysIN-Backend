package service;

import service.model.Comments;
import service.model.Posts;
import service.repository.DatabaseException;
import service.repository.JDBCComments;
import service.repository.JDBCPosts;

import java.util.List;

public class PersistenceController {

    // Post section

    public List<Posts> getPosts(){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPosts();

                System.out.println("ok");

            return posts;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Posts> getPostByUserId(int uId){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPostsByUserId(uId);

            System.out.println("ok");

            return posts;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Posts getPost(int Id){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            Posts post = (Posts) postsRepository.getPost(Id);

            System.out.println("ok");

            return post;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addPost(Posts post){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            return postsRepository.addPosts(post);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePost(Posts post){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            return postsRepository.updatePost(post);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePost(Posts post){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            return postsRepository.deletePost(post);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    // End of Post section
    // Comment section

    public List<Comments> getCommets(){
        JDBCComments commentsRepository = new JDBCComments();

        try {
            List<Comments> comments = (List<Comments>) commentsRepository.getComments();

            System.out.println("ok");

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

            System.out.println("ok");

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

            System.out.println("ok");

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

    //End of Comment section


}
