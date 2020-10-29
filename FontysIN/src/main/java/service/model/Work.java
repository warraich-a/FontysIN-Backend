package service.model;

public class Work {

    //fields
    private int id;
    private int profileId;
    private String fontys;
    private int startWorkYear;

    //constratctures

    public Work(int id, int profileId, String fontys, int startWorkYear) {
        this.id = id;
        this.profileId = profileId;
        this.fontys = fontys;
        this.startWorkYear = startWorkYear;
    }

    public Work(){

    }

    //getters and setters

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

    public String getFontys() {
        return fontys;
    }

    public void setFontys(String fontys) {
        this.fontys = fontys;
    }

    public int getStartWorkYear() {
        return startWorkYear;
    }

    public void setStartWorkYear(int startWorkYear) {
        this.startWorkYear = startWorkYear;
    }
}
