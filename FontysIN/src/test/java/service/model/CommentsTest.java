package service.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class CommentsTest {
    Comments comment;

    @Test
    public void getId() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        int id = comment.getId();

        assertEquals(1,id);

    }

    @Test
    public void setId() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        int id = comment.getId();

        assertEquals(1,id);
        comment.setId(2);
        id = comment.getId();
        assertEquals(2,id);
    }

    @Test
    public void getPostId() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        int postId = comment.getPostId();

        assertEquals(3,postId);
    }

    @Test
    public void setPostId() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        int postId = comment.getPostId();

        assertEquals(3,postId);
        comment.setPostId(2);
        postId = comment.getPostId();
        assertEquals(2,postId);
    }

    @Test
    public void getUserId() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        int userId = comment.getUserId();

        assertEquals(1,userId);
    }

    @Test
    public void setUserId() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        int userId = comment.getUserId();

        assertEquals(1,userId);
        comment.setUserId(2);
        userId = comment.getUserId();
        assertEquals(2,userId);
    }

    @Test
    public void getContent() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        String content = comment.getContent();

        assertEquals("TEST COMMENT",content);

    }

    @Test
    public void setContent() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        String content = comment.getContent();

        assertEquals("TEST COMMENT",content);
        comment.setContent("NEW TEST CONTENT");
        content = comment.getContent();
        assertEquals("NEW TEST CONTENT",content);
    }

    @Test
    public void getDate() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        Timestamp date = comment.getDate();

        assertEquals(new Timestamp(2001,3,1,10,59,10,10),date);
    }

    @Test
    public void setDate() {
        comment = new Comments(1,1,3,"TEST COMMENT",new Timestamp(2001,3,1,10,59,10,10));
        Timestamp date = comment.getDate();

        assertEquals(new Timestamp(2001,3,1,10,59,10,10),date);
        comment.setDate(new Timestamp(2022,6,5,1,59,10,10));
        date = comment.getDate();
        assertEquals(new Timestamp(2022,6,5,1,59,10,10),date);
    }
}