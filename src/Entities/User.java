package src.Entities;


/**
 * The User class represents a user in the application.
 */
public class User {
    // The unique identifier for the user.
    public int id;

    // The name of the user.
    public String name;

    // The address of the user.
    public Address address;

    // The CPF (Brazilian ID) of the user.
    public String cpf;


    /**
     * Constructs a User object with the specified ID, name, address, and CPF.
     *
     * @param id      The unique identifier for the user.
     * @param name    The name of the user.
     * @param address The address of the user.
     * @param cpf     The CPF (Brazilian ID) of the user.
     */
    public User(int id, String name, Address address, String cpf) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cpf = cpf;
    }
}
