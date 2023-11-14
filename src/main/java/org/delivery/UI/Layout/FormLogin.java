package org.delivery.UI.Layout;

import org.delivery.Database.Database;
import org.delivery.Entities.User;
import org.delivery.UI.Components.*;
import org.delivery.UI.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * The FormLogin class represents the login form layout, allowing users to log in as a regular user or a restaurant.
 */
public class FormLogin extends JLabel {
    private boolean isRestaurant = false;
    private final Screen screen;

    /**
     * Constructs a FormLogin object with the specified screen.
     *
     * @param screen The main screen of the application.
     */
    public FormLogin(Screen screen) {
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

        // Determine the title based on the current state
        String textTitle = (!isRestaurant) ? "Login usuário" : "Login restaurante";
        TitleForm titleForm = new TitleForm(textTitle, 100, 90, 200);

        // Checkbox to switch between user and restaurant login
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

        // Input fields for username and password
        Input inputName = new Input("Nome", 75, 150, 250);
        InputPassword inputPassword = new InputPassword("Senha", 75, 220, 250);

        // Button to navigate to the registration page
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

        // Button to perform the login operation
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Arial", Font.BOLD,20));
        loginButton.setFocusable(false);
        loginButton.setBounds(150, 350, 100, 40);
        loginButton.addActionListener(e -> {
            String name = inputName.inputContent();
            String password = inputPassword.inputContent();

            Database database = new Database();
            User user;

            // Check if it's a regular user or a restaurant
            if (!isRestaurant) {
                user = database.verifyAcountUser(name,password);
                // Update the UI components based on the user type
                this.screen.delivery.listRestaurantLayout.setVisible(true);
                this.screen.delivery.orderLayout.setVisible(false);
                this.screen.delivery.restaurantSpecificPage.setVisible(false);
                this.screen.delivery.myRestaurantLayout.setVisible(false);

            } else {
                user = database.verifyAcountRestaurant(name,password);
                // Update the UI components based on the user type
                this.screen.delivery.listRestaurantLayout.setVisible(false);
                this.screen.delivery.restaurantSpecificPage.setVisible(false);
                this.screen.delivery.orderLayout.setVisible(false);
                this.screen.delivery.myRestaurantLayout.setVisible(true);
            }

            if (user != null) {
                // Switch to the delivery view upon successful login
                this.screen.login.setVisible(false);
                this.screen.delivery.setVisible(true);

                // Set the user for the application and retrieve restaurant data
                this.screen.application.order.setUser(user);
                this.screen.application.setUser(user);
                this.screen.application.getAllRestaurant();

                // Update the sidebar and main layout components
                this.screen.delivery.sidebar.createComponents(isRestaurant);

                if (!isRestaurant) {
                    this.screen.delivery.listRestaurantLayout.createComponents();
                } else {
                    this.screen.delivery.myRestaurantLayout.createComponents();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No user find");
            }

            // Clear input fields after login attempt
            inputName.clearContent();
            inputPassword.clearContent();
        });

        // Add components to the form layout
        this.add(titleForm);
        this.add(inputName);
        this.add(inputPassword);
        this.add(buttonRegister);
        this.add(loginButton);
        this.add(restaurantCheckBox);

    }

}
