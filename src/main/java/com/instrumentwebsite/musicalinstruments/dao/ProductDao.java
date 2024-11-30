// src/main/java/com/instrumentwebsite/musicalinstruments/dao/ProductDao.java
package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAllProducts();
        if (products != null && !products.isEmpty()) {
            System.out.println("Products retrieved successfully:");
            for (Product product : products) {
                System.out.println(product.getName());
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
                System.out.println(product.getImageURL());
                System.out.println(product.getCategory().getName());
            }
        } else {
            System.out.println("No products found.");
        }
    }
}