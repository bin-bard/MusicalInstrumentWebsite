package com.instrumentwebsite.musicalinstruments.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import com.instrumentwebsite.musicalinstruments.model.ProductReview;
import java.util.List;

public class ProductReviewDao { 
    private EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Phương thức lưu đánh giá mới
    public void save(ProductReview review) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(review); // Lưu đánh giá
            em.getTransaction().commit(); // Xác nhận giao dịch
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Nếu có lỗi, rollback
            }
            e.printStackTrace();
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi thực hiện xong
        }
    }

    // Phương thức lấy tất cả đánh giá của sản phẩm theo productId
    public List<ProductReview> getReviewsByProductId(Long productId) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT r FROM ProductReview r WHERE r.product.id = :productId";
            return em.createQuery(query, ProductReview.class)
                     .setParameter("productId", productId)
                     .getResultList(); // Trả về danh sách đánh giá của sản phẩm
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi thực hiện xong
        }
    }

    // Phương thức để lấy một đánh giá cụ thể theo ID (nếu cần)
    public ProductReview getReviewById(Long reviewId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ProductReview.class, reviewId); // Tìm kiếm đánh giá theo ID
        } finally {
            em.close(); // Đảm bảo đóng EntityManager sau khi thực hiện xong
        }
    }
}
