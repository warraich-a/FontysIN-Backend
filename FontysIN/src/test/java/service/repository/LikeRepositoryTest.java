package service.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Like;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LikeRepositoryTest {

    @InjectMocks
    LikeRepository likeRepository;

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
    void getLikes() throws DatabaseException, URISyntaxException {
        List<Like> likes = likeRepository.getLikes();

        assertEquals(3,likes.size());
    }

    @Test
    void getLikesByPost() throws DatabaseException, URISyntaxException {
        List<Like> likes = likeRepository.getLikesByPost(1);

        assertEquals(2,likes.size());
    }

    @Test
    void getPostLikeByUSer() throws DatabaseException, URISyntaxException {
        Like like = likeRepository.getPostLikeByUSer(1,1);

        assertEquals(2,like.getId());
    }

    @Test
    void addLike() throws DatabaseException, URISyntaxException {
        Like like = new Like(1,4,1);

        Boolean addLike = likeRepository.addLike(like);

        assertEquals(true,addLike);
    }
}