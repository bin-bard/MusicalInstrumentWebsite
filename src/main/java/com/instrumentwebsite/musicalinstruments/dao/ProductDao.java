// src/main/java/com/instrumentwebsite/musicalinstruments/dao/ProductDao.java
package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Category;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

public class ProductDao {

    private EntityManagerFactory emf;

    public ProductDao() {
        this.emf = Persistence.createEntityManagerFactory("instrumentPU");
    }

    public List<Product> getAllProducts() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p JOIN FETCH p.category", Product.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

public Product findById(Long productId) {
    EntityManager em = emf.createEntityManager();
    try {
        return em.find(Product.class, productId);
    } finally {
        em.close();
    }
}

    public void update(Product product) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void save(Product product) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteProduct(Long productId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();

        // Create a new product
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("This is a test product.");
        product.setPrice(BigDecimal.valueOf(99.99));
        product.setImageURL("test-product.jpg");

        // Assuming you have a category with ID 1 in your database
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(1L);
        product.setCategory(category);

        // Save the product
        productDao.save(product);
    }



}