package service.model;


import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class PostsTest {
    private Posts post;

    @Test
    public void getId() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        int id = post.getId();

        assertEquals(1,id);

    }

    @Test
    public void setId() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        int id = post.getId();

        assertEquals(1,id);
        post.setId(2);
        id = post.getId();
        assertEquals(2,id);
    }

    @Test
    public void getUserId() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        int userId = post.getUserId();

        assertEquals(1,userId);
    }

    @Test
    public void setUserId() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        int userId = post.getUserId();

        assertEquals(1,userId);
        post.setUserId(2);
        userId = post.getUserId();
        assertEquals(2,userId);
    }

    @Test
    public void getContent() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        String content = post.getContent();

        assertEquals("TEST CONTENT",content);
    }

    @Test
    public void setContent() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        String content = post.getContent();

        assertEquals("TEST CONTENT",content);
        post.setContent("NEW TEST CONTENT");
        content = post.getContent();
        assertEquals("NEW TEST CONTENT",content);
    }

    @Test
    public void getDate() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        Timestamp date = post.getDate();

        assertEquals(new Timestamp(2001,3,1,10,59,10,10),date);
    }

    @Test
    public void setDate() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        Timestamp date = post.getDate();

        assertEquals(new Timestamp(2001,3,1,10,59,10,10),date);
        post.setDate(new Timestamp(2022,6,5,1,59,10,10));
        date = post.getDate();
        assertEquals(new Timestamp(2022,6,5,1,59,10,10),date);
    }

    @Test
    public void getImage() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        String img = post.getImage();

        assertEquals("null",img);

    }

    @Test
    public void setImage() {
        post = new Posts(1,1,"TEST CONTENT", new Timestamp(2001,3,1,10,59,10,10),"null");
        String img = post.getImage();

        assertEquals("null",img);
        post.setImage("img");
        img = post.getImage();
        assertEquals("img",img);
    }
}