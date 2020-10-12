package service.model;

public class Address {
    private int id;
    private String streetName;
    private String houseNumber;
    private String city;
    private String zipCode;

    public Address(int id, String streetName, String houseNumber, String city, String zipCode) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
