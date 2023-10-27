package src.UI.Components;

import javax.swing.*;
import java.awt.*;

public class Line extends JLabel {
    public Line(int y) {
        this.setBounds(125, y, 500, 1);
        this.setBackground(new Color(180,180,180));
        this.setOpaque(true);
    }
}
