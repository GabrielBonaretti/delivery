package src.UI;

import src.Entities.Application;
import src.UI.Pages.Register;
import src.UI.Pages.Delivery;
import src.UI.Pages.Login;

import javax.swing.*;

public class Screen extends JFrame{
    public Login login;
    public Register register;
    public Delivery delivery;
    public Application application;



    public Screen() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);

        this.application = new Application();

        this.setResizable(false);
        this.setLayout(null);

        this.login = new Login(this);
        this.add(login);
        this.login.show(true);

        this.register = new Register(this);
        this.add(register);
        this.register.show(false);

        this.delivery = new Delivery(this);
        this.add(delivery);
        this.delivery.show(false);

        this.setVisible(true);
    }
}
