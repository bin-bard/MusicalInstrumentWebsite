// src/main/java/com/instrumentwebsite/musicalinstruments/dao/CategoryDao.java
package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Category;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import java.util.List;

public class CategoryDao {

    private EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    public List<Category> getAllCategories() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Category findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Category category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteCategory(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public boolean hasProducts(Long categoryId) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId", Long.class)
                    .setParameter("categoryId", categoryId)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public void update(Category category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.getAllCategories();
        if (categories != null && !categories.isEmpty()) {
            System.out.println("Categories retrieved successfully:");
            for (Category category : categories) {
                System.out.println("Category ID: " + category.getId());
                System.out.println("Category Name: " + category.getName());
            }
        } else {
            System.out.println("No categories found.");
        }
    }
}