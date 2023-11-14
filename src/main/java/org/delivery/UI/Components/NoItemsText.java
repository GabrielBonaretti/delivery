package org.delivery.UI.Components;

import javax.swing.*;
import java.awt.*;

/**
 * The NoItemsText class represents a centered text label indicating the absence of items in the user interface.
 */
public class NoItemsText extends JLabel {

    /**
     * Constructs a NoItemsText object with the specified text.
     *
     * @param text The text to be displayed by the label.
     */
    public NoItemsText(String text) {
        super(text, SwingConstants.CENTER);
        this.setBounds(225, 365, 300, 50);
        this.setFont(new Font("Arial", Font.BOLD,15));
    }
}
