package src.Entities;

import src.Database.Database;

import java.util.ArrayList;

public class Application {
    public ArrayList<Restaurant> restaurants;
    public User user;
    public Order order;

    public Database database;

    public Application() {
        this.database = new Database();
    }

    public void registerRestaurant(
        String name,
        String cnpj,
        int posicaoX,
        int posicaoY,
        String senha
    ) {
        database.createRestaurant(
            name,
            cnpj,
            posicaoX,
            posicaoY,
            senha
        );

    }

    public void registerUser(
        String name,
        String cpf,
        int posicaoX,
        int posicaoY,
        String senha
    ) {
        database.createUser(
            name,
            cpf,
            posicaoX,
            posicaoY,
            senha
        );
    }

    public void getAllRestaurant() {
        this.restaurants = database.getAllRestaurants();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
