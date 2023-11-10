package src.UI.Layout;

import src.Entities.OrderBank;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;

/**
 * The LabelOrder class represents a labeled display for an order in the HistoricLayout.
 * It includes the order ID, date, total price, and a button to view details of the order.
 */
public class LabelOrder extends JLabel {

    /**
     * Constructs a LabelOrder object with the specified Delivery instance and OrderBank.
     *
     * @param delivery   The Delivery instance.
     * @param orderBank  The OrderBank instance representing the order.
     */
    public LabelOrder(Delivery delivery, OrderBank orderBank) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(180,180,180));;

        // Label to display the order ID
        JLabel name = new JLabel(String.valueOf(orderBank.id));
        name.setFont(new Font("Arial", Font.BOLD,15));
        name.setBounds(30, 0, 25, 50);
        this.add(name);

        // Label to display the order date
        JLabel date = new JLabel(orderBank.date);
        date.setFont(new Font("Arial", Font.BOLD,15));
        date.setBounds(80, 0, 200, 50);
        this.add(date);

        // Label to display the total price of the order
        JLabel price = new JLabel("Preço total: R$ "+ orderBank.totalPrice);
        price.setFont(new Font("Arial", Font.BOLD,15));
        price.setBounds(250, 0, 200, 50);
        this.add(price);

        // Button to view details of the order
        JButton button = new JButton("...");
        button.setFont(new Font("Arial", Font.BOLD,15));
        button.setBounds(440, 10, 50, 30);
        button.addActionListener(e -> {
            // Hide the historic layout, set the order for the specific order layout, and display it
            delivery.historicLayout.setVisible(false);
            delivery.specificOrderLayout.setOrder(orderBank);
            delivery.specificOrderLayout.createComponents();
            delivery.specificOrderLayout.setVisible(true);
        });
        this.add(button);
    }
}
