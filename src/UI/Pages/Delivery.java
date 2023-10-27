package src.UI.Pages;

import src.Entities.Pedido;
import src.UI.Screen;
import src.UI.Subpage.*;

import javax.swing.*;
import java.awt.*;

public class Delivery extends JPanel {
    public Pedido pedido;
    public Sidebar sidebar;
    public ListRestaurantLayout listRestaurantLayout;
    public OrderLayout orderLayout;
    public MyRestaurantLayout myRestaurantLayout;
    public RestaurantSpecificPage restaurantSpecificPage;
    public HistoricLayout historicLayout;
    public SpecificOrderLayout specificOrderLayout;
    public int id;

    public Delivery(Screen screen) {
        this.setBounds(0,0, 1000, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);

        this.pedido = new Pedido();

        this.sidebar = new Sidebar(this, screen);
        this.add(sidebar);

        this.listRestaurantLayout = new ListRestaurantLayout(this);
        this.add(listRestaurantLayout);
        this.listRestaurantLayout.setVisible(false);

        this.orderLayout = new OrderLayout(this);
        this.add(orderLayout);
        this.listRestaurantLayout.setVisible(false);

        this.myRestaurantLayout = new MyRestaurantLayout(this);
        this.add(myRestaurantLayout);
        this.listRestaurantLayout.setVisible(false);

        this.restaurantSpecificPage = new RestaurantSpecificPage(this);
        this.add(restaurantSpecificPage);
        this.restaurantSpecificPage.setVisible(false);

        this.historicLayout = new HistoricLayout(this);
        this.add(historicLayout);
        this.historicLayout.setVisible(false);

        this.specificOrderLayout = new SpecificOrderLayout(this);
        this.add(specificOrderLayout);
        this.specificOrderLayout.setVisible(false);
    }
}