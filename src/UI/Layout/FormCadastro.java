package src.UI.Layout;

import src.Database.Database;
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

        Input inputNome = new Input("Nome", 75, 75, 250);
        Input inputCPF;
        if (!isRestaurant) {
            inputCPF = new Input("CPF", 75, 145, 250);
        } else {
            inputCPF = new Input("CNPJ", 75, 145, 250);
        }
        Input inputPosicaoX = new Input("Posição (X)", 75, 215, 115);
        Input inputPosicaoY = new Input("Posição (Y)", 210, 215, 115);
        InputSenha inputSenha = new InputSenha("Senha", 75, 285, 250);

        JButton botaoLogin = new JButton("Já tem uma conta? Entre!");
        botaoLogin.setFont(new Font("Arial", Font.BOLD,10));
        botaoLogin.setBackground(new Color(170,170,170));
        botaoLogin.setBorder(null);
        botaoLogin.setFocusable(false);
        botaoLogin.setBounds(150, 355, 175, 20);
        botaoLogin.addActionListener(e -> {
            this.screen.login.setVisible(true);
            this.screen.cadastro.setVisible(false);

            inputNome.clearContent();
            inputCPF.clearContent();
            inputPosicaoX.clearContent();
            inputPosicaoY.clearContent();
            inputSenha.clearContent();
        });


        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setFont(new Font("Arial", Font.BOLD,20));
        cadastrar.setFocusable(false);
        cadastrar.setBounds(150, 375, 100, 40);
        cadastrar.addActionListener(e -> {
            String name = inputNome.inputContent();
            String cpfCnpj = inputCPF.inputContent();
            int posicaoX = Integer.parseInt(inputPosicaoX.inputContent());
            int posicaoY = Integer.parseInt(inputPosicaoY.inputContent());
            String senha = inputSenha.inputContent();

            Database database = new Database();

            if (!isRestaurant) {
                database.createUser(
                        name,
                        cpfCnpj,
                        posicaoX,
                        posicaoY,
                        senha
                );
            } else {
                database.createRestaurant(
                        name,
                        cpfCnpj,
                        posicaoX,
                        posicaoY,
                        senha
                );
            }

            inputNome.clearContent();
            inputCPF.clearContent();
            inputPosicaoX.clearContent();
            inputPosicaoY.clearContent();
            inputSenha.clearContent();

        });

        this.add(titleForm);
        this.add(restaurantCheckBox);
        this.add(inputNome);
        this.add(inputCPF);
        this.add(inputPosicaoX);
        this.add(inputPosicaoY);
        this.add(inputSenha);
        this.add(botaoLogin);
        this.add(cadastrar);
    }
}
