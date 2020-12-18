package service.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Posts;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PostsRepositoryTest {

    @InjectMocks
    PostsRepository postsRepository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName ("org.h2.Driver");

        when(jdbcRepository.getDatabaseConnection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test")
        );

        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
    }



    @Test
    void getPosts() throws DatabaseException, URISyntaxException {
        List<Posts> posts = postsRepository.getPosts();
        assertEquals(4,posts.size());
    }

    @Test
    void getPostsByDate() throws DatabaseException, URISyntaxException {
        List<Posts> posts = postsRepository.getPostsByDate();

        assertEquals(4,posts.size());

    }

    @Test
    void getPostsByUserId() throws DatabaseException, URISyntaxException {
        List<Posts> posts = postsRepository.getPostsByUserId(1);

        List<Posts> expPosts = Arrays.asList(new Posts(1, 1, "test post", ""));
        assertEquals(expPosts.get(0).getId(),posts.get(0).getUserId());
    }

    @Test
    void getPost() throws DatabaseException, URISyntaxException {
        Posts post = new Posts(1, 1, "test post", "");

        Posts expPosts = postsRepository.getPost(1);
        assertEquals(expPosts.getId(),post.getId());
        assertEquals(expPosts.getContent(),post.getContent());
        assertEquals(expPosts.getUserId(),post.getUserId());
        assertEquals(expPosts.getImage(),post.getImage());

    }

    @Test
    void addPosts() throws DatabaseException, URISyntaxException {
        Posts post = new Posts(1, 1, "test add post",new Timestamp(2020,10,1,6,34,1,1), "");

        Boolean addPost = postsRepository.addPosts(post);

        assertEquals(addPost,true);

    }

    @Test
    void updatePost() throws DatabaseException, URISyntaxException {
        Posts post = new Posts(1, 1, "test update post",new Timestamp(2020,10,1,6,34,1,1), "");

        Boolean updatePost = postsRepository.updatePost(post);

        assertEquals(updatePost,true);

    }

    @Test
    void deletePost() throws URISyntaxException, DatabaseException, SQLException {
        Posts post = new Posts(1, 1, "test update post",new Timestamp(2020,10,1,6,34,1,1), "");

        Boolean deletePost = postsRepository.deletePost(post);

        assertEquals(deletePost,true);

    }
}