package src.UI.Components;

import javax.swing.*;
import java.awt.*;


/**
 * The Input class represents a labeled input field in the user interface.
 */
public class Input extends JLabel {
    // The text field used for user input.
    private final JTextField input;


    /**
     * Constructs an Input object with the specified label text, position, and width.
     *
     * @param text   The label text to be displayed.
     * @param x      The horizontal position of the input field.
     * @param y      The vertical position of the input field.
     * @param width  The width of the input field.
     */
    public Input(String textParam, int x, int y, int width) {
        this.setBounds(x, y, width, 70);

        // Create and set properties for the label
        JLabel text = new JLabel();
        text.setFont(new Font("Arial", Font.BOLD,15));
        text.setText(textParam);
        text.setBounds(0,0, width, 30);

        // Create and set properties for the text field
        this.input = new JTextField();
        input.setFont(new Font("Arial", Font.BOLD,15));
        input.setBounds(0, 30, width, 40);

        // Add label and text field to the input component
        this.add(text);
        this.add(input);
    }


    /**
     * Retrieves the content of the input field.
     *
     * @return The content of the input field.
     */
    public String inputContent() {
        return input.getText();
    }


    /**
     * Clears the content of the input field.
     */
    public void clearContent() {
        input.setText("");
    }
}
