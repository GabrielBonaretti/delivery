package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Application;
import src.Entities.Food;
import src.Entities.Restaurant;
import src.UI.Components.Line;
import src.UI.Components.NoItemsText;
import src.UI.Components.Title;
import src.UI.Layout.LabelMyRestaurantFood;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class MyRestaurantLayout extends JPanel {
    public Delivery delivery;
    public Application application;
    public MyRestaurantLayout(Delivery delivery, Application application) {
        this.delivery = delivery;
        this.application = application;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    public void createComponents() {
        this.removeAll();
        this.revalidate();
        this.repaint();

        Database database = new Database();

        Restaurant restaurant = database.getRestaurant(this.application.user.id);
        restaurant.setId(this.application.user.id);
        restaurant.setListLanches();

        Title title = new Title(restaurant.nome, 450);
        this.add(title);

        Line line1 = new Line(160);
        this.add(line1);

        JLabel newFood = new JLabel("New Food: ");
        newFood.setBounds(125, 170, 100, 40);
        newFood.setFont(new Font("Arial", Font.BOLD,15));
        this.add(newFood);

        JLabel name = new JLabel("name ");
        name.setBounds(225, 170, 50, 40);
        name.setFont(new Font("Arial", Font.BOLD,15));
        this.add(name);

        JTextField nameFoodInput = new JTextField();
        nameFoodInput.setBounds(275, 170, 100, 40);
        nameFoodInput.setFont(new Font("Arial", Font.BOLD,15));
        this.add(nameFoodInput);

        JLabel price = new JLabel("price ");
        price.setBounds(400, 170, 50, 40);
        price.setFont(new Font("Arial", Font.BOLD,15));
        this.add(price);

        JTextField priceFoodInput = new JTextField();
        priceFoodInput.setBounds(450, 170, 100, 40);
        priceFoodInput.setFont(new Font("Arial", Font.BOLD,15));
        this.add(priceFoodInput);

        JButton addFood = new JButton("+");
        addFood.setBounds(575, 170, 50, 40);
        addFood.addActionListener(e -> {
            try {
                String nome = nameFoodInput.getText();
                Double preco = Double.valueOf(priceFoodInput.getText());

                Food food = new Food(nome, preco);
                restaurant.addFood(food);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Fill in the fields correctly");
            }

            createComponents();
        });
        this.add(addFood);

        Line line2 = new Line(220);
        this.add(line2);

        if (!restaurant.getListLanches().isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setVisible(true);

            for (Food food : restaurant.getListLanches()) {
                LabelMyRestaurantFood labelMyRestaurantFood = new LabelMyRestaurantFood(
                        food,
                        restaurant,
                        this
                );

                panel.add(labelMyRestaurantFood);
                panel.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(50);
            scrollPane.setBounds(125, 230, 500, 400);
            this.add(scrollPane);
        } else {
            NoItemsText noItemsText = new NoItemsText("Não há pedidos no restaurante!");
            this.add(noItemsText);
        }
    }
}
