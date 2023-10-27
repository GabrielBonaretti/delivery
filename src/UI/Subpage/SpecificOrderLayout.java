package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Application;
import src.Entities.Food;
import src.Entities.OrderBank;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Layout.LabelSpecificOrder;
import src.UI.Pages.Delivery;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpecificOrderLayout extends JPanel {
    public OrderBank orderBank;
    public Delivery delivery;
    public Application application;
    public SpecificOrderLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    public void setOrder(OrderBank orderBank) {
        this.orderBank = orderBank;
    }

    public void createComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        JLabel label = new JLabel(orderBank.id + " | " + orderBank.date);
        label.setBounds(125,90,500,40);
        label.setFont(new Font("Arial", Font.BOLD,30));
        this.add(label);

        JLabel linha = new JLabel();
        linha.setBounds(125, 160, 500, 1);
        linha.setBackground(new Color(180,180,180));
        linha.setOpaque(true);
        this.add(linha);

        Database database = new Database();
        ArrayList<ArrayList<Object>> listItemsInOrder = database.getSpecificOrder(orderBank.id);

        if (!listItemsInOrder.isEmpty()) {
            double totalPriceValue = 0;

            JPanel panel = new JPanel();
            panel.setBackground(new Color(240, 240, 240));
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (ArrayList<Object> pedidoLanche: listItemsInOrder) {
                Food food = (Food) pedidoLanche.get(0);
                int qntLanche = (int) pedidoLanche.get(1);
                totalPriceValue += food.preco * qntLanche;

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
