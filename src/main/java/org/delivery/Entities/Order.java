package org.delivery.Entities;

import org.delivery.Database.Database;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * The Order class represents an order made by a user in the application.
 */
public class Order {
    // The restaurant associated with the order.
    public Restaurant restaurant;

    // The user who placed the order.
    public User user;

    // The list representing the items in the user's cart for the order.
    // Each element is an ArrayList containing a Food object and the quantity of that food in the order.
    public ArrayList<ArrayList<Object>> cart = new ArrayList<ArrayList<Object>>();


    /**
     * Sets the user associated with the order.
     *
     * @param user The user to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Adds a food item to the user's cart for the order.
     * If the item already exists in the cart, increments the quantity.
     *
     * @param food The food item to be added to the cart.
     */
    public void doOrder(Food food) {
        boolean alreadyExists = false;
        if (cart.toArray().length != 0) {
            for (ArrayList<Object> itemOrder: cart) {
                Food foodOrder = (Food) itemOrder.get(0);
                if (Objects.equals(foodOrder.name, food.name)) {
                    int quantidade = (int) itemOrder.get(1);
                    itemOrder.set(1, quantidade + 1);
                    alreadyExists = true;
                }
            }
        }

        if (!alreadyExists) {
            ArrayList<Object> foodOrder = new ArrayList<Object>();
            foodOrder.add(food);
            foodOrder.add(1);
            this.cart.add(foodOrder);
        }
    }


    /**
     * Calculates the total price of the items in the user's cart.
     *
     * @return The total price of the items in the cart.
     */
    public double getSumValues() {
        double sumTotal = 0;
        if (cart.toArray().length != 0) {
            for (ArrayList<Object> foodOrder: cart) {
                Food food = (Food) foodOrder.get(0);
                int amount = (int) foodOrder.get(1);
                sumTotal += food.price * amount;
            }
        }
        return sumTotal;
    }


    /**
     * Saves the order to the database, including the associated foods and quantities.
     *
     * @param date       The date of the order.
     * @param totalPrice The total price of the order.
     */
    public void saveOrder(String date, double totalPrice) {
        // connects database
        Database database = new Database();

        // add order in datbase
        database.addOrder(user.id, date, totalPrice);
        int idLastOrder = database.getLastOrder();

        // add order food in datbase
        for (ArrayList<Object> foodOrder: cart) {
            Food food = (Food) foodOrder.get(0);
            int amount = (int) foodOrder.get(1);
            database.setOrderFood(idLastOrder, food.id, amount);
        }

        cart.clear();
    }


    /**
     * Prints the details of the order.
     */
    public void printOrder(String date, double totalPrice) {
        StringBuilder stringOrder = new StringBuilder(String.format(
                """
                Restaurant: %s
                Time order: %s
                Foods:
                """,
                restaurant.name,
                date
        ));

        // add order food in datbase
        for (ArrayList<Object> foodOrder: cart) {
            Food food = (Food) foodOrder.get(0);
            int amount = (int) foodOrder.get(1);

            String foodString = String.format("ID: %d | NAME: %s | PRICE: %.2f | QUANTITY: %d", food.id, food.name, food.price, amount);
            String specifyFood = String.format("""
               =>  %s
               """,
                foodString
            );

            stringOrder.append(specifyFood);
        }

        String totalPriceString = String.format("Total price: %.2f", totalPrice);
        stringOrder.append(totalPriceString);


        stringOrder.append("\nOrder created successfully!");

        JOptionPane.showMessageDialog(null, String.valueOf(stringOrder));

    }
}
