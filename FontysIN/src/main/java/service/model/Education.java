package service.model;

import java.time.LocalDate;


public class Education {
    private int id;
    private int profileId;
    private String school;
    private LocalDate startYear;
    private LocalDate endYear;
    private String degree;
    private String fieldStudy;
    private String description;

    public Education(int id, int profileId, String school, LocalDate startYear, LocalDate endYear, String degree, String fieldStudy, String description) {
        this.id = id;
        this.profileId = profileId;
        this.school = school;
        this.startYear = startYear;
        this.endYear = endYear;
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

    public void setStartYear(LocalDate startDate) {
        this.startYear = startDate;
    }

    public LocalDate getEndDate() {
        return endYear;
    }

    public void setEndDate(LocalDate endDate) {
        this.endYear = endDate;
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

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", school='" + school + '\'' +
                ", startYear=" + startYear +
                ", endDate=" + endYear +
                ", degree='" + degree + '\'' +
                ", fieldStudy='" + fieldStudy + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
