package org.delivery.UI.Pages;

import org.delivery.Entities.Order;
import org.delivery.Entities.User;
import org.delivery.UI.Screen;
import org.delivery.UI.Subpage.*;

import javax.swing.*;
import java.awt.*;

/**
 * The Delivery class represents the main delivery page containing subpages for different functionalities.
 * It includes a sidebar for navigation and subpages for listing restaurants, managing orders, and more.
 */
public class Delivery extends JPanel {
    public Sidebar sidebar;
    public ListRestaurantLayout listRestaurantLayout;
    public OrderLayout orderLayout;
    public MyRestaurantLayout myRestaurantLayout;
    public RestaurantSpecificPage restaurantSpecificPage;
    public HistoricLayout historicLayout;
    public SpecificOrderLayout specificOrderLayout;

    /**
     * Constructs a Delivery object with the specified screen instance.
     *
     * @param screen The Screen instance containing the application's main logic.
     */
    public Delivery(Screen screen) {
        this.setBounds(0,0, 1000, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);

        // Initialize the order for the current session
        screen.application.order = new Order();

        // Create and add the sidebar for navigation
        this.sidebar = new Sidebar(this, screen);
        this.add(sidebar);

        // Create and add subpages with associated layouts:

        // 1. ListRestaurantLayout for displaying a list of available restaurants
        this.listRestaurantLayout = new ListRestaurantLayout(this, screen.application);
        this.add(listRestaurantLayout);
        this.listRestaurantLayout.setVisible(false);

        // 2. OrderLayout for managing the user's order
        this.orderLayout = new OrderLayout(this, screen.application);
        this.add(orderLayout);
        this.listRestaurantLayout.setVisible(false);

        // 3. MyRestaurantLayout for managing a restaurant (if user owns a restaurant)
        this.myRestaurantLayout = new MyRestaurantLayout(this, screen.application);
        this.add(myRestaurantLayout);
        this.listRestaurantLayout.setVisible(false);

        // 4. RestaurantSpecificPage for viewing details of a specific restaurant
        this.restaurantSpecificPage = new RestaurantSpecificPage(this, screen.application);
        this.add(restaurantSpecificPage);
        this.restaurantSpecificPage.setVisible(false);

        // 5. HistoricLayout for viewing the user's order history
        this.historicLayout = new HistoricLayout(this, screen.application);
        this.add(historicLayout);
        this.historicLayout.setVisible(false);

        // 6. SpecificOrderLayout for viewing details of a specific order in historic orders
        this.specificOrderLayout = new SpecificOrderLayout(this, screen.application);
        this.add(specificOrderLayout);
        this.specificOrderLayout.setVisible(false);
    }
}
