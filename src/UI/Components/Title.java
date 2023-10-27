package src.UI.Components;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {

    public Title(String text, int width) {
        super(text);
        this.setBounds(125,90, width,40);
        this.setFont(new Font("Arial", Font.BOLD,30));
    }
}
