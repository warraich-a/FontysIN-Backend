package service.model;

import java.time.LocalDate;


public class Education {
    private int id;
    private int profileId;
    private String school;
    private String startYearEducation;
    private String endYearEducation;
    private String degreeEducation;
    private String fieldStudy;
    private String descriptionEducation;

    public Education(int id, int profileId, String school, String startYear, String endYear, String degree, String fieldStudy, String description) {
        this.id = id;
        this.profileId = profileId;
        this.school = school;
        this.startYearEducation = startYear;
        this.endYearEducation = endYear;
        this.degreeEducation = degree;
        this.fieldStudy = fieldStudy;
        this.descriptionEducation = description;
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

    public String getStartYearEducation() {
        return startYearEducation;
    }

    public void setStartYearEducation(String startYearEducation) {
        this.startYearEducation = startYearEducation;
    }

    public String getEndYearEducation() {
        return endYearEducation;
    }

    public void setEndYearEducation(String endYearEducation) {
        this.endYearEducation = endYearEducation;
    }

    public String getDegreeEducation() {
        return degreeEducation;
    }

    public void setDegreeEducation(String degreeEducation) {
        this.degreeEducation = degreeEducation;
    }

    public String getFieldStudy() {
        return fieldStudy;
    }

    public void setFieldStudy(String fieldStudy) {
        this.fieldStudy = fieldStudy;
    }

    public String getDescriptionEducation() {
        return descriptionEducation;
    }

    public void setDescriptionEducation(String descriptionEducation) {
        this.descriptionEducation = descriptionEducation;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", school='" + school + '\'' +
                ", startYearEducation=" + startYearEducation +
                ", endYearEducation=" + endYearEducation +
                ", degreeEducation='" + degreeEducation + '\'' +
                ", fieldStudy='" + fieldStudy + '\'' +
                ", descriptionEducation='" + descriptionEducation + '\'' +
                '}';
    }
}
