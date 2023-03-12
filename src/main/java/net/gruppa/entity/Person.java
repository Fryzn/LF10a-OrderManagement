package net.gruppa.entity;

public class Person {

    private int id;
    private String name;
    private String lastname;
    private String password;
    private String username;
    private String email;
    private Address address;

    public Person(int id, String username, String name, String lastname, String password, String email, Address address) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    // Id
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    // First name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    // Last name
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    // Username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    // Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    // E-Mail
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    // Address
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) { this.address = address; }
}
