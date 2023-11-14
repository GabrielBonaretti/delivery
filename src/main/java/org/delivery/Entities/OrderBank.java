package org.delivery.Entities;

/**
 * The OrderBank class represents a completed order in the application retrieved in the bank.
 */
public class OrderBank {
    // The unique identifier for the order.
    public int id;

    // The date of the order.
    public String date;

    // The total price of the order.
    public double totalPrice;


    /**
     * Constructs an OrderBank object with the specified ID, date, and total price.
     *
     * @param id         The unique identifier for the order.
     * @param date       The date of the order.
     * @param totalPrice The total price of the order.
     */
    public OrderBank(int id, String date, double totalPrice) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
    }
}
