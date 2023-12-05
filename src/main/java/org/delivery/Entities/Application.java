package org.delivery.Entities;

import org.delivery.DAO.RestaurantDAO;
import org.delivery.DAO.UserDAO;
import org.delivery.Database.Database;
import org.delivery.EntitiesBD.RestaurantDB;
import org.delivery.EntitiesBD.UserDB;
import org.delivery.Util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;


/**
 * The Application class represents the main application logic and serves as a controller.
 */
public class Application {
    // List of restaurants in the application.
    public ArrayList<Restaurant> restaurants;

    // The current user of the application.
    public User user;

    // The current order being processed in the application.
    public Order order;

    // The database instance for interacting with the underlying database.
    public Database database;

    private EntityManager entityManager;

    /**
     * Constructs an Application object with a new Database instance.
     */
    public Application() {
        this.database = new Database();
        this.entityManager = JPAUtil.getEntityManager();
    }


    /**
     * Registers a new restaurant in the application.
     *
     * @param name     The name of the restaurant.
     * @param cnpj     The CNPJ (Brazilian business ID) of the restaurant.
     * @param positionX The X coordinate of the restaurant's position.
     * @param positionY The Y coordinate of the restaurant's position.
     * @param password    The password of the restaurant.
     */
    public void registerRestaurant(
        String name,
        String cnpj,
        int positionX,
        int positionY,
        String password
    ) {
        RestaurantDAO restaurantDAO = new RestaurantDAO(entityManager);
        RestaurantDB restaurantDB = new RestaurantDB(name, cnpj, positionX, positionY, password);
        entityManager.getTransaction().begin();
        restaurantDAO.create(restaurantDB);
        entityManager.getTransaction().commit();
        entityManager.close();
//        database.createRestaurant(
//            name,
//            cnpj,
//            positionX,
//            positionY,
//            password
//        );
    }


    /**
     * Registers a new user in the application.
     *
     * @param name     The name of the user.
     * @param cpf      The CPF (Brazilian ID) of the user.
     * @param positionX The X coordinate of the user's position.
     * @param positionY The Y coordinate of the user's position.
     * @param password    The password of the user.
     */
    public void registerUser(
        String name,
        String cpf,
        int positionX,
        int positionY,
        String password
    ) {
        UserDAO userDAO = new UserDAO(entityManager);
        UserDB user = new UserDB(name, cpf, positionX, positionY, password);
        entityManager.getTransaction().begin();
        userDAO.create(user);
        entityManager.getTransaction().commit();
        entityManager.close();
//        database.createUser(
//            name,
//            cpf,
//            positionX,
//            positionY,
//            password
//        );
    }


    /**
     * Retrieves all restaurants from the database and stores them in the 'restaurants' field.
     */
    public void getAllRestaurant() {
        this.restaurants = database.getAllRestaurants();
    }


    /**
     * Sets the current user of the application.
     *
     * @param user The user to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
