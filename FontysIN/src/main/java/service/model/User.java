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
    private String phoneNumber; // user's phone number
    private int addressId; // user's address
    private int locationId; // user's location
    private int departmentId; // user's department
    private String userNumber; // user's number
    private String img; // user's image /* I will add this to the constracture later*/
    private UserType userType; // user's type
    private Education education; // user's education


    public User(int id, String firstName, String lastName, UserType type, String email, String password,
                 String phoneNumber, int addressId, int locationId, int departmentId, String userNumber, Education education) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = type;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
        this.departmentId = departmentId;
        this.locationId = locationId;
        this.userNumber = userNumber;
        this.education = education;
    }

    public User() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhoneNumber() {
        return phoneNumber;
    }

    public void setUserPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
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

    @Override
    public String toString() {
        return "User{" +
                "User ID: " + id +
                ", First Name: '" + firstName + '\'' +
                ", Last Name: '" + lastName + '\'' +
                ", Email: '" + email + '\'' +
                ", Password: '" + password + '\'' +
                ", Phone Number: " + phoneNumber + '\'' +
                ", Address: " + addressId + '\'' +
                ", Location: " + locationId + '\'' +
                ", Department: " + departmentId + '\'' +
                ", User Number: " + userNumber + '\'' +
                '}';
    }
}
