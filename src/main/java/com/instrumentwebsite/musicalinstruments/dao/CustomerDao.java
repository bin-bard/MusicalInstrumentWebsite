package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Customer;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CustomerDao {

    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Tìm Customer theo ID
    public Customer findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Customer.class, id);
        }
    }
}
