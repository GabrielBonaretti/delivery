package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Lanche;
import src.Entities.Order;
import src.UI.Components.NoItemsText;
import src.UI.Layout.LabelSpecificOrder;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpecificOrderLayout extends JPanel {
    public Order order;
    public Delivery delivery;
    public SpecificOrderLayout(Delivery delivery) {
        this.delivery = delivery;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void createComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        JLabel label = new JLabel(order.id + " | " + order.date);
        label.setBounds(125,90,500,40);
        label.setFont(new Font("Arial", Font.BOLD,30));
        this.add(label);

        JLabel linha = new JLabel();
        linha.setBounds(125, 160, 500, 1);
        linha.setBackground(new Color(180,180,180));
        linha.setOpaque(true);
        this.add(linha);

        Database database = new Database();
        ArrayList<ArrayList<Object>> listItemsInOrder = database.getSpecificOrder(order.id);

        if (!listItemsInOrder.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (ArrayList<Object> pedidoLanche: listItemsInOrder) {
                Lanche lanche = (Lanche) pedidoLanche.get(0);
                int qntLanche = (int) pedidoLanche.get(1);

                LabelSpecificOrder labelSpecificOrder = new LabelSpecificOrder(lanche, qntLanche);

                panel.add(labelSpecificOrder);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.setBounds(125, 190, 500, 400);
            this.add(scrollPane);
        } else {
            NoItemsText noItemsText = new NoItemsText("Não há items no carrinho!");
            this.add(noItemsText);
        }
    }
}
