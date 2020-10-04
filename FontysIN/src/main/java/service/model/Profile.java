package service.model;

public class Profile {

    private int id;
    private int userId;
    private String language;

    public Profile(int id, int userId, String language) {
        this.id = id;
        this.userId = userId;
        this.language = language;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
