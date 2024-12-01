package com.instrumentwebsite.musicalinstruments.service;

import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductService {

    private EntityManager entityManager;

    // Constructor: Khởi tạo EntityManager từ EntityManagerFactory
    public ProductService() {
        this.entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    // Lấy danh sách sản phẩm
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }
    
    public List<Product> searchProducts(String keyword) {
        String query = "SELECT p FROM Product p WHERE p.name LIKE :keyword OR p.description LIKE :keyword";
        return entityManager.createQuery(query, Product.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    // Đóng EntityManager
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
