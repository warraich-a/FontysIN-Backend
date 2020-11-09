package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

@XmlRootElement
public class Posts {


    private int id;
    private int userId;
    private String content;
    private Date date;
    private Blob image;


    public Posts(){

    }

    public Posts(int id, int userId, String content, Date date, Blob image){
        setId(id);
        setUserId(userId);
        setContent(content);
        setDate(date);
        setImage(image);

    }
    public Posts(int id, int userId, String content, Date date){
        setId(id);
        setUserId(userId);
        setContent(content);
        setDate(date);

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public Blob getImage() {
        return image;
    }
    public void setImage(Blob image) {
        this.image = image;
    }











}
