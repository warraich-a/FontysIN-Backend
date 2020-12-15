package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Blob;
import java.sql.Timestamp;

@XmlRootElement
public class Like {

    private int id;
    private int postId;
    private int likerId;




    public Like() {

    }

    public Like(int id, int postId, int likerId) {
        setId(id);
        setPostId(postId);
        setLikerId(likerId);

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPostId() {
        return postId;
    }

    public int getLikerId() {
        return likerId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setLikerId(int likerId) {
        this.likerId = likerId;
    }
}