package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.*;

import service.repository.DatabaseException;
import service.repository.ProfileRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @InjectMocks
    ProfileController profileController;

    @Mock
    ProfileRepository profileRepository;



    @Test
    public void GetProfileTest() throws DatabaseException, URISyntaxException, SQLException {
        Profile p1 = new Profile(1, "English");
        Profile p2 = new Profile(1, "Urdu");

        List<Profile> expectedList = Arrays.asList( p1, p2);

        when(profileRepository.getProfile(1))
                .thenReturn(
                        Arrays.asList(p1, p2)
                );

        List<Profile> actualList = profileController.getProfile(1);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(1), actualList.get(1));
    }

    @Test
    public void GetExperiencesTest() throws DatabaseException, URISyntaxException, SQLException {
       Experience e1= new Experience( 2, "Clerk", "Fontys", EmplymentType.FreeLancer,  "Islamabad, Pakistan",2016, 2020, "I was happy there" );
        Experience e2= new Experience( 2, "Boss", "Fontys", EmplymentType.FullTime,  "Islamabad, Pakistan",2016, 2020, "Good one" );

        List<Experience> expectedList = Arrays.asList( e1, e2);

        when(profileRepository.getExperiences(100, 2))
                .thenReturn(
                    Arrays.asList(e1, e2)
        );

        List<Experience> actualList = profileController.getExperience(100, 2);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(1), actualList.get(1));
    }

    @Test
    public void GetEducationsTest() throws DatabaseException, URISyntaxException, SQLException {
        Education edu1 = new Education(2, "Fontys", 2019, 2023, "High School", "ICT", "Got good grades" );
        Education edu2 = new Education(2, "NJC", 2000, 2005, "Bechelors", "ICT", "Got good grades" );
        Education edu3 = new Education(2, "SGD ", 2016, 2021, "Bechelors", "ICT", "Got good grades" );
        Education edu4 = new Education(2, "FoId", 2015, 2020, "Bechelors", "ICT", "Got good grades" );


        List<Education> expectedList = Arrays.asList(edu1, edu2, edu3, edu4);

        when(profileRepository.getEducations(100, 2)).thenReturn(
                Arrays.asList(edu1, edu2, edu3, edu4)
        );

        List<Education> actualList = profileController.getEducations(100, 2);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(3), actualList.get(3));
    }


    @Test
    public void GetSkillsTest() throws DatabaseException, URISyntaxException, SQLException {

        Skill s3 = new Skill(2, "C#");
        Skill s4 = new Skill(2, "Java");

        List<Skill> expectedList = Arrays.asList(s3, s4);

        when(profileRepository.getSkills(100, 2)).thenReturn(
                Arrays.asList(s3, s4)
        );

        List<Skill> actualList = profileController.getSkills(100, 2);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(1), actualList.get(1));

    }

    @Test
    public void GetAboutTest() throws DatabaseException, URISyntaxException, SQLException {
        About a1 = new About(2, "I am businesman");

        List<About> expectedList = Arrays.asList(a1);

        when(profileRepository.getAbout(100, 2)).thenReturn(
                Arrays.asList(a1)
        );

        List<About> actualList = profileController.getAbout(100, 2);

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0), actualList.get(0));

    }

    @Test
    public void AddAboutTest() throws URISyntaxException, DatabaseException, SQLException {
        About a1 = new About(2, "I am businesman");

       when(profileRepository.createAbout(a1)).thenReturn(true);
        boolean isAdded = profileController.addAbout(a1);

        assertEquals(true, isAdded);

    }

    @Test
    public void AddExperienceTest() throws URISyntaxException, DatabaseException, SQLException {
        Experience e1= new Experience( 2, "Clerk", "Fontys", EmplymentType.FreeLancer,  "Islamabad, Pakistan",2016, 2020, "I was happy there" );

        when(profileRepository.createExperience(e1)).thenReturn(true);
        boolean isAdded = profileController.addExperience(e1);

        assertEquals(true, isAdded);

    }

    @Test
    public void AddEducationTest() throws URISyntaxException, DatabaseException, SQLException {
        Education edu1 = new Education(2, "Fontys", 2019, 2023, "High School", "ICT", "Got good grades" );

        when(profileRepository.createEducation(edu1)).thenReturn(true);
        boolean isAdded = profileController.addEducation(edu1);

        assertEquals(true, isAdded);

    }

    @Test
    public void AddProfileTest() throws URISyntaxException, DatabaseException, SQLException {
        Profile p1 = new Profile(1, "English");

        when(profileRepository.createProfile(p1, 1)).thenReturn(p1.getId());

        int newProfileId = profileController.addProfile(p1, 1);

        assertEquals(p1.getId(), newProfileId);

    }

    @Test
    public void UploadProfilePictureTest() throws URISyntaxException, DatabaseException, SQLException, IOException {
        when(profileRepository.uploadImage(1, "IMG_20160827_163306.jpg")).thenReturn(true);

        boolean isAdded = profileController.uploadPicture(1, "IMG_20160827_163306.jpg");

        assertEquals(true, isAdded);

    }

    @Test
    public void GetFontysLocationsTest() throws URISyntaxException, DatabaseException, SQLException, IOException {

        Location l1 = new Location(1, "Julianastraat", "R10" , "Eindhoven", "4544HH");
        Location l2 = new Location(2, "Besverstraat", "R2" , "Tilburg", "4334SD");
        Location l3 = new Location(3, "Koningstraat", "R0" , "Venlo", "3243ER");

        List<Location> expectedList = Arrays.asList( l1, l2, l3);

        when(profileRepository.getFontysLocation())
                .thenReturn(
                        Arrays.asList( l1, l2, l3)
                );

        List<Location> actualList = profileController.getFontysLocations();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(1), actualList.get(1));
    }

    @Test
    public void GetFontysDepartmentTest() throws URISyntaxException, DatabaseException, SQLException, IOException {

        Department d1 = new Department(1, "ICT", "Computer Science");
        Department d2 = new Department(2, "Electrical Engineering", "Engineering");



        List<Department> expectedList = Arrays.asList(d1, d2 );

        when(profileRepository.getFontysDepartments())
                .thenReturn(
                        Arrays.asList(d1, d2 )
                );

        List<Department> actualList = profileController.getFontysDepartments();

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(1), actualList.get(1));
    }
}
