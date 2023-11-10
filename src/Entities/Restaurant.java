package src.Entities;

import src.Database.Database;

import java.util.ArrayList;

/**
 * The Restaurant class represents a restaurant in the application.
 */
public class Restaurant {
    // The unique identifier for the restaurant.
    public int id;

    // The name of the restaurant.
    public String name;

    // The address of the restaurant.
    public Address address;

    // The list of food items offered by the restaurant.
    public ArrayList<Food> listFoods = new ArrayList<Food>();


    /**
     * Constructs a Restaurant object with the specified name and address.
     *
     * @param name    The name of the restaurant.
     * @param address The address of the restaurant.
     */
    public Restaurant(String name, Address address) {
        this.name = name;
        this.address = address;
    }


    /**
     * Adds a food item to the restaurant's menu.
     *
     * @param food The food item to be added.
     */
    public void addFood(Food food) {
        Database database = new Database();
        database.addFood(id, food.name, food.price);
    }


    /**
     * Removes a food item from the restaurant's menu.
     *
     * @param foodId The unique identifier of the food item to be removed.
     */
    public void removeFood(int foodId) {
        Database database = new Database();
        database.deleteFood(foodId);
    }


    /**
     * Gets the list of food items offered by the restaurant.
     *
     * @return The list of food items.
     */
    public ArrayList<Food> getListFoods() {
        return listFoods;
    }


    /**
     * Sets the list of food items offered by the restaurant.
     */
    public void setListFoods() {
        Database database = new Database();
        ArrayList<Food> listFoods = database.getAllFoods(this.id);
        this.listFoods.clear();
        this.listFoods = listFoods;
    }


    /**
     * Sets the unique identifier for the restaurant.
     *
     * @param id The unique identifier to be set.
     */
    public void setId(int id) {
        this.id = id;
    }
}
