package org.delivery.UI.Layout;

import org.delivery.Entities.Restaurant;
import org.delivery.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;

/**
 * The RestaurantLabel class represents a labeled display for a restaurant in the ListRestaurantLayout.
 * It includes the restaurant name, address, and a button to enter the restaurant.
 */
public class RestaurantLabel extends JLabel {

    /**
     * Constructs a RestaurantLabel object with the specified parameters.
     *
     * @param restaurant The Restaurant instance representing the specific restaurant.
     * @param y          The y-coordinate position of the label in the UI.
     * @param delivery   The Delivery instance for managing the navigation and restaurant details.
     */
    public RestaurantLabel(Restaurant restaurant, int y, Delivery delivery) {
        this.setBounds(0, y, 500, 50);
        this.setBackground(new Color(180,180,180));
        this.setOpaque(true);

        // Label to display the restaurant image
        JLabel labelImage = new JLabel();
        labelImage.setBounds(35, 0, 50, 50);
        ImageIcon image = new ImageIcon("src/main/java/org/delivery/Resources/lunch.png");
        image.setImage(image.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

        // Label to display the restaurant name
        JLabel textName = new JLabel(restaurant.name);
        textName.setFont(new Font("Arial", Font.BOLD,15));
        textName.setBounds(110, 0, 150, 50);

        // Label to display the restaurant position
        String restaurantPosition = restaurant.address.positionX+", "+ restaurant.address.positionY;
        JLabel textPosition = new JLabel(restaurantPosition);
        textPosition.setFont(new Font("Arial", Font.BOLD,15));
        textPosition.setBounds(270, 0, 50, 50);

        // Button to enter the restaurant
        JButton buttonRestaurant = new JButton("Entrar");
        buttonRestaurant.setFont(new Font("Arial", Font.BOLD,15));
        buttonRestaurant.setBounds(360, 10, 100, 30);
        buttonRestaurant.setFocusable(false);
        buttonRestaurant.addActionListener(e -> {
            // Display the specific restaurant page and hide other layouts
            delivery.restaurantSpecificPage.createComponents(restaurant);
            delivery.restaurantSpecificPage.setVisible(true);
            delivery.listRestaurantLayout.setVisible(false);
            delivery.orderLayout.setVisible(false);
            delivery.myRestaurantLayout.setVisible(false);
            delivery.historicLayout.setVisible(false);
        });

        labelImage.setIcon(image);
        this.add(labelImage);
        this.add(textName);
        this.add(textPosition);
        this.add(buttonRestaurant);
    }
}
