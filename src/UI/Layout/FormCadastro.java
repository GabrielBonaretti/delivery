package src.UI.Layout;

import src.UI.Components.*;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;

public class FormCadastro extends JLabel {
    private final Screen screen;
    private boolean isRestaurant = false;
    public FormCadastro(Screen screen) {
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

        TitleForm titleForm = new TitleForm("Cadastrar", 75, 25, 100);

        JCheckBox restaurantCheckBox = new JCheckBox("Restaurante");
        restaurantCheckBox.setSelected(isRestaurant);
        restaurantCheckBox.setBounds(225, 25, 100, 40);
        restaurantCheckBox.setBackground(new Color(170,170,170));
        restaurantCheckBox.setOpaque(true);
        restaurantCheckBox.setFocusable(false);
        restaurantCheckBox.addActionListener(e -> {
            isRestaurant = !isRestaurant;
            createaComponents();
        });

        Input inputName = new Input("Nome", 75, 75, 250);
        Input inputCPF;
        if (!isRestaurant) {
            inputCPF = new Input("CPF", 75, 145, 250);
        } else {
            inputCPF = new Input("CNPJ", 75, 145, 250);
        }
        Input inputPositionX = new Input("Posição (X)", 75, 215, 115);
        Input inputPositionY = new Input("Posição (Y)", 210, 215, 115);
        InputSenha inputPassword = new InputSenha("Senha", 75, 285, 250);

        JButton buttonLogin = new JButton("Já tem uma conta? Entre!");
        buttonLogin.setFont(new Font("Arial", Font.BOLD,10));
        buttonLogin.setBackground(new Color(170,170,170));
        buttonLogin.setBorder(null);
        buttonLogin.setFocusable(false);
        buttonLogin.setBounds(150, 355, 175, 20);
        buttonLogin.addActionListener(e -> {
            this.screen.login.setVisible(true);
            this.screen.register.setVisible(false);

            inputName.clearContent();
            inputCPF.clearContent();
            inputPositionX.clearContent();
            inputPositionY.clearContent();
            inputPassword.clearContent();
        });


        JButton registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Arial", Font.BOLD,20));
        registerButton.setFocusable(false);
        registerButton.setBounds(150, 375, 100, 40);
        registerButton.addActionListener(e -> {
            try {
                String name = inputName.inputContent();
                String cpfCnpj = inputCPF.inputContent();
                int positionX = Integer.parseInt(inputPositionX.inputContent());
                int positionY = Integer.parseInt(inputPositionY.inputContent());
                String password = inputPassword.inputContent();


                if (!isRestaurant) {
                    screen.application.registerUser(
                            name,
                            cpfCnpj,
                            positionX,
                            positionY,
                            password
                    );
                } else {
                    screen.application.registerRestaurant(
                            name,
                            cpfCnpj,
                            positionX,
                            positionY,
                            password
                    );
                }


            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Fill in the fields correctly");
            }

            inputName.clearContent();
            inputCPF.clearContent();
            inputPositionX.clearContent();
            inputPositionY.clearContent();
            inputPassword.clearContent();
        });

        this.add(titleForm);
        this.add(restaurantCheckBox);
        this.add(inputName);
        this.add(inputCPF);
        this.add(inputPositionX);
        this.add(inputPositionY);
        this.add(inputPassword);
        this.add(buttonLogin);
        this.add(registerButton);
    }
}
