package src.UI.Layout;

import src.Entities.Food;
import src.Entities.Restaurant;
import src.UI.Subpage.MyRestaurantLayout;

import javax.swing.*;
import java.awt.*;

public class LabelMyRestaurantFood extends JLabel {
    public LabelMyRestaurantFood(Food food, Restaurant restaurant, MyRestaurantLayout myRestaurantLayout) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(180,180,180));


        JLabel nome = new JLabel(food.nome);
        nome.setFont(new Font("Arial", Font.BOLD,15));
        nome.setBounds(30, 0, 270, 50);
        this.add(nome);

        JLabel preco = new JLabel("R$ "+ food.preco);
        preco.setFont(new Font("Arial", Font.BOLD,15));
        preco.setBounds(340, 0, 100, 50);
        this.add(preco);

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
