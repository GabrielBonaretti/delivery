package src.Entities;

public class User {
    public int id;
    public String name;
    public Address address;
    public String dpf;

    public User(int id, String name, Address address, String dpf) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dpf = dpf;
    }
}
