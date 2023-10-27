package src.UI.Components;

import javax.swing.*;
import java.awt.*;

public class NoItemsText extends JLabel {

    public NoItemsText(String text) {
        super(text, SwingConstants.CENTER);
        this.setBounds(225, 365, 300, 50);
        this.setFont(new Font("Arial", Font.BOLD,15));
    }
}
