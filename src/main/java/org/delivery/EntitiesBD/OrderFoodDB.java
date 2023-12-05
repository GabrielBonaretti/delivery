package org.delivery.EntitiesBD;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class OrderFoodDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private FoodDB food;
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderDB order;
    @NotNull
    private int quantity;

    public OrderFoodDB(FoodDB food, OrderDB order, int quantity) {
        this.food = food;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderFoodDB() {}
}
