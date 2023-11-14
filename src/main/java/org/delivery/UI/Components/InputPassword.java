package org.delivery.UI.Components;

import javax.swing.*;
import java.awt.*;


/**
 * The InputPassword class represents a labeled password input field in the user interface.
 */
public class InputPassword extends JLabel {
    // The password field used for user input.
    private final JPasswordField input;

    /**
     * Constructs an InputPassword object with the specified label text, position, and width.
     *
     * @param textInput The label text to be displayed.
     * @param x         The horizontal position of the password input field.
     * @param y         The vertical position of the password input field.
     * @param width     The width of the password input field.
     */
    public InputPassword(String textInput, int x, int y, int width) {
        this.setBounds(x, y, width, 70);

        // Create and set properties for the label
        JLabel text = new JLabel(textInput);
        text.setFont(new Font("Arial", Font.BOLD,15));
        text.setBounds(0,0, width, 30);

        // Create and set properties for the password field
        this.input = new JPasswordField();
        input.setFont(new Font("Arial", Font.BOLD,15));
        input.setBounds(0, 30, width, 40);

        // Add label and password field to the input component
        this.add(text);
        this.add(input);
    }


    /**
     * Retrieves the content of the password input field.
     *
     * @return The content of the password input field.
     */
    public String inputContent() {
        return input.getText();
    }


    /**
     * Clears the content of the password input field.
     */
    public void clearContent() {
        input.setText("");
    }
}
