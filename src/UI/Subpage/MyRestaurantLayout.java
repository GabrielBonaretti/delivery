package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Application;
import src.Entities.Food;
import src.Entities.Restaurant;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Components.Title;
import src.UI.Layout.LabelMyRestaurantFood;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;

/**
 * The MyRestaurantLayout class represents a page for managing a restaurant (if user owns a restaurant) in the application.
 * You can create foods and delete foods with this name and prices.
 */
public class MyRestaurantLayout extends JPanel {
    // Fields for managing delivery, application, and UI components
    public Delivery delivery;
    public Application application;

    /**
     * Constructs a MyRestaurantLayout with the specified delivery and application instances.
     *
     * @param delivery    The Delivery instance associated with this layout.
     * @param application The Application instance containing the user and restaurant data.
     */
    public MyRestaurantLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    /**
     * Creates and adds UI components for the My Restaurant layout.
     */
    public void createComponents() {
        this.removeAll();
        this.revalidate();
        this.repaint();

        // Access the database to get restaurant details
        Database database = new Database();
        Restaurant restaurant = database.getRestaurant(this.application.user.id);
        restaurant.setId(this.application.user.id);
        restaurant.setListFoods();

        // Create and add title for the restaurant
        Title title = new Title(restaurant.name, 450);
        this.add(title);

        Line line1 = new Line(160);
        this.add(line1);

        // UI components for adding a new food item
        JLabel newFood = new JLabel("New Food: ");
        newFood.setBounds(125, 170, 100, 40);
        newFood.setFont(new Font("Arial", Font.BOLD,15));
        this.add(newFood);

        JLabel name = new JLabel("name ");
        name.setBounds(225, 170, 50, 40);
        name.setFont(new Font("Arial", Font.BOLD,15));
        this.add(name);

        JTextField nameFoodInput = new JTextField();
        nameFoodInput.setBounds(275, 170, 100, 40);
        nameFoodInput.setFont(new Font("Arial", Font.BOLD,15));
        this.add(nameFoodInput);

        JLabel price = new JLabel("price ");
        price.setBounds(400, 170, 50, 40);
        price.setFont(new Font("Arial", Font.BOLD,15));
        this.add(price);

        JTextField priceFoodInput = new JTextField();
        priceFoodInput.setBounds(450, 170, 100, 40);
        priceFoodInput.setFont(new Font("Arial", Font.BOLD,15));
        this.add(priceFoodInput);

        JButton addFood = new JButton("+");
        addFood.setBounds(575, 170, 50, 40);
        addFood.addActionListener(e -> {
            try {
                // Get input values and create a new Food object
                String nameInput = nameFoodInput.getText();
                Double priceInput = Double.valueOf(priceFoodInput.getText());

                Food food = new Food(nameInput, priceInput);
                restaurant.addFood(food);
            } catch (Exception exception) {
                // Display an error message for incorrect input
                JOptionPane.showMessageDialog(null, "Fill in the fields correctly");
            }

            // Refresh the UI components after adding a new food item
            createComponents();
        });
        this.add(addFood);

        Line line2 = new Line(220);
        this.add(line2);

        // Check if the restaurant has any food items
        if (!restaurant.getListFoods().isEmpty()) {
            // Create a panel for displaying food items
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setVisible(true);

            // Iterate through food items and create corresponding labels
            for (Food food : restaurant.getListFoods()) {
                LabelMyRestaurantFood labelMyRestaurantFood = new LabelMyRestaurantFood(
                        food,
                        restaurant,
                        this
                );

                panel.add(labelMyRestaurantFood);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            // Create a scroll pane for the panel
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 230, 500, 400);
            this.add(scrollPane);
        } else {
            // Display a message if there are no food items in the restaurant
            NoItemsText noItemsText = new NoItemsText("Não há pedidos no restaurante!");
            this.add(noItemsText);
        }
    }
}
