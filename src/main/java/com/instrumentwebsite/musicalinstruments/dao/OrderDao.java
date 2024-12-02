// src/main/java/com/instrumentwebsite/musicalinstruments/dao/OrderDao.java
package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Order;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrderDao {

    private EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    public List<Object[]> getAllOrders() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT o.orderDate, c.firstName, c.lastName, o.id, o.status, o.paymentMethod, SUM(oi.quantity * oi.unitPrice) " +
                    "FROM Order o " +
                    "JOIN o.customer c " +
                    "JOIN o.orderItems oi " +
                    "GROUP BY o.orderDate, c.firstName, c.lastName, o.id, o.status, o.paymentMethod";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}