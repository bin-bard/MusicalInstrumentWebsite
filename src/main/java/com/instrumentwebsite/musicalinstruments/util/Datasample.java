// src/main/java/com/instrumentwebsite/musicalinstruments/util/DataInitializer.java
package com.instrumentwebsite.musicalinstruments.util;

import com.instrumentwebsite.musicalinstruments.dao.*;
import com.instrumentwebsite.musicalinstruments.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Datasample {

    public static void main(String[] args) {
        EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Create sample categories
            Category category1 = new Category();
            category1.setName("Guitars");
            Category category2 = new Category();
            category2.setName("Pianos");
            Category category3 = new Category();
            category3.setName("Drums");

            em.persist(category1);
            em.persist(category2);
            em.persist(category3);

            // Create sample products
            Product product1 = new Product();
            product1.setName("Acoustic Guitar");
            product1.setDescription("A high-quality acoustic guitar.");
            product1.setPrice(new BigDecimal("299.99"));
            product1.setCategory(category1);

            Product product2 = new Product();
            product2.setName("Electric Piano");
            product2.setDescription("A versatile electric piano.");
            product2.setPrice(new BigDecimal("499.99"));
            product2.setCategory(category2);

            Product product3 = new Product();
            product3.setName("Wood Drum");
            product3.setDescription("A versatile Drum.");
            product3.setPrice(new BigDecimal("799.99"));
            product3.setCategory(category3);

            em.persist(product1);
            em.persist(product2);
            em.persist(product3);

            // Create sample customers
            Customer customer1 = new Customer();
            customer1.setFirstName("Alice");
            customer1.setLastName("Johnson");
            customer1.setEmail("alice.johnson@example.com");
            customer1.setPassword("password123");

            Customer customer2 = new Customer();
            customer2.setFirstName("Bob");
            customer2.setLastName("Brown");
            customer2.setEmail("bob.brown@example.com");
            customer2.setPassword("password456");

            em.persist(customer1);
            em.persist(customer2);

            // Create sample addresses
            Address address1 = new Address();
            address1.setStreet("123 Main St");
            address1.setCity("Springfield");
            address1.setState("IL");
            address1.setZipCode("62701");
            address1.setCountry("USA");
            address1.setCustomer(customer1);
            Address address2 = new Address();
            address2.setStreet("456 Elm St");
            address2.setCity("Shelbyville");
            address2.setState("IL");
            address2.setZipCode("62702");
            address2.setCountry("USA");
            address2.setCustomer(customer2);

            em.persist(address1);
            em.persist(address2);

            // Create sample orders
            Order order1 = new Order();
            order1.setOrderDate(LocalDateTime.now());
            order1.setTotalAmount(new BigDecimal("299.99"));
            order1.setStatus(Order.OrderStatus.PENDING);
            order1.setPaymentMethod(Order.PaymentMethod.CREDIT_CARD);
            order1.setCustomer(customer1);
            order1.setShippingAddress(address1);

            Order order2 = new Order();
            order2.setOrderDate(LocalDateTime.now());
            order2.setTotalAmount(new BigDecimal("499.99"));
            order2.setStatus(Order.OrderStatus.PENDING);
            order2.setPaymentMethod(Order.PaymentMethod.CASH_ON_DELIVERY);
            order2.setCustomer(customer2);
            order2.setShippingAddress(address2);

            em.persist(order1);
            em.persist(order2);

            // Create sample order items
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setQuantity(1);
            orderItem1.setUnitPrice(new BigDecimal("299.99"));
            orderItem1.setOrder(order1);
            orderItem1.setProduct(product1);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setQuantity(1);
            orderItem2.setUnitPrice(new BigDecimal("499.99"));
            orderItem2.setOrder(order2);
            orderItem2.setProduct(product2);

            em.persist(orderItem1);
            em.persist(orderItem2);

            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}