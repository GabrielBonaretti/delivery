package src.UI.Layout;

import src.Entities.OrderBank;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;

public class LabelOrder extends JLabel {
    public LabelOrder(Delivery delivery, OrderBank orderBank) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(180,180,180));;

        JLabel name = new JLabel(String.valueOf(orderBank.id));
        name.setFont(new Font("Arial", Font.BOLD,15));
        name.setBounds(30, 0, 25, 50);
        this.add(name);

        JLabel date = new JLabel(orderBank.date);
        date.setFont(new Font("Arial", Font.BOLD,15));
        date.setBounds(80, 0, 200, 50);
        this.add(date);

        JLabel price = new JLabel("Preço total: R$ "+ orderBank.totalPrice);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(250, 0, 200, 50);
        this.add(price);

        JButton button = new JButton("...");
        button.setFont(new Font("Arial", Font.BOLD,15));
        button.setBounds(440, 10, 50, 30);
        button.addActionListener(e -> {
            delivery.historicLayout.setVisible(false);
            delivery.specificOrderLayout.setOrder(orderBank);
            delivery.specificOrderLayout.createComponents();
            delivery.specificOrderLayout.setVisible(true);
        });
        this.add(button);
    }
}
