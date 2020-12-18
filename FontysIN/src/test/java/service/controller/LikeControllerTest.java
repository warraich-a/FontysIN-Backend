package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Like;
import service.repository.DatabaseException;
import service.repository.LikeRepository;


import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeControllerTest {

    @InjectMocks
    LikeController likeController;

    @Mock
    LikeRepository likeRepository;

    @Test
    public void getLikes() throws DatabaseException, URISyntaxException {
        List<Like> expLikes = Arrays.asList(new Like(1,1,1),new Like(2,2,1));

        when(likeRepository.getLikes()).thenReturn(Arrays.asList(new Like(1,1,1),new Like(2,2,1)));

        List<Like> actLike = likeController.getLikes();

        assertEquals(expLikes.size(),actLike.size());
    }

    @Test
    public void getLikesByPost() throws DatabaseException, URISyntaxException {
        List<Like> expLikes = Arrays.asList(new Like(1,1,1),new Like(2,1,1));

        when(likeRepository.getLikesByPost(1)).thenReturn(Arrays.asList(new Like(1,1,1),new Like(2,1,1)));

        List<Like> actLike = likeController.getLikesByPost(1);

        assertEquals(expLikes.size(),actLike.size());
    }

    @Test
    public void getPostLikesByUser() throws DatabaseException, URISyntaxException {
        Like expLikes =new Like(1,1,1);

        when(likeRepository.getPostLikeByUSer(1,1)).thenReturn(new Like(1,1,1));

        Like actLike = likeController.getPostLikesByUser(1,1);

        assertEquals(expLikes.getLikerId(),actLike.getLikerId());
    }

    @Test
    public void addLike() {
        Like newLikes =new Like(1,1,1);

        when(likeController.addLike(newLikes)).thenReturn(true);

        boolean addedLikesInfo = likeController.addLike(newLikes);

        assertEquals(true,addedLikesInfo);
    }
}