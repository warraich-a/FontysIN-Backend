package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.User;
import service.model.UserType;
import service.repository.DatabaseException;
import service.repository.ProfileRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    ProfileRepository repository;


    @Test
    public void AdduserTest() throws DatabaseException, URISyntaxException, SQLException {
        User user = new User(1, "Beatrice", "Forslund", UserType.Student, "bea@fontys.com", "1234", 2, 2, "734695", "noImage");
        when(repository.createUser(user)).thenReturn(true);


        boolean expected =  userController.addUser(user);

        assertEquals(true, expected);

    }
}
