package src.UI.Subpage;

import src.Entities.Application;
import src.Entities.Restaurant;
import src.UI.Components.Line;
import src.UI.Layout.RestaurantLabel;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The HistoricLayout class represents the list of available restaurants in the application.
 * It displays a list of available restaurants in the application.
 */
public class ListRestaurantLayout extends JPanel {
    // Fields for managing restaurant lists and UI components
    private ArrayList<Restaurant> listRestaurants = new ArrayList<Restaurant>();
    private ArrayList<Restaurant> listFilteredRestaurants = new ArrayList<Restaurant>();
    private JLabel restaurantsListLabel;
    private JButton prev;
    private JButton next;
    private int countPage;
    public Delivery delivery;
    public Application application;

    /**
     * Constructs a ListRestaurantLayout with the specified delivery and application instances.
     *
     * @param delivery The Delivery instance associated with this layout.
     * @param application The Application instance containing the restaurant data.
     */
    public ListRestaurantLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    /**
     * Sets the restaurants to be displayed on the current page.
     */
    public void setRestaurants() {
        // Iterate through restaurants and create corresponding labels
        for(int i = 0; i < 6; i++) {
            try {
                Restaurant restaurant = listFilteredRestaurants.get(i + 6*countPage);
                RestaurantLabel restaurantLabel = new RestaurantLabel(restaurant, i*70, this.delivery);
                restaurantsListLabel.add(restaurantLabel);
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Verifies the state of navigation buttons and enables/disables accordingly.
     */
    public void verifyButtons() {
        // Check conditions to enable/disable navigation buttons
        if (countPage > 0 && listFilteredRestaurants.toArray().length / 6 != countPage) {
            prev.setEnabled(true);
            next.setEnabled(true);
        } else if (countPage > 0 && listFilteredRestaurants.toArray().length / 6 == countPage) {
            prev.setEnabled(true);
            next.setEnabled(false);
        } else if (countPage == 0 && listFilteredRestaurants.toArray().length / 6 != countPage) {
            prev.setEnabled(false);
            next.setEnabled(true);
        } else {
            prev.setEnabled(false);
            next.setEnabled(false);
        }

    }

    /**
     * Searches for restaurants based on the provided text input.
     *
     * @param textInput The text input used for searching restaurants.
     */
    public void searchList(String textInput) {
        // Reset page count and clear filtered restaurants
        countPage = 0;
        listFilteredRestaurants.clear();

        // Count and filter restaurants based on input
        int count = 0;
        for (Restaurant restaurant : listRestaurants) {
            if (restaurant.name.contains(textInput)) {
                count ++;
                listFilteredRestaurants.add(restaurant);
            }
        }

        // Update UI based on search results
        if (count > 0) {
            updateListLabel();
        } else {
            restaurantsListLabel.removeAll();
            restaurantsListLabel.revalidate();
            restaurantsListLabel.repaint();

            // Display message if no restaurants are found
            JLabel dontHaveRestaurant = new JLabel("Não foi encontrado nenhum restaurante!");
            dontHaveRestaurant.setFont(new Font("Arial", Font.BOLD,20));
            dontHaveRestaurant.setHorizontalAlignment(SwingConstants.CENTER);
            dontHaveRestaurant.setBounds(0,0,500,410);
            restaurantsListLabel.add(dontHaveRestaurant);

            verifyButtons();
        }
    }

    /**
     * Updates the restaurant list label with the current set of restaurants.
     */
    public void updateListLabel() {
        restaurantsListLabel.removeAll();
        restaurantsListLabel.revalidate();
        restaurantsListLabel.repaint();
        setRestaurants();
        verifyButtons();
    }

    /**
     * Creates and adds UI components for the list of restaurants.
     */
    public void createComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        // Initialize listRestaurants with application's restaurant data
        this.listRestaurants = application.restaurants;

        // Initialize listFilteredRestaurants with a copy of listRestaurants
        listFilteredRestaurants = (ArrayList<Restaurant>) new ArrayList<>(listRestaurants);

        // Input field for restaurant search
        JTextField inputSearch = new JTextField();
        inputSearch.setBounds(225,90,250,40);

        // Search button with associated action listener
        JButton search = new JButton();
        search.setBounds(485, 90, 40, 40);
        search.addActionListener(e -> searchList(inputSearch.getText()));
        ImageIcon imageButton = new ImageIcon("src/Resources/search.png");
        imageButton.setImage(imageButton.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
        search.setIcon(imageButton);
        search.setFocusable(false);

        Line line = new Line(160);

        // Label for displaying the list of restaurants
        restaurantsListLabel = new JLabel();
        restaurantsListLabel.setBounds(125, 190, 600, 600);

        // Set the initial list of restaurants
        setRestaurants();

        // Navigation buttons for paging through restaurant list
        prev = new JButton("<");
        prev.setFont(new Font("Arial", Font.BOLD,25));
        prev.setBounds(320, 620, 50, 50);
        prev.setFocusable(false);
        prev.addActionListener(e -> {
            countPage --;
            updateListLabel();
        });

        next = new JButton(">");
        next.setFont(new Font("Arial", Font.BOLD,25));
        next.setBounds(380, 620, 50, 50);
        next.addActionListener(e -> {
            countPage ++;
            updateListLabel();
        });

        // Verify and set the initial state of navigation buttons
        verifyButtons();

        // Add components to the layout
        this.add(restaurantsListLabel);
        this.add(search);
        this.add(inputSearch);
        this.add(line);
        this.add(prev);
        this.add(next);
    }
}
