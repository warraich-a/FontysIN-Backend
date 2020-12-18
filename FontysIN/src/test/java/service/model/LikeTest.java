package service.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class LikeTest {

    Like like;

    @Test
    public void getId() {
        like = new Like(1,2,6);
        int id = like.getId();

        assertEquals(1,id);
    }

    @Test
    public void setId() {
        like = new Like(1,2,6);
        int id = like.getId();

        assertEquals(1,id);
        like.setId(2);
        id = like.getId();
        assertEquals(2,id);
    }

    @Test
    public void getPostId() {
        like = new Like(1,2,6);
        int postId = like.getPostId();

        assertEquals(2,postId);
    }

    @Test
    public void getLikerId() {
        like = new Like(1,2,6);
        int userId = like.getLikerId();

        assertEquals(6,userId);
    }

    @Test
    public void setPostId() {
        like = new Like(1,2,6);
        int postId = like.getPostId();

        assertEquals(2,postId);
        like.setPostId(3);
        postId = like.getPostId();
        assertEquals(3,postId);
    }

    @Test
    public void setLikerId() {
        like = new Like(1,2,6);
        int userId = like.getLikerId();

        assertEquals(6,userId);
        like.setLikerId(3);
        userId = like.getLikerId();
        assertEquals(3,userId);
    }
}