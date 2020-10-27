package service.model;

public class Location {

    //fields
    private int id;
    private String streetName;
    private String buildingNumber;
    private String city;
    private String zipcode;

    //constractures
    public Location(int id, String streetName, String buildingNumber, String city, String zipcode) {
        this.id = id;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Location() {
    }

    //getters and setters

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

    public String getBuildingNumber() {
        return buildingNumber;
    }
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
