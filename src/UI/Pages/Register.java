package src.UI.Pages;

import src.UI.Layout.FormCadastro;
import src.UI.Screen;

import javax.swing.*;
import java.awt.*;

public class Register extends JPanel{
    public Register(Screen screen) {
        this.setBounds(0,0, 1000, 800);
        this.setBackground(new Color(240,240,240));
        this.setLayout(null);

        FormCadastro containerForm = new FormCadastro(screen);

        this.add(containerForm);
    }
}
