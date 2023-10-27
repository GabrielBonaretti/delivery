package src.UI.Layout;

import src.Entities.Application;
import src.Entities.Food;
import src.Entities.Restaurant;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LancheLabel extends JLabel {
    public LancheLabel(Food food, Delivery delivery, Restaurant restaurant, Application application) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setBackground(new Color(240,240,240));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


        JLabel name = new JLabel(food.name);
        name.setFont(new Font("Arial", Font.BOLD,15));
        name.setBounds(30, 0, 270, 50);
        this.add(name);

        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(340, 0, 100, 50);
        this.add(price);

        JButton button = new JButton("+");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 1, 50, 48);
        button.setBackground(new Color(240,240,240));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            if (application.order.restaurant != null && !Objects.equals(application.order.restaurant.name, restaurant.name)) {
                application.order.restaurant = restaurant;
                application.order.cart.clear();
                delivery.orderLayout.createRequests();
            } else {
                application.order.restaurant = restaurant;
            }
            application.order.doOrder(food);
            delivery.orderLayout.createRequests();
        });
        this.add(button);
    }
}
