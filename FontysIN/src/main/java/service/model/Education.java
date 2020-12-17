package service.model;

import java.util.Objects;


public class Education {
    private int id;
    private int profileId;
    private String school;
    private int startYearEducation;
    private int endYearEducation;
    private String degreeEducation;
    private String fieldStudy;
    private String descriptionEducation;
    private static int idSeeder = 1;


    public Education(int profileId, String school, int startYear, int endYear, String degree, String fieldStudy, String description) {
        this.id = idSeeder;
        idSeeder++;
        this.profileId = profileId;
        this.school = school;
        this.startYearEducation = startYear;
        this.endYearEducation = endYear;
        this.degreeEducation = degree;
        this.fieldStudy = fieldStudy;
        this.descriptionEducation = description;
    }

    public Education(int id, int profileId, String school, int startYear, int endYear, String degree, String fieldStudy, String description) {
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getStartYearEducation() {
        return startYearEducation;
    }

    public void setStartYearEducation(int startYearEducation) {
        this.startYearEducation = startYearEducation;
    }

    public int getEndYearEducation() {
        return endYearEducation;
    }

    public void setEndYearEducation(int endYearEducation) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return id == education.id &&
                profileId == education.profileId &&
                startYearEducation == education.startYearEducation &&
                endYearEducation == education.endYearEducation &&
                Objects.equals(school, education.school) &&
                Objects.equals(degreeEducation, education.degreeEducation) &&
                Objects.equals(fieldStudy, education.fieldStudy) &&
                Objects.equals(descriptionEducation, education.descriptionEducation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, school, startYearEducation, endYearEducation, degreeEducation, fieldStudy, descriptionEducation);
    }
}
