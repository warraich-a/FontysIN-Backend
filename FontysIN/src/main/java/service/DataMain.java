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

        controller.UserFilteredWithType(UserType.Student);
    }
}
