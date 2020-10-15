package service.model;

import java.time.LocalDate;


public class Experience {

    private int id;
    private int profileId;
    private String title;
    private String company;
    private EmplymentType employmentType;
    private int locationId;
    private LocalDate startDateExperience;
    private LocalDate endDateExperience;
    private String descriptionExperience;
    private static int idSeeder = 0;

    public Experience(int profileId, String title, String company, EmplymentType employmentType, int locationId, LocalDate startDate, LocalDate endDate, String description) {
        this.id = idSeeder;
        idSeeder++;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDate getStartDateExperience() {
        return startDateExperience;
    }

    public void setStartDateExperience(LocalDate startDateExperience) {
        this.startDateExperience = startDateExperience;
    }

    public LocalDate getEndDateExperience() {
        return endDateExperience;
    }

    public void setEndDateExperience(LocalDate endDateExperience) {
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
