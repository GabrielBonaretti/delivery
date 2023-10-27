package src.UI.Subpage;

import src.Entities.Application;
import src.Entities.Food;
import src.Entities.Restaurant;
import src.UI.Layout.LancheLabel;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;

public class RestaurantSpecificPage extends JPanel {
    public Delivery delivery;
    public Application application;
    public RestaurantSpecificPage(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    public void createComponents(Restaurant restaurant) {
        this.removeAll();
        this.revalidate();
        this.repaint();

        restaurant.setListLanches();

        JLabel label = new JLabel(restaurant.name);
        label.setBounds(125,90,500,40);
        label.setFont(new Font("Arial", Font.BOLD,30));
        this.add(label);

        JLabel linha = new JLabel();
        linha.setBounds(125, 160, 500, 1);
        linha.setBackground(new Color(180,180,180));
        linha.setOpaque(true);
        this.add(linha);

        if (!restaurant.getListLanches().isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setVisible(true);

            for (Food food : restaurant.getListLanches()) {
                LancheLabel lancheLabel = new LancheLabel(food, delivery, restaurant, application);
                lancheLabel.setVisible(true);
                panel.add(lancheLabel);

                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 190, 500, 500);
            this.add(scrollPane);
        } else {
            JLabel noItems = new JLabel("Não há pedidos no restaurante!", SwingConstants.CENTER);
            noItems.setBounds(225, 365, 300, 50);
            noItems.setFont(new Font("Arial", Font.BOLD,15));
            this.add(noItems);
        }


    }
}
