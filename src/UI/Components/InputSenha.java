package src.UI.Components;

import javax.swing.*;
import java.awt.*;

public class InputSenha extends JLabel {
    private final JPasswordField input;
    public InputSenha(String textInput, int x, int y, int width) {
        this.setBounds(x, y, width, 70);

        JLabel text = new JLabel(textInput);
        text.setFont(new Font("Arial", Font.BOLD,15));
        text.setBounds(0,0, width, 30);

        this.input = new JPasswordField();
        input.setFont(new Font("Arial", Font.BOLD,15));
        input.setBounds(0, 30, width, 40);

        this.add(text);
        this.add(input);
    }

    public String inputContent() {
        return input.getText();
    }

    public void clearContent() {
        input.setText("");
    }
}
