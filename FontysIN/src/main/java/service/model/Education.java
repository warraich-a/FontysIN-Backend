package service.model;

import java.time.LocalDate;

public class Education {
    private int id;
    private int profileId;
    private String school;
    private LocalDate startYear;
    private LocalDate endDate;
    private String degree;
    private String fieldStudy;
    private String description;

    public Education(int id, int profileId, String school, LocalDate startYear, LocalDate endDate, String degree, String fieldStudy, String description) {
        this.id = id;
        this.profileId = profileId;
        this.school = school;
        this.startYear = startYear;
        this.endDate = endDate;
        this.degree = degree;
        this.fieldStudy = fieldStudy;
        this.description = description;
    }

    public Education() {

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getStartYear() {
        return startYear;
    }

    public void setStartYear(LocalDate startYear) {
        this.startYear = startYear;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldStudy() {
        return fieldStudy;
    }

    public void setFieldStudy(String fieldStudy) {
        this.fieldStudy = fieldStudy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
