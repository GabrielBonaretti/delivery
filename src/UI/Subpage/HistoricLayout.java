package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Lanche;
import src.Entities.Order;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Components.Title;
import src.UI.Layout.LabelOrder;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HistoricLayout extends JPanel {
    public Delivery delivery;
    public HistoricLayout(Delivery delivery) {
        this.delivery = delivery;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
        createComponents();
    }

    public void createComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        Title title = new Title("Historico", 200);
        this.add(title);

        Line line = new Line(160);
        this.add(line);

        Database database = new Database();
        ArrayList<Order> allOrders = database.getAllOrders(delivery.id);

        if (!allOrders.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (Order order: allOrders) {
                LabelOrder labelOrder = new LabelOrder(delivery, order);

                panel.add(labelOrder);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.setBounds(125, 200, 500, 500);
            this.add(scrollPane);
        } else {
            NoItemsText noItems = new NoItemsText("Não há pedidos no restaurante!");
            this.add(noItems);
        }
    }
}
