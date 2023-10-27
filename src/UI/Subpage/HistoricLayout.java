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

public class HistoricLayout extends JPanel {
    public Delivery delivery;
    public Application application;
    public HistoricLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
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
        ArrayList<OrderBank> allOrderBanks = database.getAllOrders(application.user.id);

        if (!allOrderBanks.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (OrderBank orderBank : allOrderBanks) {
                LabelOrder labelOrder = new LabelOrder(delivery, orderBank);

                panel.add(labelOrder);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 200, 500, 500);
            this.add(scrollPane);
        } else {
            NoItemsText noItems = new NoItemsText("Não há pedidos no restaurante!");
            this.add(noItems);
        }
    }
}
