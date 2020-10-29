package service.model;

//import javax.xml.bind.annotation.XmlRootElement;

import java.util.Objects;
//
//@SuppressWarnings("WeakerAccess")
//@XmlRootElement
public class User {
    //fields
    private int id;
    private String firstName; // user's first name
    private String lastName; // user's last name
    private String email; // user's email
    private String password; // user's password
    private String phoneNumbar; // user's phone number
    private int addressId; // user's address
    private int locationId; // user's location
    private int departmentId; // user's department
    private String userNumber; // user's number
    private String img; // user's image /* I will add this to the constracture later*/
    private UserType userType; // user's type
    private Education education;
    private Working working;

    public User(int id, String firstName, String lastName, UserType type, String email, String password,
                 String phoneNumbar, int addressId, int locationId, int departmentId, String userNumber,
                Education education, Working working) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = type;
        this.password = password;
        this.phoneNumbar = phoneNumbar;
        this.addressId = addressId;
        this.departmentId = departmentId;
        this.locationId = locationId;
        this.userNumber = userNumber;
        this.education = education;
        this.working = working;
    }

    public User() {
    }

    //getters and setters
    public int getUserID() { return id; }

    public void setUserID(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType type) {
        this.userType = type;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String mail) {
        this.email = mail;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String pass) {
        this.password = pass;
    }

    public String getUserPhoneNumber() {
        return phoneNumbar;
    }

    public void setUserPhoneNumber(String phone) {
        this.phoneNumbar = phone;
    }

    public int getUserAddress() {
        return addressId;
    }
    public void setUserAddress(int addressId) { this.addressId = addressId; }

    public int getUserLocation() {
        return locationId;
    }
    public void setUserLocation(int locationId) { this.locationId = locationId; }

    public int getUserDepartment() { return departmentId; }
    public void setUserDepartment(int departmentId) { this.departmentId = departmentId; }

    public String getUserNumber() { return userNumber; }
    public void setUserNumber(String userNumber) { this.userNumber = userNumber; }

    public String getUserImage() { return img; }
    public void setUserImage(String img) { this.img = img; }

    public Education getEducation() {
        return education;
    }
    public void setEducation(Education education) {
        this.education = education;
    }

    public Working getWorking() {
        return working;
    }
    public void setWorking(Working working) {
        this.working = working;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User u = (User) o;
        return id == u.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
