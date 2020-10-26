package service.model;

public class Privacy {

 private int profilePage;

//0 is everyone, 1 is friends and 2 is only me
    public Privacy(){
        profilePage = 0;
    }
    public int getProfilePage() {
        return profilePage;
    }

    public void setProfilePage(int profilePage) {
        this.profilePage = profilePage;
    }
}
