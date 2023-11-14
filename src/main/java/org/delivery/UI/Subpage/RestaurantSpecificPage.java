package org.delivery.UI.Subpage;

import org.delivery.Entities.Application;
import org.delivery.Entities.Food;
import org.delivery.Entities.Restaurant;
import org.delivery.UI.Components.Line;
import org.delivery.UI.Layout.LancheLabel;
import org.delivery.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;

/**
 * The RestaurantSpecificPage class represents a page for viewing details of a specific restaurant in the application.
 * where you can check the available food and add it to the cart.
 */
public class RestaurantSpecificPage extends JPanel {
    // Fields for managing delivery and application instances
    public Delivery delivery;
    public Application application;

    /**
     * Constructs a RestaurantSpecificPage with the specified delivery and application instances.
     *
     * @param delivery    The Delivery instance associated with this page.
     * @param application The Application instance containing the user and restaurant data.
     */
    public RestaurantSpecificPage(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    /**
     * Creates and adds UI components for the restaurant-specific page.
     *
     * @param restaurant The restaurant for which the components are created.
     */
    public void createComponents(Restaurant restaurant) {
        this.removeAll();
        this.revalidate();
        this.repaint();

        // Ensure the restaurant has an updated list of foods
        restaurant.setListFoods();

        // Create and add a label for the restaurant name
        JLabel label = new JLabel(restaurant.name);
        label.setBounds(125,90,500,40);
        label.setFont(new Font("Arial", Font.BOLD,30));
        this.add(label);

        Line line = new Line(160);
        this.add(line);

        // Check if the restaurant has foods
        if (!restaurant.getListFoods().isEmpty()) {
            // Create a panel for displaying food labels
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setVisible(true);

            // Iterate through the foods and create corresponding labels
            for (Food food : restaurant.getListFoods()) {
                LancheLabel lancheLabel = new LancheLabel(food, delivery, restaurant, application);
                lancheLabel.setVisible(true);
                panel.add(lancheLabel);

                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            // Create and configure a scroll pane for the panel
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 190, 500, 500);
            this.add(scrollPane);
        } else {
            // Display a message if there are no foods in the restaurant
            JLabel noItems = new JLabel("Não há pedidos no restaurante!", SwingConstants.CENTER);
            noItems.setBounds(225, 365, 300, 50);
            noItems.setFont(new Font("Arial", Font.BOLD,15));
            this.add(noItems);
        }


    }
}
