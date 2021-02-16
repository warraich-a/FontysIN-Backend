package service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.UserType;
import service.model.dto.UserDTO;
import service.repository.DatabaseException;
import service.repository.UserRepository;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserRepository userRepository;

    @Test
    void GetUserByType() throws DatabaseException, URISyntaxException {
        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByType(UserType.Student)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilteredWithType(UserType.Student);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByLocation() throws DatabaseException, URISyntaxException {
        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByLocation(1)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilteredWithLocation(1);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByDepartment() throws DatabaseException, URISyntaxException {
        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByDepartment(1)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilteredWithDepartment(1);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByStartStudyYear() throws DatabaseException, URISyntaxException {
        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByStartStudyYear(2019)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilteredWithStartStudyYear(2019);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByStartWorkYear() throws DatabaseException, URISyntaxException {

        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByStartWorkYear(2011)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilteredWithStartWorkYear(2011);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByTypeDepartmentAndLocation() throws DatabaseException, URISyntaxException {

        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByUserTypeAndLocationAndDepartment(UserType.Student,1,1)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilterByTypeLocationAndDepartment(UserType.Student,1,1);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByTypeDepartmentLocationStartStudyYear() throws DatabaseException, URISyntaxException {

        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByUserTypeAndStartStudyYearAndDepartmentAndLocation(UserType.Student,2019,1,1)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilterByTypeLocationDepartmentAndStartSudyYear(UserType.Student,2019,1,1);

        assertEquals(userDTOS.size(), dtos.size());
    }

    @Test
    void GetUserByTypeDepartmentLocationStartWorkYear() throws DatabaseException, URISyntaxException {

        UserDTO userDTO = new UserDTO(1,1,"Ranim","Alayoubi","image.pnf");

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(userDTO);

        when(userRepository.getUsersByUserTypeAndStartWorkYearAndDepartmentAndLocationFontysStaff(UserType.Student,2011,1,1)).thenReturn(userDTOS);

        List<UserDTO> dtos = userController.UserFilterByTypeLocationDepartmentAndStartWorkyearFontysStaff(UserType.Student,2011,1,1);

        assertEquals(userDTOS.size(), dtos.size());
    }
}
