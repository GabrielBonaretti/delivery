package org.delivery.UI.Components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * The ButtonSideBar class represents a custom button used in the application's sidebar.
 */
public class ButtonSideBar extends JButton {
    // An identifier representing the choice associated with the button.
    public int buttonChoice;


    /**
     * Constructs a ButtonSideBar object with the specified text, position, image text, and button choice.
     *
     * @param text         The text to be displayed on the button.
     * @param y            The vertical position of the button.
     * @param textImg      The path to the image used on the button.
     * @param buttonChoice An identifier representing the choice associated with the button.
     */
    public ButtonSideBar(String text, int y, String textImg, int buttonChoice) {
        super(text);
        this.buttonChoice = buttonChoice;

        // Set the image on the button
        ImageIcon imageButton = new ImageIcon(textImg);
        imageButton.setImage(imageButton.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        this.setIcon(imageButton);

        // Set button properties
        this.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        this.setFocusable(false);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setBackground(new Color(200,200,200));
        this.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        this.setBounds(0, y, 250, 40);
    }
}
