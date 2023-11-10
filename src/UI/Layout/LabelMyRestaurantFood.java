package src.UI.Layout;

import src.Entities.Food;
import src.Entities.Restaurant;
import src.UI.Subpage.MyRestaurantLayout;

import javax.swing.*;
import java.awt.*;

/**
 * The LabelMyRestaurantFood class represents a labeled display for a food item in the MyRestaurantLayout.
 * It includes the food name, price, and a button to remove the food item from the restaurant's menu.
 */
public class LabelMyRestaurantFood extends JLabel {

    /**
     * Constructs a LabelMyRestaurantFood object with the specified food, restaurant, and layout.
     *
     * @param food                The food item to be displayed.
     * @param restaurant          The restaurant to which the food item belongs.
     * @param myRestaurantLayout  The MyRestaurantLayout instance to update when a food item is removed.
     */
    public LabelMyRestaurantFood(Food food, Restaurant restaurant, MyRestaurantLayout myRestaurantLayout) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(180,180,180));

        // Label to display the food name
        JLabel name = new JLabel(food.name);
        name.setFont(new Font("Arial", Font.BOLD,15));
        name.setBounds(30, 0, 270, 50);
        this.add(name);

        // Label to display the food price
        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(340, 0, 100, 50);
        this.add(price);

        // Button to remove the food item from the restaurant's menu
        JButton button = new JButton("-");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 0, 50, 50);
        button.setBackground(new Color(180,180,180));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            restaurant.removeFood(food.id);
            myRestaurantLayout.createComponents();
        });

        this.add(button);
    }
}
