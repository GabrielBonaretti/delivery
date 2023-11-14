package org.delivery.UI.Layout;

import org.delivery.UI.Components.*;
import org.delivery.UI.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * The FormCadastro class represents a registration form in the user interface.
 */
public class FormRegister extends JLabel {
    private final Screen screen;
    private boolean isRestaurant = false;

    /**
     * Constructs a FormCadastro object with the specified screen.
     *
     * @param screen The screen to which the form belongs.
     */
    public FormRegister(Screen screen) {
        // Set initial properties of the form
        this.screen = screen;
        this.setBounds(300, 150, 400, 450);
        this.setBackground(new Color(170,170,170));
        this.setOpaque(true);

        // Create and update the components of the form
        createaComponents();
    }

    /**
     * Creates and updates the components of the registration form based on the current state.
     */
    public void createaComponents() {
        // Remove existing components
        this.removeAll();
        this.repaint();
        this.revalidate();

        // Create and add the title of the form
        TitleForm titleForm = new TitleForm("Cadastrar", 75, 25, 100);

        // Create and add the checkbox for restaurant registration
        JCheckBox restaurantCheckBox = new JCheckBox("Restaurante");
        restaurantCheckBox.setSelected(isRestaurant);
        restaurantCheckBox.setBounds(225, 25, 100, 40);
        restaurantCheckBox.setBackground(new Color(170,170,170));
        restaurantCheckBox.setOpaque(true);
        restaurantCheckBox.setFocusable(false);
        // Update the state of the form based on checkbox selection
        restaurantCheckBox.addActionListener(e -> {
            isRestaurant = !isRestaurant;
            createaComponents();
        });

        // Create input fields for user information
        Input inputName = new Input("Nome", 75, 75, 250);
        Input inputCPF;
        if (!isRestaurant) {
            inputCPF = new Input("CPF", 75, 145, 250);
        } else {
            inputCPF = new Input("CNPJ", 75, 145, 250);
        }
        Input inputPositionX = new Input("Posição (X)", 75, 215, 115);
        Input inputPositionY = new Input("Posição (Y)", 210, 215, 115);
        InputPassword inputPassword = new InputPassword("Senha", 75, 285, 250);

        // Create a button to navigate to the login form
        JButton buttonLogin = new JButton("Já tem uma conta? Entre!");
        buttonLogin.setFont(new Font("Arial", Font.BOLD,10));
        buttonLogin.setBackground(new Color(170,170,170));
        buttonLogin.setBorder(null);
        buttonLogin.setFocusable(false);
        buttonLogin.setBounds(150, 355, 175, 20);
        buttonLogin.addActionListener(e -> {
            this.screen.login.setVisible(true);
            this.screen.register.setVisible(false);

            // Clear input fields when navigating to the login form
            inputName.clearContent();
            inputCPF.clearContent();
            inputPositionX.clearContent();
            inputPositionY.clearContent();
            inputPassword.clearContent();
        });

        // Create a button to register a user or restaurant
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Arial", Font.BOLD,20));
        registerButton.setFocusable(false);
        registerButton.setBounds(150, 375, 100, 40);
        registerButton.addActionListener(e -> {
            try {
                // Retrieve information from input fields
                String name = inputName.inputContent();
                String cpfCnpj = inputCPF.inputContent();
                int positionX = Integer.parseInt(inputPositionX.inputContent());
                int positionY = Integer.parseInt(inputPositionY.inputContent());
                String password = inputPassword.inputContent();

                // Register the user or restaurant based on the form state
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
                // Show an error message if form validation fails
                JOptionPane.showMessageDialog(null, "Fill in the fields correctly");
            }

            // Clear input fields after registration attempt
            inputName.clearContent();
            inputCPF.clearContent();
            inputPositionX.clearContent();
            inputPositionY.clearContent();
            inputPassword.clearContent();
        });

        // Add components to the form
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
