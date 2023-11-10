package src.UI.Pages;

import src.UI.Layout.FormLogin;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * The Login class represents the login page of the application.
 * It includes a form for user login.
 */
public class Login extends JPanel {

    /**
     * Constructs a Login object with the specified screen instance.
     *
     * @param screen The Screen instance containing the application's main logic.
     */
    public Login(Screen screen) {
        this.setBounds(0,0, 1000, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);

        // Create and add the login form
        FormLogin containerForm = new FormLogin(screen);

        this.add(containerForm);
    }
}
