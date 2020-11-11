package service.model;


public class Work {
    // fields
    private int id;
    private int profileId;
    private String company;
    private int startYearWork;
    private static int idSeeder = 1;


    // constracture
    public Work(int id, int profileId, String company, int startYear) {
        this.id = idSeeder;
        idSeeder++;
        this.profileId = profileId;
        this.company = company;
        this.startYearWork = startYear;
    }

    public Work() {

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

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public int getStartYearWork() {
        return startYearWork;
    }
    public void setStartYearWork(int startYearWork) {
        this.startYearWork = startYearWork;
    }
}
