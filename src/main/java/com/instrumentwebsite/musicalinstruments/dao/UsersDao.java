package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;

public class UsersDao {

    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Lưu một người dùng vào cơ sở dữ liệu
    public void save(Users user) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);  // Thêm người dùng mới
            em.getTransaction().commit();  // Cam kết giao dịch
        } catch (Exception e) {
            // Có thể thêm log hoặc xử lý ngoại lệ nếu cần
            e.printStackTrace();
        }
    }

    // Tìm người dùng theo email
    public Users findByEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            // Không tìm thấy hoặc có lỗi, trả về null
            return null;
        }
    }
}
