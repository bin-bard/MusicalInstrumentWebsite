package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {

    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Phương thức lưu sản phẩm
    public void save(Product product) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức tìm tất cả sản phẩm
    public List<Product> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Product p", Product.class)
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức tìm sản phẩm theo ID
    public Product findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức xóa sản phẩm theo ID
    public void deleteById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức tìm sản phẩm theo loại nhạc cụ (category)
    public List<Product> findByCategory(Long categoryId) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Product p WHERE p.category.id = :categoryId", Product.class)
                     .setParameter("categoryId", categoryId)
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}