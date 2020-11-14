package service;

import service.model.User;
import service.model.UserType;

import java.net.URI;
import java.time.LocalDate;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    private static final URI BASE_URI = URI.create("http://localhost:9090");

    public static void main(String[] args) {

        PersistenceController controller = new PersistenceController();

//        controller.DeleteEducation(5,6,12);

//        controller.UserFilteredWithType(UserType.Student);
//        controller.UserFilteredWithLocation(1);
//        controller.UserFilteredWithDepartment(2);
//        controller.UserFilteredWithStartStudyYear(2012);
//        controller.UserFilteredWithStartWorkYear(2011);

//        controller.UserFilterByTypeLocationAndDepartment(UserType.Student, 1, 2);

//        controller.UserFilterByTypeLocationDepartmentAndStartSudyYear(UserType.Student, 2012,1,2);

//        controller.UserFilterByTypeLocationDepartmentAndStartWorkyearFontysStaff(UserType.FontysStaff, 2013, 1,3);

//        controller.UserFilterByTypeLocationDepartmentAndStartWorkyearTeacher(UserType.Teacher, 2011, 1,1);
    }
}
