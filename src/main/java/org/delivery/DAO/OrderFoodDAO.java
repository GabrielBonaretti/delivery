package org.delivery.DAO;

import org.delivery.EntitiesBD.OrderFoodDB;
import org.delivery.EntitiesBD.RestaurantDB;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderFoodDAO {
    private final EntityManager entityManager;
    public OrderFoodDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(OrderFoodDB orderFood) {
        this.entityManager.persist(orderFood);
    }

    public List<OrderFoodDB> getAllOrderFoodsByOrderId(int id){
        String jpql = "SELECT p FROM OrderFood p WHERE p.order.id = :id";
        return entityManager.createQuery(jpql, OrderFoodDB.class).setParameter("id", id).getResultList();
    }

    public String getRestaurantNameByOrder(int id) {
        String jpql = "SELECT p.food.restaurant FROM OrderFood p WHERE p.order.id = :id";
        return entityManager.createQuery(jpql, RestaurantDB.class).setParameter("id", id).getSingleResult().getName();
    }

}
