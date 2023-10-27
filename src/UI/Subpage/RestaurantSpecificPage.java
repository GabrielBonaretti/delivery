package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Lanche;
import src.Entities.Restaurante;
import src.UI.Layout.LancheLabel;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantSpecificPage extends JPanel {
    public Delivery delivery;

    public RestaurantSpecificPage(Delivery delivery) {
        this.delivery = delivery;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    public void createComponents(Restaurante restaurante) {
        this.removeAll();
        this.revalidate();
        this.repaint();

        restaurante.setListaLanches();

        JLabel label = new JLabel(restaurante.nome);
        label.setBounds(125,90,500,40);
        label.setFont(new Font("Arial", Font.BOLD,30));
        this.add(label);

        JLabel linha = new JLabel();
        linha.setBounds(125, 160, 500, 1);
        linha.setBackground(new Color(180,180,180));
        linha.setOpaque(true);
        this.add(linha);

        if (restaurante.listaLanches.toArray().length > 0) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setVisible(true);

            for (Lanche lanche: restaurante.listaLanches) {
                LancheLabel lancheLabel = new LancheLabel(lanche, delivery, restaurante);
                lancheLabel.setVisible(true);
                panel.add(lancheLabel);

                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
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
