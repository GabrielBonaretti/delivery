package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Application;
import src.Entities.Food;
import src.Entities.OrderBank;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Layout.LabelSpecificOrder;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The SpecificOrderLayout class represents a page for viewing details of a specific order in historic orders in the application.
 * see the specific old order you placed, along with its details.
 */
public class SpecificOrderLayout extends JPanel {
    // OrderBank instance representing the specific order
    public OrderBank orderBank;
    public Delivery delivery;
    public Application application;

    /**
     * Constructs a SpecificOrderLayout with the specified delivery and application instances.
     *
     * @param delivery    The Delivery instance associated with this specific order layout.
     * @param application The Application instance containing the main logic of the application.
     */
    public SpecificOrderLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    /**
     * Sets the OrderBank instance representing the specific order.
     *
     * @param orderBank The OrderBank instance representing the specific order.
     */
    public void setOrder(OrderBank orderBank) {
        this.orderBank = orderBank;
    }

    /**
     * Creates and displays components for the specific order layout.
     */
    public void createComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        JLabel label = new JLabel(orderBank.id + " | " + orderBank.date);
        label.setBounds(125,90,500,40);
        label.setFont(new Font("Arial", Font.BOLD,30));
        this.add(label);

        Line line = new Line(160);
        this.add(line);

        Database database = new Database();
        ArrayList<ArrayList<Object>> listItemsInOrder = database.getSpecificOrder(orderBank.id);
        String restaurantName = database.getRestaurantOrder(orderBank.id);

        if (!listItemsInOrder.isEmpty()) {
            double totalPriceValue = 0;

            JPanel panel = new JPanel();
            panel.setBackground(new Color(240, 240, 240));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (ArrayList<Object> orderFood : listItemsInOrder) {
                Food food = (Food) orderFood.get(0);
                int qntLanche = (int) orderFood.get(1);
                totalPriceValue += food.price * qntLanche;

                LabelSpecificOrder labelSpecificOrder = new LabelSpecificOrder(food, qntLanche);

                panel.add(labelSpecificOrder);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);

            scrollPane.setBounds(125, 190, 500, 400);
            this.add(scrollPane);

            Line line2 = new Line(620);
            this.add(line2);

            JLabel restaurant = new JLabel("Restaurant: "+ restaurantName,  SwingConstants.LEFT);
            restaurant.setBounds(125, 650, 500, 40);
            restaurant.setFont(new Font("Arial", Font.BOLD,17));
            this.add(restaurant);

            JLabel totalPrice = new JLabel("Total price: "+ totalPriceValue,  SwingConstants.RIGHT);
            totalPrice.setBounds(125, 650, 500, 40);
            totalPrice.setFont(new Font("Arial", Font.BOLD,17));
            this.add(totalPrice);
        } else {
            NoItemsText noItemsText = new NoItemsText("Não há items no carrinho!");
            this.add(noItemsText);
        }
    }
}
