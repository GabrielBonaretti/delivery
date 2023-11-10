package src.UI.Layout;

import src.Entities.Application;
import src.Entities.Food;
import src.UI.Subpage.OrderLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The LabelOrderLayoutFood class represents a labeled display for a food item in the OrderLayout.
 * It includes the food name, quantity, price, and a button to decrease the quantity or remove the item.
 */
public class LabelOrderLayoutFood extends JLabel {

    /**
     * Constructs a LabelOrderLayoutFood object with the specified parameters.
     *
     * @param application   The Application instance.
     * @param food          The Food instance representing the food item.
     * @param quantityFood  The quantity of the food item in the order.
     * @param itemOrder     The ArrayList<Object> representing the food item in the order.
     * @param orderLayout   The OrderLayout instance.
     */
    public LabelOrderLayoutFood(
            Application application,
            Food food,
            int quantityFood,
            ArrayList<Object> itemOrder,
            OrderLayout orderLayout
    ) {
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

        // Label to display the quantity of the food item in the order
        JLabel quantity = new JLabel("Qnt: "+quantityFood);
        quantity.setFont(new Font("Arial", Font.BOLD,15));
        quantity.setBounds(250, 0, 50, 50);
        this.add(quantity);

        // Label to display the price of the food item
        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(340, 0, 100, 50);
        this.add(price);

        // Button to decrease the quantity or remove the food item from the order
        JButton button = new JButton("-");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 1, 50, 48);
        button.setBackground(new Color(240,240,240));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            int qnt = (int) itemOrder.get(1);
            if (qnt > 1) {
                itemOrder.set(1,  qnt - 1);
            } else {
                // Remove the food item from the order if the quantity is 1
                application.order.cart.remove(itemOrder);
            }
            // Update the OrderLayout to reflect changes
            orderLayout.createRequests();
        });
        this.add(button);
    }
}
