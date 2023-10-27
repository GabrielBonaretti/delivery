package src.UI.Layout;

import src.Entities.Application;
import src.Entities.Food;
import src.UI.Subpage.OrderLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LabelOrderLayoutFood extends JLabel {
    public LabelOrderLayoutFood(
            Application application,
            Food food,
            int qntLanche,
            ArrayList<Object> pedidoLanche,
            OrderLayout orderLayout
    ) {
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
        quantity.setBounds(250, 0, 50, 50);
        this.add(quantity);

        JLabel price = new JLabel("R$ "+ food.price);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(340, 0, 100, 50);
        this.add(price);

        JButton button = new JButton("-");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 1, 50, 48);
        button.setBackground(new Color(240,240,240));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            int qnt = (int) pedidoLanche.get(1);
            if (qnt > 1) {
                pedidoLanche.set(1,  qnt - 1);
            } else {
                application.order.cart.remove(pedidoLanche);
            }
            orderLayout.createRequests();
        });
        this.add(button);
    }
}
