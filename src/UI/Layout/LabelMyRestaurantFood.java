package src.UI.Layout;

import src.Entities.Lanche;
import src.Entities.Restaurante;
import src.UI.Subpage.MyRestaurantLayout;

import javax.swing.*;
import java.awt.*;

public class LabelMyRestaurantFood extends JLabel {
    public LabelMyRestaurantFood(Lanche lanche, Restaurante restaurante, MyRestaurantLayout myRestaurantLayout) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(180,180,180));


        JLabel nome = new JLabel(lanche.nome);
        nome.setFont(new Font("Arial", Font.BOLD,15));
        nome.setBounds(30, 0, 270, 50);
        this.add(nome);

        JLabel preco = new JLabel("R$ "+lanche.preco);
        preco.setFont(new Font("Arial", Font.BOLD,15));
        preco.setBounds(340, 0, 100, 50);
        this.add(preco);

        JButton button = new JButton("-");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 0, 50, 50);
        button.setBackground(new Color(180,180,180));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            restaurante.removerLanche(lanche.id);
            myRestaurantLayout.createComponents();
        });

        this.add(button);
    }
}
