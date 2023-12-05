package org.delivery.EntitiesBD;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String cpf;
    @NotNull
    private int positionX;
    @NotNull
    private int positionY;
    @NotNull
    private String password;

    public UserDB() {}

    public UserDB(String name, String cpf, int positionX, int positionY, String password) {
        this.name = name;
        this.cpf = cpf;
        this.positionX = positionX;
        this.positionY = positionY;
        this.password = password;
    }
}
