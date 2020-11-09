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
    private Date date;
    private String username;

    public Comments(){

    }
    public Comments(int id, int userId, int postId, String content, Date date){
        setId(id);
        setUserId(userId);
        setContent(content);
        setDate(date);
        setPostId(postId);



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
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }



}
