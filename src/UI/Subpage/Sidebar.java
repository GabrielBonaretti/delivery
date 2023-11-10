package src.UI.Subpage;

import src.UI.Components.ButtonSideBar;
import src.UI.Pages.Delivery;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The sideBar is for navigating between screens and logging out the user
 * */
public class Sidebar extends JPanel {
    // ArrayList to store sidebar buttons
    private final ArrayList<ButtonSideBar> listButton = new ArrayList<ButtonSideBar>();

    // Instances of Delivery and Screen
    public Delivery delivery;
    public Screen screen;

    /**
     * Constructs a Sidebar with the specified delivery and screen instances.
     *
     * @param delivery The Delivery instance associated with this sidebar.
     * @param screen   The Screen instance containing the application's main logic.
     */
    public Sidebar(Delivery delivery, Screen screen) {
        this.setBounds(0, 0, 250, 800);
        this.setBackground(new Color(200, 200, 200));
        this.setOpaque(true);
        this.setLayout(null);
        this.delivery = delivery;
        this.screen = screen;
        createComponents(false);
    }

    // Method to reset the background color of all buttons
    public void clearButtonColor() {
        for (ButtonSideBar button : listButton) {
            button.setBackground(new Color(200, 200, 200));
        }
    }

    /**
     * Creates and adds sidebar buttons based on the user type.
     *
     * @param isRestaurante Indicates whether the user is a restaurant owner.
     */
    public void createComponents(boolean isRestaurante) {
        this.removeAll();
        this.repaint();
        this.revalidate();

        ButtonSideBar buttonExit;
        ButtonSideBar buttonMyRestaurant;
        ButtonSideBar buttonListRestaurant;
        ButtonSideBar buttonCart;
        ButtonSideBar buttonHistory;

        // Check if the user is a restaurant owner
        if (isRestaurante) {
            // Create button for "My Restaurant"
            buttonMyRestaurant = new ButtonSideBar("Meu restaurante", 0, "src/Resources/loja.png", 2);
            buttonMyRestaurant.setBackground(new Color(170, 170, 170));

            // Create button for "Exit"
            buttonExit = new ButtonSideBar("Sair", 40, "src/Resources/logout.png", 4);

            // Add buttons to the list and panel
            listButton.add(buttonMyRestaurant);
            listButton.add(buttonExit);
            this.add(buttonMyRestaurant);
        } else {
            // Create buttons for "List Restaurants," "Cart," "History," and "Exit"
            buttonListRestaurant = new ButtonSideBar("Listar restaurantes", 0, "src/Resources/lista.png", 0);
            buttonCart = new ButtonSideBar("Carrinho", 40, "src/Resources/cart.png", 1);
            buttonHistory = new ButtonSideBar("Historico", 80, "src/Resources/history.png", 3);
            buttonExit = new ButtonSideBar("Sair", 120, "src/Resources/logout.png", 4);

            // Add buttons to the list and panel
            listButton.add(buttonListRestaurant);
            listButton.add(buttonCart);
            listButton.add(buttonHistory);
            listButton.add(buttonExit);
            this.add(buttonListRestaurant);
            this.add(buttonHistory);
            this.add(buttonCart);
        }
        this.add(buttonExit);

        // Set up action listeners for each button
        for (ButtonSideBar button : listButton) {
            button.addActionListener(e -> {
                clearButtonColor();
                button.setBackground(new Color(170, 170, 170));

                // Perform actions based on the selected button
                switch (button.buttonChoice) {
                    case 0:
                        // Show list of restaurants
                        showListRestaurantLayout();
                        break;
                    case 1:
                        // Show order layout (cart)
                        showOrderLayout();
                        break;
                    case 2:
                        // Show user's restaurant layout
                        showMyRestaurantLayout();
                        break;
                    case 3:
                        // Show historic layout
                        showHistoricLayout();
                        break;
                    case 4:
                        // Logout and return to login screen
                        logout();
                        break;
                }
            });
        }
    }

    // Methods to show different layouts based on button selection
    private void showListRestaurantLayout() {
        this.delivery.listRestaurantLayout.setVisible(true);
        this.delivery.orderLayout.setVisible(false);
        this.delivery.myRestaurantLayout.setVisible(false);
        this.delivery.historicLayout.setVisible(false);
        this.delivery.specificOrderLayout.setVisible(false);
        if (this.delivery.restaurantSpecificPage != null) {
            this.delivery.restaurantSpecificPage.setVisible(false);
        }
    }

    private void showOrderLayout() {
        this.delivery.listRestaurantLayout.setVisible(false);
        this.delivery.orderLayout.setVisible(true);
        this.delivery.myRestaurantLayout.setVisible(false);
        this.delivery.historicLayout.setVisible(false);
        this.delivery.specificOrderLayout.setVisible(false);
        if (this.delivery.restaurantSpecificPage != null) {
            this.delivery.restaurantSpecificPage.setVisible(false);
        }
    }

    private void showMyRestaurantLayout() {
        this.delivery.listRestaurantLayout.setVisible(false);
        this.delivery.orderLayout.setVisible(false);
        this.delivery.myRestaurantLayout.setVisible(true);
        this.delivery.historicLayout.setVisible(false);
        this.delivery.specificOrderLayout.setVisible(false);
        if (this.delivery.restaurantSpecificPage != null) {
            this.delivery.restaurantSpecificPage.setVisible(false);
        }
    }

    private void showHistoricLayout() {
        this.delivery.listRestaurantLayout.setVisible(false);
        this.delivery.orderLayout.setVisible(false);
        this.delivery.myRestaurantLayout.setVisible(false);
        this.delivery.historicLayout.setVisible(true);
        this.delivery.historicLayout.createComponents();
        this.delivery.specificOrderLayout.setVisible(false);
        if (this.delivery.restaurantSpecificPage != null) {
            this.delivery.restaurantSpecificPage.setVisible(false);
        }
    }

    private void logout() {
        this.screen.delivery.setVisible(false);
        this.screen.login.setVisible(true);
        this.screen.application.order.cart.clear();
        this.delivery.orderLayout.createRequests();
    }
}

