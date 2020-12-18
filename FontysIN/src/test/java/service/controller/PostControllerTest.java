package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Posts;
import service.repository.DatabaseException;
import service.repository.PostsRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostsRepository postsRepository;

    @Test
    public void getPosts() throws DatabaseException, URISyntaxException {
        List<Posts> expPosts = Arrays.asList(
                new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2","")
        );

        when(postsRepository.getPosts()).thenReturn(Arrays.asList(new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2",""))
        );

        List<Posts> actPosts = postController.getPosts();

        assertEquals(expPosts.size(), actPosts.size());


    }

    @Test
    public void getPostsByDate() throws DatabaseException, URISyntaxException {
        List<Posts> expPosts = Arrays.asList(
                new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2","")
        );

        when(postsRepository.getPostsByDate()).thenReturn(Arrays.asList(new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2",""))
        );

        List<Posts> actPosts = postController.getPostsByDate();

        assertEquals(expPosts.size(), actPosts.size());
    }

    @Test
    public void getNewsfeed() throws DatabaseException, URISyntaxException {
        List<Posts> expPosts = Arrays.asList(
                new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2","")
        );

        when(postsRepository.getNewsfeed(1)).thenReturn(Arrays.asList(new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2",""))
        );

        List<Posts> actPosts = postController.getNewsfeed(1);

        assertEquals(expPosts.size(), actPosts.size());
    }

    @Test
    public void getPostByUserId() throws DatabaseException, URISyntaxException {
        List<Posts> expPosts = Arrays.asList(
                new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2","")
        );

        when(postsRepository.getPostsByUserId(1)).thenReturn(Arrays.asList(new Posts(1,1,"Test1",""),
                new Posts(2,1,"Test2",""))
        );

        List<Posts> actPosts = postController.getPostByUserId(1);

        assertEquals(expPosts.size(), actPosts.size());
    }

    @Test
    public void getPost() throws DatabaseException, URISyntaxException {
        Posts expPosts = new Posts(1,1,"Test1","");

        when(postsRepository.getPost(1)).thenReturn(new Posts(1,1,"Test1",""));

        Posts actPosts = postController.getPost(1);

        assertEquals(expPosts.getId(), actPosts.getId());
    }

    @Test
    public void addPost() throws DatabaseException, URISyntaxException {
        Posts expPosts = new Posts(1,1,"Test1","");

        when(postsRepository.addPosts(expPosts)).thenReturn(true);

        boolean addedPostInfo = postController.addPost(expPosts);

        assertEquals(true, addedPostInfo);
    }

    @Test
    public void updatePost() throws DatabaseException, URISyntaxException {
        Posts expPosts = new Posts(1,1,"Test1","");

        when(postsRepository.updatePost(expPosts)).thenReturn(true);

        boolean updatedPostInfo = postController.updatePost(expPosts);

        assertEquals(true, updatedPostInfo);
    }

    @Test
    public void deletePost() throws URISyntaxException, DatabaseException, SQLException {
        Posts expPosts = new Posts(1,1,"Test1","");

        when(postsRepository.deletePost(expPosts)).thenReturn(true);

        boolean deletedPostInfo = postController.deletePost(expPosts);

        assertEquals(true, deletedPostInfo);
    }
}