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

        Input inputNome = new Input("Nome", 75, 150, 250);
        InputSenha inputSenha = new InputSenha("Senha", 75, 220, 250);

        JButton botaoCadastrar = new JButton("Não tem uma conta? Cadastre-se!");
        botaoCadastrar.setFont(new Font("Arial", Font.BOLD,10));
        botaoCadastrar.setBackground(new Color(170,170,170));
        botaoCadastrar.setBorder(null);
        botaoCadastrar.setFocusable(false);
        botaoCadastrar.setBounds(150, 290, 175, 20);
        botaoCadastrar.addActionListener(e -> {
            this.screen.login.setVisible(false);
            this.screen.cadastro.setVisible(true);

            inputNome.clearContent();
            inputSenha.clearContent();
        });

        JButton avancar = new JButton("Entrar");
        avancar.setFont(new Font("Arial", Font.BOLD,20));
        avancar.setFocusable(false);
        avancar.setBounds(150, 350, 100, 40);
        avancar.addActionListener(e -> {
            String name = inputNome.inputContent();
            String password = inputSenha.inputContent();

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

            if (user.id > 0) {
                this.screen.login.setVisible(false);
                this.screen.delivery.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Dont user find");
            }

            this.screen.delivery.setUser(user);
            this.screen.delivery.sidebar.createComponents(isRestaurant);

            if (!isRestaurant) {
                this.screen.delivery.listRestaurantLayout.createComponents();
            } else {
                this.screen.delivery.myRestaurantLayout.createComponents();
            }
            inputNome.clearContent();
            inputSenha.clearContent();
        });

        this.add(titleForm);
        this.add(inputNome);
        this.add(inputSenha);
        this.add(botaoCadastrar);
        this.add(avancar);
        this.add(restaurantCheckBox);

    }

}
