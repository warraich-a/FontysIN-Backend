package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

@XmlRootElement
public class Comments {
    private int id;
    private int userId;
    private int postId;
    private String content;
    private LocalDate date;

    public Comments(){

    }
    public Comments(int id, int userId, int postId, String content, LocalDate date){
        setId(id);
        setUserId(userId);
        setContent(content);
        setDate(date);
        setPostId(postId);

    }
    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }
    private void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }
    private void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }
    private void setDate(LocalDate date) {
        this.date = date;
    }



}
