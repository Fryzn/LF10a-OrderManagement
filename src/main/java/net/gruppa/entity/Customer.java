package net.gruppa.entity;

public class Customer {

    private int id;
    private String companyName;
    private String contactPerson;
    private String email;
    private Address address;

    public Customer(int id, String companyName, String contactPerson, String email, Address address) {
        this.id = id;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.email = email;
        this.address = address;
    }

    // Id
    public void setId(int id) { this.id = id; }
    public int getId() {
        return id;
    }
    // Company name
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    // Contact person
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    // E-Mail
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) { this.email = email; }
    // Address
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) { this.address = address; }
}
