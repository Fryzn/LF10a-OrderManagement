package net.gruppa.entity;

public class Address {

    private String street;
    private int houseNumber;
    private int postCode;
    private String city;

    public Address(String street, int houseNumber, int postCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.city = city;
    }

    // Street
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    // House Number
    public int getHouseNumber() { return houseNumber; }
    public void setHouseNumber(int houseNumber) { this.houseNumber = houseNumber; }
    // Post Code
    public int getPostCode() { return postCode; }
    public void setPostCode(int postCode) { this.postCode = postCode; }
    // City
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
