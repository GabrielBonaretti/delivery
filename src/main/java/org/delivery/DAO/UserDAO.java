package org.delivery.DAO;

import org.delivery.EntitiesBD.UserDB;

import javax.persistence.EntityManager;

public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(UserDB user) {
        this.entityManager.persist(user);
    }

    public UserDB login(String name, String password) {
        String jpql = "SELECT p FROM User p WHERE p.name = :name AND p.password = :password";
        return entityManager.createQuery(jpql, UserDB.class).setParameter("name", name).setParameter("password", password).getSingleResult();
    }
}
