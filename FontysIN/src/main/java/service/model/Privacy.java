package service.model;

public class Privacy {
public enum Setting {
    EVERYONE, CONNECTIONS, ONLYME
}
private int id;
 private int userId;
 private Setting educationSetting;
    private Setting experienceSetting;
    private Setting skillSetting;

//0 is everyone, 1 is friends and 2 is only me
    public Privacy(){
        educationSetting = Setting.EVERYONE;
        experienceSetting = Setting.ONLYME;
        skillSetting = Setting.EVERYONE;
    }

    public Privacy(int id, int userId) {
        this.id = id;
        this.userId = userId;
        educationSetting = Setting.EVERYONE;
        experienceSetting = Setting.ONLYME;
        skillSetting = Setting.EVERYONE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Setting getEducationSetting() {
        return educationSetting;
    }

    public void setEducationSetting(Setting EducationSetting) {
        educationSetting = EducationSetting;
    }

    public Setting getExperienceSetting() {
        return experienceSetting;
    }

    public void setExperienceSetting(Setting ExperienceSetting) {
        experienceSetting = ExperienceSetting;
    }

    public Setting getSkillSetting() {
        return skillSetting;
    }

    public void setSkillSetting(Setting SkillSetting) {
        skillSetting = SkillSetting;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
