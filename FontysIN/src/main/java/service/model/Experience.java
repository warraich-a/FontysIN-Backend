package service.model;

import java.time.LocalDate;


public class Experience {

    private int id;
    private int profileId;
    private String title;
    private String company;
    private EmplymentType employmentType;
    private int locationId;
    private String startDateExperience;
    private String endDateExperience;
    private String descriptionExperience;

    public Experience(int id, int profileId, String title, String company, EmplymentType employmentType, int locationId, String startDate, String endDate, String description) {
        this.id = id;
        this.profileId = profileId;
        this.title = title;
        this.company = company;
        this.employmentType = employmentType;
        this.locationId = locationId;
        this.startDateExperience = startDate;
        this.endDateExperience = endDate;
        this.descriptionExperience = description;
    }


    public Experience() {

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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStartDateExperience() {
        return startDateExperience;
    }

    public void setStartDateExperience(String startDateExperience) {
        this.startDateExperience = startDateExperience;
    }

    public String getEndDateExperience() {
        return endDateExperience;
    }

    public void setEndDateExperience(String endDateExperience) {
        this.endDateExperience = endDateExperience;
    }

    public String getDescriptionExperience() {
        return descriptionExperience;
    }

    public void setDescriptionExperience(String descriptionExperience) {
        this.descriptionExperience = descriptionExperience;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", employmentType=" + employmentType +
                ", locationId=" + locationId +
                ", startDateExperience=" + startDateExperience +
                ", endDateExperience=" + endDateExperience +
                ", descriptionExperience='" + descriptionExperience + '\'' +
                '}';
    }
}
