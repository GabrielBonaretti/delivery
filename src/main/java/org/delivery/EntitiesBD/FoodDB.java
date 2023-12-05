package org.delivery.EntitiesBD;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class FoodDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private RestaurantDB restaurant;
    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private boolean active;

    public FoodDB() {}

    public FoodDB(RestaurantDB restaurant, String name, double price, boolean active) {
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
