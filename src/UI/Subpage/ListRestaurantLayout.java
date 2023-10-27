package src.UI.Subpage;

import src.Database.Database;
import src.Entities.Restaurant;
import src.UI.Components.Line;
import src.UI.Layout.RestauranteLabel;
import src.UI.Pages.Delivery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListRestaurantLayout extends JPanel {
    private ArrayList<Restaurant> listRestaurants = new ArrayList<Restaurant>();
    private ArrayList<Restaurant> listFilteredRestaurants = new ArrayList<Restaurant>();
    private JLabel restaurantsListLabel;
    private JButton prev;
    private JButton next;
    private int countPage;
    public Delivery delivery;

    public ListRestaurantLayout(Delivery delivery) {
        this.delivery = delivery;
        this.setBounds(250, 0, 750, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);
        this.setOpaque(true);
    }

    public void setRestaurants() {
        for(int i = 0; i < 6; i++) {
            try {
                Restaurant restaurant = listFilteredRestaurants.get(i + 6*countPage);
                RestauranteLabel restauranteLabel = new RestauranteLabel(restaurant, i*70, this.delivery);
                restaurantsListLabel.add(restauranteLabel);
            } catch (Exception e) {
                break;
            }
        }
    }

    public void verifyButtons() {
        if (countPage > 0 && listFilteredRestaurants.toArray().length / 6 != countPage) {
            prev.setEnabled(true);
            next.setEnabled(true);
        } else if (countPage > 0 && listFilteredRestaurants.toArray().length / 6 == countPage) {
            prev.setEnabled(true);
            next.setEnabled(false);
        } else if (countPage == 0 && listFilteredRestaurants.toArray().length / 6 != countPage) {
            prev.setEnabled(false);
            next.setEnabled(true);
        } else {
            prev.setEnabled(false);
            next.setEnabled(false);
        }

    }

    public void searchList(String textInput) {
        countPage = 0;
        listFilteredRestaurants.clear();

        int count = 0;
        for (Restaurant restaurant : listRestaurants) {
            if (restaurant.nome.contains(textInput)) {
                count ++;
                listFilteredRestaurants.add(restaurant);
            }
        }

        if (count > 0) {
            updateListLabel();
        } else {
            restaurantsListLabel.removeAll();
            restaurantsListLabel.revalidate();
            restaurantsListLabel.repaint();

            JLabel dontHaveRestaurant = new JLabel("Não foi encontrado nenhum restaurante!");
            dontHaveRestaurant.setFont(new Font("Arial", Font.BOLD,20));
            dontHaveRestaurant.setHorizontalAlignment(SwingConstants.CENTER);
            dontHaveRestaurant.setBounds(0,0,500,410);
            restaurantsListLabel.add(dontHaveRestaurant);

            verifyButtons();
        }
    }

    public void updateListLabel() {
        restaurantsListLabel.removeAll();
        restaurantsListLabel.revalidate();
        restaurantsListLabel.repaint();
        setRestaurants();
        verifyButtons();
    }

    public void createComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        Database database = new Database();
        this.listRestaurants = database.getAllRestaurants();

        listFilteredRestaurants = (ArrayList<Restaurant>) listRestaurants.stream().collect(Collectors.toList());

        JTextField inputSearch = new JTextField();
        inputSearch.setBounds(225,90,250,40);

        JButton search = new JButton();
        search.setBounds(485, 90, 40, 40);
        search.addActionListener(e -> searchList(inputSearch.getText()));
        ImageIcon imageButton = new ImageIcon("src/Resources/search.png");
        imageButton.setImage(imageButton.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
        search.setIcon(imageButton);
        search.setFocusable(false);

        Line line = new Line(160);

        restaurantsListLabel = new JLabel();
        restaurantsListLabel.setBounds(125, 190, 600, 600);

        setRestaurants();

        prev = new JButton("<");
        prev.setFont(new Font("Arial", Font.BOLD,25));
        prev.setBounds(320, 620, 50, 50);
        prev.setFocusable(false);
        prev.addActionListener(e -> {
            countPage --;
            updateListLabel();
        });

        next = new JButton(">");
        next.setFont(new Font("Arial", Font.BOLD,25));
        next.setBounds(380, 620, 50, 50);
        next.addActionListener(e -> {
            countPage ++;
            updateListLabel();
        });

        verifyButtons();

        this.add(restaurantsListLabel);
        this.add(search);
        this.add(inputSearch);
        this.add(line);
        this.add(prev);
        this.add(next);
    }
}
