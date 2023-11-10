package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Application;
import src.Entities.OrderBank;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Components.Title;
import src.UI.Layout.LabelOrder;
import src.UI.Pages.Delivery;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The HistoricLayout class represents the user's order history layout in the application.
 * It displays a list of past orders made by the user.
 */
public class HistoricLayout extends JPanel {
    public Delivery delivery;
    public Application application;

    /**
     * Constructs a HistoricLayout object with the specified Delivery and Application instances.
     *
     * @param delivery     The Delivery instance representing the main delivery page.
     * @param application  The Application instance containing the main logic of the application.
     */
    public HistoricLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    /**
     * Creates and displays the components of the order history layout.
     * If there are past orders, it creates a scrollable list of orders; otherwise, it displays a message indicating no orders.
     */
    public void createComponents() {
        // Clear existing components
        this.removeAll();
        this.repaint();
        this.revalidate();

        // Create and add title
        Title title = new Title("Historico", 200);
        this.add(title);

        // Create and add separating line
        Line line = new Line(160);
        this.add(line);

        // Access the database to get all past orders of the user
        Database database = new Database();
        ArrayList<OrderBank> allOrderBanks = database.getAllOrders(application.user.id);

        // Check if there are any past orders
        if (!allOrderBanks.isEmpty()) {
            // If there are past orders, create a panel and a scroll pane
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            // Iterate over each past order and add a LabelOrder component
            for (OrderBank orderBank : allOrderBanks) {
                LabelOrder labelOrder = new LabelOrder(delivery, orderBank);

                panel.add(labelOrder);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            // Create and configure a scroll pane for the list of orders
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 200, 500, 500);
            this.add(scrollPane);
        } else {
            // If there are no past orders, display a message
            NoItemsText noItems = new NoItemsText("Não há pedidos no restaurante!");
            this.add(noItems);
        }
    }
}
