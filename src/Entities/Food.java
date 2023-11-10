package src.Entities;

/**
 * The Food class represents a food item in the application.
 */
public class Food {
    // The unique identifier for the food item.
    public int id;

    // The name of the food item.
    public String name;

    // The price of the food item.
    public double price;


    /**
     * Constructs a Food object with the specified name and price.
     *
     * @param name  The name of the food item.
     * @param price The price of the food item.
     */
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }


    /**
     * Sets the unique identifier for the food item.
     *
     * @param id The unique identifier to be set.
     */
    public void setId(int id) {
        this.id = id;
    }
}
