package src.UI.Subpage;

import src.UI.Components.BotaoSideBar;
import src.UI.Pages.Delivery;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    private ArrayList<BotaoSideBar> listaBotoes = new ArrayList<BotaoSideBar>();
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
        for (BotaoSideBar botao: listaBotoes) {
            botao.setBackground(new Color(200, 200, 200));
        }
    }

    public void createComponents(boolean isRestaurante) {
        this.removeAll();
        this.repaint();
        this.revalidate();

        BotaoSideBar botaoSair;
        BotaoSideBar botaoMeuRestaurantes;
        BotaoSideBar botaoListarRestaurantes;
        BotaoSideBar carrinho;
        BotaoSideBar historico;

        if (isRestaurante) {
            botaoMeuRestaurantes = new BotaoSideBar("Meu restaurante", 0, "src/Resources/loja.png",2);
            botaoMeuRestaurantes.setBackground(new Color(170, 170, 170));

            botaoSair = new BotaoSideBar("Sair", 40, "src/Resources/logout.png", 4);
            listaBotoes.add(botaoMeuRestaurantes);
            listaBotoes.add(botaoSair);
            this.add(botaoMeuRestaurantes);
        } else {
            botaoListarRestaurantes = new BotaoSideBar("Listar restaurantes", 0, "src/Resources/lista.png",0);
            botaoListarRestaurantes.setBackground(new Color(170, 170, 170));

            carrinho = new BotaoSideBar("Carrinho", 40, "src/Resources/cart.png",1);
            historico = new BotaoSideBar("Historico", 80, "src/Resources/history.png",3);
            botaoSair = new BotaoSideBar("Sair", 120, "src/Resources/logout.png",4);
            listaBotoes.add(botaoListarRestaurantes);
            listaBotoes.add(carrinho);
            listaBotoes.add(historico);
            listaBotoes.add(botaoSair);
            this.add(botaoListarRestaurantes);
            this.add(historico);
            this.add(carrinho);
        }
        this.add(botaoSair);


        for (BotaoSideBar botao: listaBotoes) {
            botao.addActionListener(e -> {
                limpandoCor();
                botao.setBackground(new Color(170, 170, 170));

                switch (botao.buttonChoice) {
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
                        this.delivery.pedido.carrinho.clear();
                        this.delivery.orderLayout.createRequests();
                        break;
                }
            });
        }
    }

}
