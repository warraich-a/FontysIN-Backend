package service.controller;

import service.model.Posts;
import service.repository.DatabaseException;
import service.repository.PostsRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class PostController {
    PostsRepository postsRepository = new PostsRepository();
    public List<Posts> getPosts(){


        try {
            List<Posts> posts =  postsRepository.getPosts();


            return posts;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<Posts> getPostsByDate(){


        try {
            List<Posts> posts = postsRepository.getPostsByDate();

            System.out.println("ok");

            return posts;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Posts> getNewsfeed(int uId){


        try {
            List<Posts> posts =  postsRepository.getNewsfeed(uId);

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


        try {
            List<Posts> posts =  postsRepository.getPostsByUserId(uId);

            System.out.println("ok");

            return posts;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Posts getPost(int Id){


        try {
            Posts post = postsRepository.getPost(Id);

            System.out.println("ok");

            return post;
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addPost(Posts post){


        try {
            return postsRepository.addPosts(post);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePost(Posts post){


        try {
            return postsRepository.updatePost(post);
        } catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePost(Posts post){


        try {
            return postsRepository.deletePost(post);
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

}
