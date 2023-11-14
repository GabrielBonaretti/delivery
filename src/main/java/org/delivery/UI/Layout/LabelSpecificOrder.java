package org.delivery.UI.Layout;

import org.delivery.Entities.Food;

import javax.swing.*;
import java.awt.*;

/**
 * The LabelSpecificOrder class represents a labeled display for a specific food item in a specificOrderLayout.
 * It includes the food name, quantity, and price.
 */
public class LabelSpecificOrder extends JLabel {

    /**
     * Constructs a LabelSpecificOrder object with the specified parameters.
     *
     * @param food       The Food instance representing the specific food item.
     * @param quantityParam   The quantity of the food item in the specific order.
     */
    public LabelSpecificOrder(Food food, int quantityParam) {
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

        // Label to display the quantity of the food item in the specific order
        JLabel quantityLabel = new JLabel("Qnt: "+quantityParam);
        quantityLabel.setFont(new Font("Arial", Font.BOLD,15));
        quantityLabel.setBounds(300, 0, 50, 50);
        this.add(quantityLabel);

        // Label to display the price of the food item
        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(400, 0, 100, 50);
        this.add(price);
    }
}
