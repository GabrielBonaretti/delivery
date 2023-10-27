package src.UI.Layout;

import src.Entities.Food;

import javax.swing.*;
import java.awt.*;

public class LabelSpecificOrder extends JLabel {

    public LabelSpecificOrder(Food food, int qntLanche) {
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

        JLabel quantity = new JLabel("Qnt: "+qntLanche);
        quantity.setFont(new Font("Arial", Font.BOLD,15));
        quantity.setBounds(300, 0, 50, 50);
        this.add(quantity);

        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(400, 0, 100, 50);
        this.add(price);
    }
}
