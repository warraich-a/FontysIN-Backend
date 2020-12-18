package service.model;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(streetName, location.streetName) &&
                Objects.equals(buildingNumber, location.buildingNumber) &&
                Objects.equals(city, location.city) &&
                Objects.equals(zipcode, location.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, buildingNumber, city, zipcode);
    }
}
