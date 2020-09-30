package service.repository;
import service.model.Experience;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDataProfile {


    private final List<Experience> experiences = new ArrayList<>();


    private static final FakeDataProfile INSTANCE = new FakeDataProfile();
    public static FakeDataProfile getInstance() {
        return INSTANCE;
    }

    private FakeDataProfile(){
        Experience e1 = new Experience(1, 1, "No title", "Fontys", "Teacher", 1, LocalDate.of(1998,01,01), LocalDate.of(2000,01,01), "I love it" );
        Experience e2 = new Experience(2, 1, "No title", "Fontys", "Employee", 1, LocalDate.of(1996,01,01), LocalDate.of(1998,01,01), "was good" );

        experiences.add(e1);
        experiences.add(e2);
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

    //to add experience
    public boolean AddExperience(Experience e){
        if (this.GetExperienceById(e.getId()) == null)
        {
            experiences.add(e);
                return true;
        }
        return false;
    }



}
