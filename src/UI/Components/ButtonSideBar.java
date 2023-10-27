package src.UI.Components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ButtonSideBar extends JButton {
    public int buttonChoice;
    public ButtonSideBar(String text, int y, String textImg, int buttonChoice) {
        super(text);
        this.buttonChoice = buttonChoice;
        ImageIcon imageButton = new ImageIcon(textImg);
        imageButton.setImage(imageButton.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        this.setIcon(imageButton);
        this.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        this.setFocusable(false);
        this.setHorizontalAlignment(SwingConstants.LEADING);
        this.setBackground(new Color(200,200,200));
        this.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        this.setBounds(0, y, 250, 40);
    }
}
