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
    private int locationId; // user's location
    private int departmentId; // user's department
    private String userNumber; // user's number
    private String img; // user's image /* I will add this to the constracture later*/
    private UserType userType; // user's type
    private Education education; // user's education
    private Privacy privacy;


    public User(int id, String firstName, String lastName, UserType type, String email, String password,
                int locationId, int departmentId, String userNumber, Education education) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = type;
        this.password = password;

        this.departmentId = departmentId;
        this.locationId = locationId;
        this.userNumber = userNumber;
        this.education = education;
        privacy = new Privacy(id);
    }
    public User(int id, String firstName, String lastName, UserType type, String email, String password,
                String phoneNumber, int addressId, int locationId, int departmentId, String userNumber, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = type;
        this.password = password;
        this.departmentId = departmentId;
        this.locationId = locationId;
        this.userNumber = userNumber;
        privacy = new Privacy(id);
        this.img = image;
    }
    public User(int id, String firstName, String lastName, UserType type, String email, String password,
                int locationId, int departmentId, String userNumber, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = type;
        this.password = password;
        this.departmentId = departmentId;
        this.locationId = locationId;
        this.userNumber = userNumber;
        privacy = new Privacy(id);
        this.img = image;
    }

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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



    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    //methods


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", locationId=" + locationId +
                ", departmentId=" + departmentId +
                ", userNumber='" + userNumber + '\'' +
                ", img='" + img + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                locationId == user.locationId &&
                departmentId == user.departmentId &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userNumber, user.userNumber) &&
                Objects.equals(img, user.img) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, locationId, departmentId, userNumber, img, userType, education, privacy);
    }
}
