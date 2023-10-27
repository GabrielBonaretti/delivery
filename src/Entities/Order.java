package src.Entities;

import src.Database.Database;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Order {
    public Restaurant restaurant;
    public User user;
    public ArrayList<ArrayList<Object>> carrinho = new ArrayList<ArrayList<Object>>();

    public void setUser(User user) {
        this.user = user;
    }

    public void doOrder(Food food) {
        boolean alreadyExists = false;
        if (carrinho.toArray().length != 0) {
            for (ArrayList<Object> lanchePedido: carrinho) {
                Food teste = (Food) lanchePedido.get(0);
                if (Objects.equals(teste.nome, food.nome)) {
                    int quantidade = (int) lanchePedido.get(1);
                    lanchePedido.set(1, quantidade + 1);
                    alreadyExists = true;
                }
            }
        }

        if (!alreadyExists) {
            ArrayList<Object> lanchePedido = new ArrayList<Object>();
            lanchePedido.add(food);
            lanchePedido.add(1);
            this.carrinho.add(lanchePedido);
        }
    }

    public double getSumValues() {
        double sumTotal = 0;
        if (carrinho.toArray().length != 0) {
            for (ArrayList<Object> lanchePedido: carrinho) {
                Food food = (Food) lanchePedido.get(0);
                int amount = (int) lanchePedido.get(1);
                sumTotal += food.preco * amount;
            }
        }
        return sumTotal;
    }

    public void saveOrder(String date, double totalPrice) {
        Database database = new Database();
        System.out.println(this.user.id);
        database.addOrder(user.id, date, totalPrice);
        int idLastOrder = database.getLastOrder();

        for (ArrayList<Object> lanchePedido: carrinho) {
            Food food = (Food) lanchePedido.get(0);
            int amount = (int) lanchePedido.get(1);
            database.setOrderFood(idLastOrder, food.id, amount);
        }

        carrinho.clear();
        JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso");
    }

    public void printOrder() {

    }
}
