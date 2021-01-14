package service.model;

import java.util.List;

public class Data {

    private List<Experience> experiences;
    private List<Education> educations;
    private List<Skill> skills;
//    private List<About> abouts;

    public Data(List<Experience> experiences, List<Education> educations, List<Skill> skills) {
        this.experiences = experiences;
        this.educations = educations;
        this.skills = skills;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


}
