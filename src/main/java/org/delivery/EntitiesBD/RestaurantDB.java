package org.delivery.EntitiesBD;

import com.sun.istack.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RestaurantDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String cnpj;
    @NotNull
    private int positionX;
    @NotNull
    private int positionY;
    @NotNull
    private String password;

    public RestaurantDB() {}

    public RestaurantDB(String name, String cnpj, int positionX, int positionY, String password) {
        this.name = name;
        this.cnpj = cnpj;
        this.positionX = positionX;
        this.positionY = positionY;
        this.password = password;
    }

    public String getName() {
        return name;
    }
}
