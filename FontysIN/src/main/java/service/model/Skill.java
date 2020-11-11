package service.model;

public class Skill {
    private int id;
    private int profileId;
    private String name;
    private static int idSeeder = 1;

    public Skill(int profileId, String name) {
        this.id = idSeeder;
        idSeeder++;
        this.profileId = profileId;
        this.name = name;
    }
    public Skill(int id, int profileId, String name) {
        this.id = id;
        this.profileId = profileId;
        this.name = name;
    }
    public Skill(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

