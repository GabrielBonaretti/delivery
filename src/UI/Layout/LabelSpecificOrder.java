package src.UI.Layout;

import src.Entities.Food;

import javax.swing.*;
import java.awt.*;

public class LabelSpecificOrder extends JLabel {

    public LabelSpecificOrder(Food food, int qntLanche) {
        this.setPreferredSize(new Dimension(500, 50));
        this.setMinimumSize(new Dimension(500, 50));
        this.setMaximumSize(new Dimension(500, 50));
        this.setLayout(null);
        this.setBackground(new Color(240,240,240));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel nome = new JLabel(food.nome);
        nome.setFont(new Font("Arial", Font.BOLD,15));
        nome.setBounds(30, 0, 270, 50);
        this.add(nome);

        JLabel quantidade = new JLabel("Qnt: "+qntLanche);
        quantidade.setFont(new Font("Arial", Font.BOLD,15));
        quantidade.setBounds(300, 0, 50, 50);
        this.add(quantidade);

        JLabel preco = new JLabel("R$ "+ food.preco);
        preco.setFont(new Font("Arial", Font.BOLD,15));
        preco.setBounds(400, 0, 100, 50);
        this.add(preco);
    }
}
