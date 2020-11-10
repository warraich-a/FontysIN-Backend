package service.model;

public class About {
    private int id;
    private int profileId;
    private String content;
    private static int idSeeder = 1;

    public About(int profileId, String content) {
        this.id = idSeeder;
        idSeeder++;
        this.profileId = profileId;
        this.content = content;
    }
    public About(int id, int profileId, String content) {
        this.id = id;
        this.profileId = profileId;
        this.content = content;
    }


    public About() {
        this.id = idSeeder;
        idSeeder++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
