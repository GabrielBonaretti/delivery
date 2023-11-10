package src.UI;

import src.Database.Database;
import src.Entities.Application;
import src.UI.Pages.Register;
import src.UI.Pages.Delivery;
import src.UI.Pages.Login;

import javax.swing.*;
import javax.xml.crypto.Data;

/**
 * The Screen class represents the main frame of the application, managing different pages.
 */
public class Screen extends JFrame{
    // Fields for different pages
    public Login login;
    public Register register;
    public Delivery delivery;
    public Application application;

    /**
     * Constructs a Screen object, initializes the application and sets up the initial layout.
     */
    public Screen() {
        // Set basic frame properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        // Create tables in database if dont exists
        Database database = new Database();
        database.createDatabase();

        // Initialize the application
        this.application = new Application();

        // Create and add the Login page
        this.login = new Login(this);
        this.add(login);
        this.login.show(true);  // Show the Login page by default

        // Create and add the Register page
        this.register = new Register(this);
        this.add(register);
        this.register.show(false);  // Hide the Register page initially

        // Create and add the Delivery page
        this.delivery = new Delivery(this);
        this.add(delivery);
        this.delivery.show(false); // Hide the Delivery page initially

        // Make the frame visible
        this.setVisible(true);
    }
}
