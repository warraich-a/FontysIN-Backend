package service.model;

import java.time.LocalDate;


public class Experience {

    private int id;
    private int profileId;
    private String title;
    private String company;
    private EmplymentType employmentType;
    private String location;
    private int startDateExperience;
    private int endDateExperience;
    private String descriptionExperience;
    private static int idSeeder = 1;

    public Experience(int profileId, String title, String company, EmplymentType employmentType, String location, int startDate, int endDate, String description) {
//        this.id = idSeeder;
//        idSeeder++;

        this.profileId = profileId;
        this.title = title;
        this.company = company;
        this.employmentType = employmentType;
        this.location = location;
        this.startDateExperience = startDate;
        this.endDateExperience = endDate;
        this.descriptionExperience = description;
    }
    public Experience(int id, int profileId, String title, String company, EmplymentType employmentType, String location, int startDate, int endDate, String description) {
        this.id = id;
        this.profileId = profileId;
        this.title = title;
        this.company = company;
        this.employmentType = employmentType;
        this.location = location;
        this.startDateExperience = startDate;
        this.endDateExperience = endDate;
        this.descriptionExperience = description;
    }


    public Experience() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public EmplymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmplymentType employmentType) {
        this.employmentType = employmentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStartDateExperience() {
        return startDateExperience;
    }

    public void setStartDateExperience(int startDateExperience) {
        this.startDateExperience = startDateExperience;
    }

    public int getEndDateExperience() {
        return endDateExperience;
    }

    public void setEndDateExperience(int endDateExperience) {
        this.endDateExperience = endDateExperience;
    }

    public String getDescriptionExperience() {
        return descriptionExperience;
    }

    public void setDescriptionExperience(String descriptionExperience) {
        this.descriptionExperience = descriptionExperience;
    }

//    @Override
//    public String toString() {
//        return "Experience{" +
//                "id=" + id +
//                ", profileId=" + profileId +
//                ", title='" + title + '\'' +
//                ", company='" + company + '\'' +
//                ", employmentType=" + employmentType +
//                ", location=" + location +
//                ", startDateExperience=" + startDateExperience +
//                ", endDateExperience=" + endDateExperience +
//                ", descriptionExperience='" + descriptionExperience + '\'' +
//                '}';
//    }
}
