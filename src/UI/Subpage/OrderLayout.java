package src.UI.Subpage;

import src.Entities.Application;
import src.Entities.Food;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Components.Title;
import src.UI.Layout.LabelOrderLayoutFood;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderLayout extends JPanel {
    public Delivery delivery;
    public Application application;
    public OrderLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setOpaque(true);
        this.setLayout(null);

        createRequests();
    }

    public void createRequests() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        Title title = new Title("Carrinho", 500);
        this.add(title);

        Line line = new Line(160);
        this.add(line);


        if (!this.application.order.carrinho.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (ArrayList<Object> pedidoLanche: this.application.order.carrinho) {
                Food food = (Food) pedidoLanche.get(0);
                int qntLanche = (int) pedidoLanche.get(1);

                LabelOrderLayoutFood labelOrderLayoutFood = new LabelOrderLayoutFood(
                        application,
                        food,
                        qntLanche,
                        pedidoLanche,
                        this
                );

                panel.add(labelOrderLayoutFood);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            panel.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 190, 500, 400);
            this.add(scrollPane);
        } else {
            NoItemsText noItemsText = new NoItemsText("Não há items no carrinho!");
            this.add(noItemsText);
        }

        Line line2 = new Line(620);
        this.add(line2);

        double totalPriceValue = application.order.getSumValues();
        JLabel totalPrice = new JLabel("Total price: "+ totalPriceValue);
        totalPrice.setBounds(125, 650, 500, 40);
        totalPrice.setFont(new Font("Arial", Font.BOLD,15));
        this.add(totalPrice);

        JButton doOrder = new JButton("Fazer pedido");
        doOrder.setBounds(475, 650, 150, 40);
        doOrder.setFont(new Font("Arial", Font.BOLD,15));
        doOrder.setEnabled(!application.order.carrinho.isEmpty());
        doOrder.addActionListener(e -> {
            Date data = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            DateFormat hora = DateFormat.getTimeInstance();
            String dataFormated = sdf.format(data) + " " + hora.format(data);

            application.order.saveOrder(dataFormated, totalPriceValue);

            createRequests();
        });

        this.add(doOrder);
    }

}
