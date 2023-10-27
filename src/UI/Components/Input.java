package src.UI.Components;

import javax.swing.*;
import java.awt.*;

public class Input extends JLabel {
    private JTextField input;

    public Input(String textoEmCima, int x, int y, int width) {
        this.setBounds(x, y, width, 70);

        JLabel texto = new JLabel();
        texto.setFont(new Font("Arial", Font.BOLD,15));
        texto.setText(textoEmCima);
        texto.setBounds(0,0, width, 30);

        this.input = new JTextField();
        input.setFont(new Font("Arial", Font.BOLD,15));
        input.setBounds(0, 30, width, 40);

        this.add(texto);
        this.add(input);
    }

    public String inputContent() {
        return input.getText();
    }

    public void clearContent() {
        input.setText("");
    }
}
