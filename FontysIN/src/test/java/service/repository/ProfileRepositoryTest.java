package service.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProfileRepositoryTest {

    @InjectMocks
    ProfileRepository profileRepository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName ("org.h2.Driver");

        when(jdbcRepository.getDatabaseConnection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test") // test is the name of the folder inside db
        );

        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
    }

    @Test
    public void getProfileTest() throws DatabaseException, URISyntaxException, SQLException {
        List<Profile> profile = profileRepository.getProfile(1);

        assertEquals(2, profile.size());
    }


    @Test
    public void uploadImageTest() throws DatabaseException, URISyntaxException, SQLException {


       boolean isUploaded = profileRepository.uploadImage(1, "ERE");

        assertEquals(true, isUploaded);
    }


    @Test
    public void AddExperienceTest() throws DatabaseException, URISyntaxException, SQLException {
        Experience e1= new Experience( 2, "Clerk", "Fontys", EmplymentType.FreeLancer,  "Islamabad, Pakistan",2016, 2020, "I was happy there" );

        boolean isAdded = profileRepository.createExperience(e1);

        assertEquals(true, isAdded);
    }

    @Test
    public void AddEducationTest() throws DatabaseException, URISyntaxException, SQLException {
        Education edu1 = new Education(2, "Fontys", 2019, 2023, "High School", "ICT", "Got good grades" );

        boolean isAdded = profileRepository.createEducation(edu1);

        assertTrue("true", isAdded);
    }

    @Test
    public void AddProfileTest() throws DatabaseException, URISyntaxException, SQLException {
        Profile p1 = new Profile(1, "English");

        int isAdded = profileRepository.createProfile(p1, 1);

        assertEquals(0, isAdded);
    }

}
