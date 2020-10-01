package service.repository;

import service.model.Education;
import service.model.Experience;
import service.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDataProfile {


    private final List<Experience> experiences = new ArrayList<>();
    private final List<Education> educations = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private static final FakeDataProfile INSTANCE = new FakeDataProfile();
    public static FakeDataProfile getInstance() {
        return INSTANCE;
    }

    private FakeDataProfile(){
        Experience e1 = new Experience(1, 1, "No title", "Fontys", "Teacher", 1, LocalDate.of(1998,01,01), LocalDate.of(2000,01,01), "I love it" );
        Experience e2 = new Experience(2, 1, "No title", "Fontys", "Employee", 1, LocalDate.of(1996,01,01), LocalDate.of(1998,01,01), "was good" );

        experiences.add(e1);
        experiences.add(e2);

        Education edu1 = new Education(1, 1, "Fontys", LocalDate.of(2018,01,01), LocalDate.of(2020,01,01), "High School", "ICT", "Got good grades" );
        Education edu2 = new Education(2, 1, "Fontys", LocalDate.of(2016,01,01), LocalDate.of(2020,01,01), "Bechelors", "ICT", "Got good grades" );

        educations.add(edu1);
        educations.add(edu2);
    }

    // to get all the experiences
    public List<Experience> GetExperiences (){return experiences;}

    //to get experience
    public Experience GetExperienceById(int id){
        for (Experience e: experiences){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    public User GetUserById(int id){
        for (User e: users){
            if(e.getUserID() == id){
                return e;
            }
        }
        return null;
    }

    //to add experience
    public boolean AddExperience(Experience e){
        if (this.GetExperienceById(e.getId()) == null)
        {
            experiences.add(e);
                return true;
        }
        return false;
    }


    //Educations
    // to get all the All educations
    public List<Education> GetEducations (){return educations;}

    //to get experience
    public Education GetEducationById(int id){
        for (Education e: educations){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    //to add experience
    public boolean AddEducation(Education e){
        if (this.GetExperienceById(e.getId()) == null)
        {
            educations.add(e);
            return true;
        }
        return false;
    }

    public boolean updateExperience(Experience e) {
        Experience old = this.GetExperienceById(e.getId());
        if (old == null) {
            return false;
        }
        old.setTitle(e.getTitle());
        old.setEmploymentType(e.getEmploymentType());
        old.setLocationId(e.getLocationId());
        old.setCompany(e.getCompany());
        old.setStartDate(e.getStartDate());
        old.setEndDate(e.getEndDate());
        old.setDescription(e.getDescription());

        return true;
    }
    public boolean updateEducation(Education e) {
        Education old = this.GetEducationById(e.getId());
        if (old == null) {
            return false;
        }
        old.setSchool(e.getSchool());
        old.setStartYear(e.getStartYear());
        old.setEndDate(e.getEndDate());
        old.setDegree(e.getDegree());
        old.setFieldStudy(e.getFieldStudy());
        old.setDescription(e.getDescription());

        return true;
    }
    public boolean updateUser(User e) {
        User old = this.GetUserById(e.getUserID());
        if (old == null) {
            return false;
        }
        old.setUserEmail(e.getUserEmail());
        old.setUserPhoneNumber(e.getUserPhoneNumber());
        old.setUserAddress(e.getUserAddress());
        old.setUserImage(e.getUserImage());
        old.setUserLocation(e.getUserLocation());
        old.setUserDepartment(e.getUserDepartment());

        return true;
    }


}
