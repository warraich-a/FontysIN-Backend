package service.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.*;
import service.model.dto.ContactDTO;
import service.model.dto.UserDTO;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrivacyRepositoryTest {
    @InjectMocks
    PrivacyRepository repository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName ("org.h2.Driver");

        lenient().when(jdbcRepository.getDatabaseConnection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test")
        );

        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
    }

    @Test
    void createPrivacy() throws DatabaseException, URISyntaxException {
        Privacy p = new Privacy(4);
        Boolean created = repository.createPrivacy(p);

        assertEquals(created,true);

    }

    @Test
    void updatePrivacy() throws DatabaseException, URISyntaxException {
        Privacy p = new Privacy(4);
        p.setUserId(5);
        Boolean created = repository.updatePrivacy(p);

        assertTrue(created);

    }

    @Test
    void getPrivacyList() throws URISyntaxException, DatabaseException, SQLException {
        List<Privacy> expectedPrivacyList = Arrays.asList(
            new Privacy(1), new Privacy(2), new Privacy(3), new Privacy(4),new Privacy(5),new Privacy(6)
        );
        List<Privacy> actualPrivacyList = repository.getPrivacyList();
        assertEquals(expectedPrivacyList.size(), actualPrivacyList.size());

    }
    @Test
    void getPrivacyByUser() throws DatabaseException, URISyntaxException {
        User user = new User(6, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        Privacy actualPrivacy = new Privacy(6);
        actualPrivacy.setSkillSetting(Privacy.Setting.ONLYME);


        Privacy expected = repository.getPrivacyByUser(user);

        assertEquals(expected.getSkillSetting(),actualPrivacy.getSkillSetting());

    }
}
