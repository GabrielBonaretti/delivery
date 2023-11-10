package src.Entities;

/**
 * The Address class represents a geographic location with X and Y coordinates.
 */
public class Address {


    // The X coordinate of the address.
    public int positionX;

    // The Y coordinate of the address.
    public int positionY;


    /**
     * Constructs an Address object with the specified X and Y coordinates.
     *
     * @param positionX The X coordinate of the address.
     * @param positionY The Y coordinate of the address.
     */
    public Address(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
