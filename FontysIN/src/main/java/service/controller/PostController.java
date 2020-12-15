package service.controller;

import service.model.Posts;
import service.repository.DatabaseException;
import service.repository.PostsRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class PostController {
    public List<Posts> getPosts(){
        PostsRepository postsRepository = new PostsRepository();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPosts();

            System.out.println("ok");

            return posts;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Posts> getPostsByDate(){
        PostsRepository postsRepository = new PostsRepository();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPostsByDate();

            System.out.println("ok");

            return posts;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Posts> getNewsfeed(int uId){
        PostsRepository postsRepository = new PostsRepository();

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
        PostsRepository postsRepository = new PostsRepository();

        try {
            List<Posts> posts = (List<Posts>) postsRepository.getPostsByUserId(uId);

            System.out.println("ok");

            return posts;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Posts getPost(int Id){
        PostsRepository postsRepository = new PostsRepository();

        try {
            Posts post = (Posts) postsRepository.getPost(Id);

            System.out.println("ok");

            return post;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addPost(Posts post){
        PostsRepository postsRepository = new PostsRepository();

        try {
            return postsRepository.addPosts(post);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePost(Posts post){
        PostsRepository postsRepository = new PostsRepository();

        try {
            return postsRepository.updatePost(post);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePost(Posts post){
        PostsRepository postsRepository = new PostsRepository();

        try {
            return postsRepository.deletePost(post);
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

}
