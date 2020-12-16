package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Comments;
import service.repository.CommentsRepository;
import service.repository.DatabaseException;


import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {
    @InjectMocks
    CommentController commentController;

    @Mock
    CommentsRepository commentsRepository;

    @Test
    public void getCommetsByPostIdTest() throws DatabaseException, URISyntaxException {
        List<Comments> expComments = Arrays.asList(new Comments(1,1,1,"test comm"));

        when(commentsRepository.getCommentsByPostId(1)).thenReturn(Arrays.asList(new Comments(1,1,1,"test comm")));

        List<Comments> actComments = commentController.getCommetsByPostId(1);

        assertEquals(expComments.size(),actComments.size());

    }
    @Test
    public void getCommetTest() throws DatabaseException, URISyntaxException {
        Comments expComment = new Comments(1,1,1,"test comm");

        when(commentsRepository.getComment(1)).thenReturn(new Comments(1,1,1,"test comm"));

        int coomId = commentController.getCommet(1).getId();

        assertEquals(1,coomId);
    }

    @Test
    public void addCommentTest() throws DatabaseException, URISyntaxException {
        Comments newComment = new Comments(1,1,1,"test comm");

        when(commentsRepository.addComm(newComment)).thenReturn(true);

        boolean addedCommentInfo = commentController.addComment(newComment);

        assertEquals(true,addedCommentInfo);
    }

    @Test
    public void updateCommentTest() throws DatabaseException, URISyntaxException {
        Comments newComment = new Comments(1,1,1,"test comm");

        when(commentsRepository.updateComm(newComment)).thenReturn(true);

        boolean updatedCommentInfo = commentController.updateComment(newComment);

        assertEquals(true,updatedCommentInfo);
    }

    @Test
    public void deleteCommentTest() throws DatabaseException, URISyntaxException {
        Comments newComment = new Comments(1,1,1,"test comm");

        when(commentsRepository.deleteComment(newComment)).thenReturn(true);

        boolean deletedCommentInfo = commentController.deleteComment(newComment);

        assertEquals(true,deletedCommentInfo);
    }
}