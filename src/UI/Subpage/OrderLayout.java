package src.UI.Subpage;

import src.Entities.Lanche;
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
    public OrderLayout(Delivery delivery) {
        this.delivery = delivery;
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


        if (!this.delivery.pedido.carrinho.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            for (ArrayList<Object> pedidoLanche: this.delivery.pedido.carrinho) {
                Lanche lanche = (Lanche) pedidoLanche.get(0);
                int qntLanche = (int) pedidoLanche.get(1);

                LabelOrderLayoutFood labelOrderLayoutFood = new LabelOrderLayoutFood(
                        lanche,
                        qntLanche,
                        pedidoLanche,
                        this.delivery,
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
            scrollPane.setBounds(125, 190, 500, 400);
            this.add(scrollPane);
        } else {
            NoItemsText noItemsText = new NoItemsText("Não há items no carrinho!");
            this.add(noItemsText);
        }

        Line line2 = new Line(620);
        this.add(line2);

        double totalPriceValue = delivery.pedido.getSumValues();
        JLabel totalPrice = new JLabel("Total price: "+ totalPriceValue);
        totalPrice.setBounds(125, 650, 500, 40);
        totalPrice.setFont(new Font("Arial", Font.BOLD,15));
        this.add(totalPrice);

        JButton doOrder = new JButton("Fazer pedido");
        doOrder.setBounds(475, 650, 150, 40);
        doOrder.setFont(new Font("Arial", Font.BOLD,15));
        doOrder.setEnabled(!delivery.pedido.carrinho.isEmpty());
        doOrder.addActionListener(e -> {
            Date data = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            DateFormat hora = DateFormat.getTimeInstance();
            String dataFormated = sdf.format(data) + " " + hora.format(data);

            delivery.pedido.saveOrder(delivery.id, dataFormated, totalPriceValue);

            createRequests();
        });

        this.add(doOrder);
    }

}
