package src.Entities;

import src.Database.Database;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Pedido {
    public Restaurante restaurante;
    public ArrayList<ArrayList<Object>> carrinho = new ArrayList<ArrayList<Object>>();

    public void fazerPedido(Lanche lanche) {
        boolean alreadyExists = false;
        if (carrinho.toArray().length != 0) {
            for (ArrayList<Object> lanchePedido: carrinho) {
                Lanche teste = (Lanche) lanchePedido.get(0);
                if (Objects.equals(teste.nome, lanche.nome)) {
                    int quantidade = (int) lanchePedido.get(1);
                    lanchePedido.set(1, quantidade + 1);
                    alreadyExists = true;
                }
            }
        }

        if (!alreadyExists) {
            ArrayList<Object> lanchePedido = new ArrayList<Object>();
            lanchePedido.add(lanche);
            lanchePedido.add(1);
            this.carrinho.add(lanchePedido);
        }
    }

    public double getSumValues() {
        double sumTotal = 0;
        if (carrinho.toArray().length != 0) {
            for (ArrayList<Object> lanchePedido: carrinho) {
                Lanche lanche = (Lanche) lanchePedido.get(0);
                int amount = (int) lanchePedido.get(1);
                sumTotal += lanche.preco * amount;
            }
        }
        return sumTotal;
    }

    public void saveOrder(int idUser, String date, double totalPrice) {
        Database database = new Database();
        database.addOrder(idUser, date, totalPrice);
        int idLastOrder = database.getLastOrder();

        for (ArrayList<Object> lanchePedido: carrinho) {
            Lanche lanche = (Lanche) lanchePedido.get(0);
            int amount = (int) lanchePedido.get(1);
            database.setOrderFood(idLastOrder, lanche.id, amount);
        }

        carrinho.clear();
        JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso");
    }
}
