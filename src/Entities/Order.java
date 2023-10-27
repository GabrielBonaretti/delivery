package src.Entities;

import src.Database.Database;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Order {
    public Restaurant restaurant;
    public User user;
    public ArrayList<ArrayList<Object>> cart = new ArrayList<ArrayList<Object>>();

    public void setUser(User user) {
        this.user = user;
    }

    public void doOrder(Food food) {
        boolean alreadyExists = false;
        if (cart.toArray().length != 0) {
            for (ArrayList<Object> itemOrder: cart) {
                Food foodOrder = (Food) itemOrder.get(0);
                if (Objects.equals(foodOrder.name, food.name)) {
                    int quantidade = (int) itemOrder.get(1);
                    itemOrder.set(1, quantidade + 1);
                    alreadyExists = true;
                }
            }
        }

        if (!alreadyExists) {
            ArrayList<Object> foodOrder = new ArrayList<Object>();
            foodOrder.add(food);
            foodOrder.add(1);
            this.cart.add(foodOrder);
        }
    }

    public double getSumValues() {
        double sumTotal = 0;
        if (cart.toArray().length != 0) {
            for (ArrayList<Object> foodOrder: cart) {
                Food food = (Food) foodOrder.get(0);
                int amount = (int) foodOrder.get(1);
                sumTotal += food.price * amount;
            }
        }
        return sumTotal;
    }

    public void saveOrder(String date, double totalPrice) {
        Database database = new Database();
        database.addOrder(user.id, date, totalPrice);
        int idLastOrder = database.getLastOrder();

        for (ArrayList<Object> foodOrder: cart) {
            Food food = (Food) foodOrder.get(0);
            int amount = (int) foodOrder.get(1);
            database.setOrderFood(idLastOrder, food.id, amount);
        }

        cart.clear();
        JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso");
    }

    public void printOrder() {

    }
}
