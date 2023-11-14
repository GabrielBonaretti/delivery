package org.delivery.UI.Components;

import javax.swing.*;
import java.awt.*;

/**
 * The TitleForm class represents a centered title label for a form in the user interface.
 */
public class TitleForm extends JLabel {

    /**
     * Constructs a TitleForm object with the specified text, horizontal position, vertical position, and width.
     *
     * @param text   The text to be displayed by the label.
     * @param x      The horizontal position of the title label.
     * @param y      The vertical position of the title label.
     * @param width  The width of the title label.
     */
    public TitleForm(String text, int x, int y, int width ) {
        super(text, SwingConstants.CENTER);
        this.setFont(new Font("Arial", Font.BOLD,20));
        this.setBounds(x, y, width, 40);
    }
}
