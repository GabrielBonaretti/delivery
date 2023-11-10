package src.UI.Components;

import javax.swing.*;
import java.awt.*;

/**
 * The Title class represents a title label in the user interface.
 */
public class Title extends JLabel {

    /**
     * Constructs a Title object with the specified text and width.
     *
     * @param text  The text to be displayed by the label.
     * @param width The width of the title label.
     */
    public Title(String text, int width) {
        super(text);
        this.setBounds(125,90, width,40);
        this.setFont(new Font("Arial", Font.BOLD,30));
    }
}
