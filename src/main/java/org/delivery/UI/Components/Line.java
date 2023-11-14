package org.delivery.UI.Components;

import javax.swing.*;
import java.awt.*;


/**
 * The Line class represents a horizontal line in the user interface.
 */
public class Line extends JLabel {

    /**
     * Constructs a Line object with the specified vertical position.
     *
     * @param y The vertical position of the line.
     */
    public Line(int y) {
        this.setBounds(125, y, 500, 1);
        this.setBackground(new Color(180,180,180));
        this.setOpaque(true);
    }
}
