package src.UI.Layout;

import src.Entities.Food;
import src.UI.Pages.Delivery;
import src.UI.Subpage.OrderLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LabelOrderLayoutFood extends JLabel {
    public LabelOrderLayoutFood(
            Food food,
            int qntLanche,
            ArrayList<Object> pedidoLanche,
            Delivery delivery,
            OrderLayout orderLayout
    ) {
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
        quantidade.setBounds(250, 0, 50, 50);
        this.add(quantidade);

        JLabel preco = new JLabel("R$ "+ food.preco);
        preco.setFont(new Font("Arial", Font.BOLD,15));
        preco.setBounds(340, 0, 100, 50);
        this.add(preco);

        JButton button = new JButton("-");
        button.setFont(new Font("Arial", Font.BOLD,20));
        button.setBounds(430, 1, 50, 48);
        button.setBackground(new Color(240,240,240));
        button.setBorder(null);
        button.setFocusable(false);
        button.addActionListener(e -> {
            int qnt = (int) pedidoLanche.get(1);
            if (qnt > 1) {
                pedidoLanche.set(1,  qnt - 1);
            } else {
                delivery.order.carrinho.remove(pedidoLanche);
            }
            orderLayout.createRequests();
        });
        this.add(button);
    }
}
