package service.model;

import java.util.Objects;

public class Privacy {
public enum Setting {
    EVERYONE, CONNECTIONS, ONLYME
}
private int id;
 private int userId;
 private Setting educationSetting;
    private Setting experienceSetting;
    private Setting skillSetting;
    private boolean hideFromSearch;

//0 is everyone, 1 is friends and 2 is only me
    public Privacy(){
        educationSetting = Setting.EVERYONE;
        experienceSetting = Setting.EVERYONE;
        skillSetting = Setting.EVERYONE;
        hideFromSearch = false;
    }

    public Privacy(int userId) {

        this.userId = userId;
        educationSetting = Setting.EVERYONE;
        experienceSetting = Setting.EVERYONE;
        skillSetting = Setting.EVERYONE;
        hideFromSearch = false;
    }
    public Privacy(int id, int userId, Setting edu, Setting exp, Setting ski, boolean search) {
        this.id = id;
        this.userId = userId;
        educationSetting = edu;
        experienceSetting = exp;
        skillSetting = ski;
        hideFromSearch = search;
    }

    public boolean getHideFromSearch() {
        return hideFromSearch;
    }

    public void setHideFromSearch(boolean hideFromSearch) {
        this.hideFromSearch = hideFromSearch;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privacy privacy = (Privacy) o;
        return id == privacy.id &&
                userId == privacy.userId &&
                hideFromSearch == privacy.hideFromSearch &&
                educationSetting == privacy.educationSetting &&
                experienceSetting == privacy.experienceSetting &&
                skillSetting == privacy.skillSetting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, educationSetting, experienceSetting, skillSetting, hideFromSearch);
    }
}
