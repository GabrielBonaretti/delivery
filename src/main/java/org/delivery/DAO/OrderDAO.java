package org.delivery.DAO;

import org.delivery.EntitiesBD.OrderDB;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDAO {
    private final EntityManager entityManager;
    public OrderDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(OrderDB order) { this.entityManager.persist(order); }

    public OrderDB getLastOrder() {
        String jpql = "SELECT p FROM Order p ORDER BY p.id DESC";
        return entityManager.createQuery(jpql, OrderDB.class).setMaxResults(1).getSingleResult();
    }

    public List<OrderDB> getAllOrders() {
        String jpql = "SELECT p FROM Order p";
        return entityManager.createQuery(jpql, OrderDB.class).getResultList();
    }

}
