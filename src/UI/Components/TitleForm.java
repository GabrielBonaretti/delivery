package src.UI.Components;

import javax.swing.*;
import java.awt.*;

public class TitleForm extends JLabel {
    public TitleForm(String text, int x, int y, int width ) {
        super(text, SwingConstants.CENTER);
        this.setFont(new Font("Arial", Font.BOLD,20));
        this.setBounds(x, y, width, 40);
    }
}
