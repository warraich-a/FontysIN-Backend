package service.model;

public class Privacy {
public enum Setting {
    EVERYONE, CONNECTIONS, ONLYME
}
private int id;
 private int userId;
 private Setting EducationSetting;
    private Setting ExperienceSetting;
    private Setting SkillSetting;

//0 is everyone, 1 is friends and 2 is only me
    public Privacy(){
        EducationSetting = Setting.EVERYONE;
        ExperienceSetting = Setting.EVERYONE;
        SkillSetting = Setting.EVERYONE;
    }

    public Privacy(int id, int userId) {
        this.id = id;
        this.userId = userId;
        EducationSetting = Setting.EVERYONE;
        ExperienceSetting = Setting.EVERYONE;
        SkillSetting = Setting.EVERYONE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Setting getEducationSetting() {
        return EducationSetting;
    }

    public void setEducationSetting(Setting educationSetting) {
        EducationSetting = educationSetting;
    }

    public Setting getExperienceSetting() {
        return ExperienceSetting;
    }

    public void setExperienceSetting(Setting experienceSetting) {
        ExperienceSetting = experienceSetting;
    }

    public Setting getSkillSetting() {
        return SkillSetting;
    }

    public void setSkillSetting(Setting skillSetting) {
        SkillSetting = skillSetting;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
