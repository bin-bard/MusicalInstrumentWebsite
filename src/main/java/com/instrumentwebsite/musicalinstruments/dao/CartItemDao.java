package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.CartItem;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CartItemDao {

    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Lấy CartItem theo ID
    public CartItem findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(CartItem.class, id);
        }
    }

    // Cập nhật CartItem
    public void update(CartItem cartItem) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(cartItem);
            em.getTransaction().commit();
        }
    }
}