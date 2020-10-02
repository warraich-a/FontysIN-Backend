package service.model;

import java.time.LocalDate;


public class Experience {

    private int id;
    private int profileId;
    private String title;
    private String company;
    private EmplymentType employmentType;
    private int locationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    public Experience(int id, int profileId, String title, String company, EmplymentType employmentType, int locationId, LocalDate startDate, LocalDate endDate, String description) {
        this.id = id;
        this.profileId = profileId;
        this.title = title;
        this.company = company;
        this.employmentType = employmentType;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Education{" +
                "Id: " + id +
                ", ProfileId: " + profileId +
                ", Title: " + title + '\'' +
                ", Company: " + company + '\'' +
                ", Emplyment Type: " + employmentType + '\'' +
                ", LocationId: " + locationId + '\'' +
                ", Start Date: " + startDate + '\'' +
                ", End Date: " + endDate + '\'' +
                ", Description: " + description + '\'' +
                '}';
    }

}
