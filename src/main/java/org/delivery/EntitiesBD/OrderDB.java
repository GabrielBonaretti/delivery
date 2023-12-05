package org.delivery.EntitiesBD;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="orders")
public class OrderDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserDB user;
    @NotNull
    private LocalDate dataCadastro = LocalDate.now();
    @NotNull
    private double totalPrice;

    public OrderDB(UserDB user, double totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public OrderDB() {}
}
