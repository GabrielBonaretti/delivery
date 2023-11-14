package org.delivery.UI.Pages;

import org.delivery.UI.Layout.FormRegister;
import org.delivery.UI.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * The Register class represents the user registration page of the application.
 * It includes a form for user registration.
 */
public class Register extends JPanel{

    /**
     * Constructs a Register object with the specified screen instance.
     *
     * @param screen The Screen instance containing the application's main logic.
     */
    public Register(Screen screen) {
        this.setBounds(0,0, 1000, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);

        // Create and add the registration form
        FormRegister containerForm = new FormRegister(screen);

        this.add(containerForm);
    }
}
