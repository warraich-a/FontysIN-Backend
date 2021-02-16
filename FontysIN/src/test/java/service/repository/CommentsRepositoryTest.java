package service.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Comments;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentsRepositoryTest {

    @InjectMocks
    CommentsRepository commentsRepository;

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
    void getComments() throws DatabaseException, URISyntaxException {
        List<Comments> comments = commentsRepository.getComments();

        assertEquals(2,comments.size());

    }

    @Test
    void getCommentsByPostId() throws DatabaseException, URISyntaxException {
        List<Comments> comments = commentsRepository.getCommentsByPostId(1);

        assertEquals(2,comments.size());
    }

    @Test
    void getComment() throws DatabaseException, URISyntaxException {
        Comments expComment = new Comments(1,2,1,"great comment");

        Comments comment = commentsRepository.getComment(1);

        assertEquals(expComment.getId(),comment.getId());
        assertEquals(expComment.getUserId(),comment.getUserId());
        assertEquals(expComment.getPostId(),comment.getPostId());
        assertEquals(expComment.getContent(),comment.getContent());
    }

    @Test
    void addComm() throws DatabaseException, URISyntaxException {
        Comments comment = new Comments(1,2,1,"new add commentas asd");

        Boolean addComment = commentsRepository.addComm(comment);

        assertEquals(true,addComment);
    }

    @Test
    void deleteComment() throws DatabaseException, URISyntaxException {
        Comments comment = new Comments(1,2,1,"new add comment");

        Boolean deleteComment = commentsRepository.deleteComment(comment);

        assertEquals(true,deleteComment);
    }
}