package org.delivery.DAO;

import org.delivery.EntitiesBD.RestaurantDB;

import javax.persistence.EntityManager;
import java.util.List;

public class RestaurantDAO {
    private final EntityManager entityManager;

    public RestaurantDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(RestaurantDB restaurant) {
        this.entityManager.persist(restaurant);
    }

    public RestaurantDB login(String name, String password) {
        String jpql = "SELECT p FROM Restaurant p WHERE p.name = :name AND p.password = :password";
        return entityManager.createQuery(jpql, RestaurantDB.class).setParameter("name", name).setParameter("password", password).getSingleResult();
    }

    public List<RestaurantDB> getAll() {
        String jpql = "SELECT p FROM Restaurant p";
        return entityManager.createQuery(jpql, RestaurantDB.class).getResultList();
    }

    public RestaurantDB getRestaurantById(int id) {
        return entityManager.find(RestaurantDB.class, id);
    }
}
