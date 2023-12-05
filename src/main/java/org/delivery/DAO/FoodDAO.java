package org.delivery.DAO;

import org.delivery.EntitiesBD.FoodDB;

import javax.persistence.EntityManager;
import java.util.List;

public class FoodDAO {
    private final EntityManager entityManager;

    public FoodDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(FoodDB food) {
        this.entityManager.persist(food);
    }

    public List<FoodDB> getAllFoodsByRestaurant(int id) {
        String jpql = "SELECT p FROM Food p WHERE p.restaurant.id = :id AND p.active = :active";
        return entityManager.createQuery(jpql, FoodDB.class).setParameter("id", id).setParameter("active", true).getResultList();
    }

    public void deactivateFood(int id) {
        FoodDB food = entityManager.find(FoodDB.class, id); // 3 is ID of user.
        food.setActive(false);

        entityManager.getTransaction().begin();
        this.entityManager.merge(food);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
