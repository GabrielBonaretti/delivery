package src.UI.Subpage;

import src.Entities.Application;
import src.Entities.Food;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Components.Title;
import src.UI.Layout.LabelOrderLayoutFood;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The OrderLayout class represents a page for managing the user's order in the application.
 * You can check the quantity of items in the cart, remove them and complete the purchase.
 */
public class OrderLayout extends JPanel {
    // Fields for managing delivery and application instances
    public Delivery delivery;
    public Application application;

    /**
     * Constructs an OrderLayout with the specified delivery and application instances.
     *
     * @param delivery    The Delivery instance associated with this layout.
     * @param application The Application instance containing the user and order data.
     */
    public OrderLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setOpaque(true);
        this.setLayout(null);

        // Initialize the layout by creating order requests
        createRequests();
    }

    /**
     * Creates and adds UI components for the order requests section.
     */
    public void createRequests() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        // Create and add title for the order requests section
        Title title = new Title("Carrinho", 500);
        this.add(title);

        Line line = new Line(160);
        this.add(line);

        // Check if the order cart is not empty
        if (!this.application.order.cart.isEmpty()) {
            // Create a panel for displaying order layout foods
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            // Iterate through items in the order cart and create corresponding labels
            for (ArrayList<Object> orderFood: this.application.order.cart) {
                Food food = (Food) orderFood.get(0);
                int quantityFood = (int) orderFood.get(1);

                LabelOrderLayoutFood labelOrderLayoutFood = new LabelOrderLayoutFood(
                        application,
                        food,
                        quantityFood,
                        orderFood,
                        this
                );

                panel.add(labelOrderLayoutFood);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            // Configure and add a scroll pane for the panel
            panel.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 190, 500, 400);
            this.add(scrollPane);
        } else {
            // Display a message if there are no items in the order cart
            NoItemsText noItemsText = new NoItemsText("Não há items no carrinho!");
            this.add(noItemsText);
        }

        Line line2 = new Line(620);
        this.add(line2);

        // Calculate and display the total price of items in the order cart
        double totalPriceValue = application.order.getSumValues();
        JLabel totalPrice = new JLabel("Total price: "+ String.format("%.2f", totalPriceValue));
        totalPrice.setBounds(125, 650, 500, 40);
        totalPrice.setFont(new Font("Arial", Font.BOLD,15));
        this.add(totalPrice);

        // Create and configure a button for placing an orde
        JButton doOrder = new JButton("Fazer pedido");
        doOrder.setBounds(475, 650, 150, 40);
        doOrder.setFont(new Font("Arial", Font.BOLD,15));
        doOrder.setEnabled(!application.order.cart.isEmpty());
        doOrder.addActionListener(e -> {
            // Get the current date and time
            Date data = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat time = DateFormat.getTimeInstance();
            String dataFormated = sdf.format(data) + " " + time.format(data);

            // Print order
            application.order.printOrder(dataFormated, totalPriceValue);

            // Save the order with the current date, time, and total price
            application.order.saveOrder(dataFormated, totalPriceValue);

            // Refresh the order requests section
            createRequests();

        });

        this.add(doOrder);
    }

}
