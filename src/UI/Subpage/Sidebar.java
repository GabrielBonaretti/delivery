package src.UI.Subpage;

import src.UI.Components.ButtonSideBar;
import src.UI.Pages.Delivery;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    private final ArrayList<ButtonSideBar> listButton = new ArrayList<ButtonSideBar>();
    public Delivery delivery;
    public Screen screen;
    public Sidebar(Delivery delivey, Screen screen) {
        this.setBounds(0, 0, 250, 800);
        this.setBackground(new Color(200,200,200));
        this.setOpaque(true);
        this.setLayout(null);
        this.delivery = delivey;
        this.screen = screen;
        createComponents(false);
    }

    public void limpandoCor() {
        for (ButtonSideBar button : listButton) {
            button.setBackground(new Color(200, 200, 200));
        }
    }

    public void createComponents(boolean isRestaurante) {
        this.removeAll();
        this.repaint();
        this.revalidate();

        ButtonSideBar buttonExit;
        ButtonSideBar buttonMyRestaurant;
        ButtonSideBar buttonListRestaurant;
        ButtonSideBar buttonCart;
        ButtonSideBar buttonHistory;

        if (isRestaurante) {
            buttonMyRestaurant = new ButtonSideBar("Meu restaurante", 0, "src/Resources/loja.png",2);
            buttonMyRestaurant.setBackground(new Color(170, 170, 170));

            buttonExit = new ButtonSideBar("Sair", 40, "src/Resources/logout.png", 4);
            listButton.add(buttonMyRestaurant);
            listButton.add(buttonExit);
            this.add(buttonMyRestaurant);
        } else {
            buttonListRestaurant = new ButtonSideBar("Listar restaurantes", 0, "src/Resources/lista.png",0);
            buttonListRestaurant.setBackground(new Color(170, 170, 170));

            buttonCart = new ButtonSideBar("Carrinho", 40, "src/Resources/cart.png",1);
            buttonHistory = new ButtonSideBar("Historico", 80, "src/Resources/history.png",3);
            buttonExit = new ButtonSideBar("Sair", 120, "src/Resources/logout.png",4);
            listButton.add(buttonListRestaurant);
            listButton.add(buttonCart);
            listButton.add(buttonHistory);
            listButton.add(buttonExit);
            this.add(buttonListRestaurant);
            this.add(buttonHistory);
            this.add(buttonCart);
        }
        this.add(buttonExit);


        for (ButtonSideBar button : listButton) {
            button.addActionListener(e -> {
                limpandoCor();
                button.setBackground(new Color(170, 170, 170));

                switch (button.buttonChoice) {
                    case 0:
                        this.delivery.listRestaurantLayout.setVisible(true);
                        this.delivery.orderLayout.setVisible(false);
                        this.delivery.myRestaurantLayout.setVisible(false);
                        this.delivery.historicLayout.setVisible(false);
                        this.delivery.specificOrderLayout.setVisible(false);
                        if (this.delivery.restaurantSpecificPage != null) {
                            this.delivery.restaurantSpecificPage.setVisible(false);
                        }
                        break;
                    case 1:
                        this.delivery.listRestaurantLayout.setVisible(false);
                        this.delivery.orderLayout.setVisible(true);
                        this.delivery.myRestaurantLayout.setVisible(false);
                        this.delivery.historicLayout.setVisible(false);
                        this.delivery.specificOrderLayout.setVisible(false);
                        if (this.delivery.restaurantSpecificPage != null) {
                            this.delivery.restaurantSpecificPage.setVisible(false);
                        }
                        break;
                    case 2:
                        this.delivery.listRestaurantLayout.setVisible(false);
                        this.delivery.orderLayout.setVisible(false);
                        this.delivery.myRestaurantLayout.setVisible(true);
                        this.delivery.historicLayout.setVisible(false);
                        this.delivery.specificOrderLayout.setVisible(false);
                        if (this.delivery.restaurantSpecificPage != null) {
                            this.delivery.restaurantSpecificPage.setVisible(false);
                        }
                        break;
                    case 3:
                        this.delivery.listRestaurantLayout.setVisible(false);
                        this.delivery.orderLayout.setVisible(false);
                        this.delivery.myRestaurantLayout.setVisible(false);
                        this.delivery.historicLayout.setVisible(true);
                        this.delivery.historicLayout.createComponents();

                        this.delivery.specificOrderLayout.setVisible(false);
                        if (this.delivery.restaurantSpecificPage != null) {
                            this.delivery.restaurantSpecificPage.setVisible(false);
                        }
                        break;
                    case 4:
                        this.screen.delivery.setVisible(false);
                        this.screen.login.setVisible(true);
                        this.screen.application.order.cart.clear();
                        this.delivery.orderLayout.createRequests();
                        break;
                }
            });
        }
    }

}
