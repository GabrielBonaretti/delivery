package src.UI.Layout;

import src.Database.Database;
import src.Entities.User;
import src.UI.Components.*;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;

public class FormLogin extends JLabel {
    private boolean isRestaurant = false;
    private final Screen screen;

    public FormLogin(Screen screen) {
        this.screen = screen;
        this.setBounds(300, 150, 400, 450);
        this.setBackground(new Color(170,170,170));
        this.setOpaque(true);

        createaComponents();
    }

    public void createaComponents() {
        this.removeAll();
        this.repaint();
        this.revalidate();

        String textTitle;

        if (!isRestaurant) {
            textTitle = "Login usuário";
        } else {
            textTitle = "Login restaurante";
        }

        TitleForm titleForm = new TitleForm(textTitle, 100, 90, 200);

        JCheckBox restaurantCheckBox = new JCheckBox("Restaurante");
        restaurantCheckBox.setSelected(isRestaurant);
        restaurantCheckBox.setBounds(225, 150, 100, 30);
        restaurantCheckBox.setBackground(new Color(170,170,170));
        restaurantCheckBox.setOpaque(true);
        restaurantCheckBox.setFocusable(false);
        restaurantCheckBox.addActionListener(e -> {
            isRestaurant = !isRestaurant;
            createaComponents();
        });

        Input inputName = new Input("Nome", 75, 150, 250);
        InputSenha inputPassword = new InputSenha("Senha", 75, 220, 250);

        JButton buttonRegister = new JButton("Não tem uma conta? Cadastre-se!");
        buttonRegister.setFont(new Font("Arial", Font.BOLD,10));
        buttonRegister.setBackground(new Color(170,170,170));
        buttonRegister.setBorder(null);
        buttonRegister.setFocusable(false);
        buttonRegister.setBounds(150, 290, 175, 20);
        buttonRegister.addActionListener(e -> {
            this.screen.login.setVisible(false);
            this.screen.register.setVisible(true);

            inputName.clearContent();
            inputPassword.clearContent();
        });

        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD,20));
        loginButton.setFocusable(false);
        loginButton.setBounds(150, 350, 100, 40);
        loginButton.addActionListener(e -> {
            String name = inputName.inputContent();
            String password = inputPassword.inputContent();

            Database database = new Database();
            User user;

            if (!isRestaurant) {
                user = database.verifyAcountUser(name,password);
                this.screen.delivery.listRestaurantLayout.setVisible(true);
                this.screen.delivery.orderLayout.setVisible(false);
                this.screen.delivery.restaurantSpecificPage.setVisible(false);
                this.screen.delivery.myRestaurantLayout.setVisible(false);

            } else {
                user = database.verifyAcountRestaurant(name,password);
                this.screen.delivery.listRestaurantLayout.setVisible(false);
                this.screen.delivery.restaurantSpecificPage.setVisible(false);
                this.screen.delivery.orderLayout.setVisible(false);
                this.screen.delivery.myRestaurantLayout.setVisible(true);
            }

            if (user != null) {
                this.screen.login.setVisible(false);
                this.screen.delivery.setVisible(true);

                this.screen.application.order.setUser(user);
                this.screen.application.setUser(user);
                this.screen.application.getAllRestaurant();
                this.screen.delivery.sidebar.createComponents(isRestaurant);

                if (!isRestaurant) {
                    this.screen.delivery.listRestaurantLayout.createComponents();
                } else {
                    this.screen.delivery.myRestaurantLayout.createComponents();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No user find");
            }

            inputName.clearContent();
            inputPassword.clearContent();
        });

        this.add(titleForm);
        this.add(inputName);
        this.add(inputPassword);
        this.add(buttonRegister);
        this.add(loginButton);
        this.add(restaurantCheckBox);

    }

}
