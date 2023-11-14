package org.delivery.UI.Layout;

import org.delivery.Entities.Application;
import org.delivery.Entities.Food;
import org.delivery.Entities.Restaurant;
import org.delivery.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The LancheLabel class represents a labeled display for a food item available in RestaurantSpecificPage.
 * It includes the food name, price, and a button to add the item to the order.
 */
public class LancheLabel extends JLabel {

    /**
     * Constructs a LancheLabel object with the specified parameters.
     *
     * @param food       The Food instance representing the specific food item.
     * @param delivery   The Delivery instance for managing the ordering process.
     * @param restaurant The Restaurant instance representing the source restaurant of the food item.
     * @param application The Application instance managing the overall application state.
     */
    public LancheLabel(Food food, Delivery delivery, Restaurant restaurant, Application application) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setBackground(new Color(240,240,240));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Label to display the food name
        JLabel name = new JLabel(food.name);
        name.setFont(new Font("Arial", Font.BOLD,15));
        name.setBounds(30, 0, 270, 50);
        this.add(name);

        // Label to display the price of the food item
        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(340, 0, 100, 50);
        this.add(price);

        // Button to add the food item to the order
        JButton button = new JButton("+");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 1, 50, 48);
        button.setBackground(new Color(240,240,240));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            // Check if the order is from a different restaurant
            if (application.order.restaurant != null && !Objects.equals(application.order.restaurant.name, restaurant.name)) {
                application.order.restaurant = restaurant;
                application.order.cart.clear();
                delivery.orderLayout.createRequests();
            } else {
                application.order.restaurant = restaurant;
            }

            // Add the food item to the order and update the display
            application.order.doOrder(food);
            delivery.orderLayout.createRequests();
            System.out.println(application.order.restaurant.name);
        });
        this.add(button);
    }
}
