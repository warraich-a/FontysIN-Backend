package service.controller;

import service.model.Posts;
import service.repository.DatabaseException;
import service.repository.JDBCPosts;

import java.sql.SQLException;
import java.util.List;

public class PostController {
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

    public List<Posts> getPostsByDate(){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPostsByDate();

            System.out.println("ok");

            return posts;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Posts> getNewsfeed(int uId){
        JDBCPosts postsRepository = new JDBCPosts();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getNewsfeed(uId);

            for (Posts post: posts) {
                System.out.println(post.getId());
            }

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
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
