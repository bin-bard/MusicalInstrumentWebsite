package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AccountDao {

    // Sử dụng EntityManagerFactoryProvider để lấy EntityManagerFactory duy nhất
    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Lưu Account vào cơ sở dữ liệu
    public void save(Account account) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(account);  // Lưu đối tượng account vào cơ sở dữ liệu
            em.getTransaction().commit();  // Cam kết giao dịch
        } catch (Exception e) {
            // Log hoặc xử lý ngoại lệ nếu cần
            e.printStackTrace();
        }
    }

    // Tìm Account theo username
    public Account findByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            // Nếu không tìm thấy hoặc có lỗi, trả về null
            return null;
        }
    }
}
