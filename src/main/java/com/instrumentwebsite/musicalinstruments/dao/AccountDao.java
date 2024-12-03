package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDao {
    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());
    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Lưu Account vào cơ sở dữ liệu
    public void save(Account account) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error saving account", e);
            throw new RuntimeException("Could not save account", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Tìm Account theo username
    public Account findByUsername(String username) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery("SELECT a FROM Account a LEFT JOIN FETCH a.user WHERE a.username = :username", Account.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.INFO, "No account found with username: " + username);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding account by username", e);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Tìm Account theo email (thông qua User)
    public Account findByEmail(String email) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery("SELECT a FROM Account a JOIN a.user u WHERE u.email = :email", Account.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.INFO, "No account found with email: " + email);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding account by email", e);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Kiểm tra thông tin đăng nhập
    public Account validateLogin(String emailOrUsername, String password) {
        Account account = findByUsername(emailOrUsername);
        if (account == null) {
            account = findByEmail(emailOrUsername);
        }

        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    // Cập nhật Account
    public void update(Account account) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error updating account", e);
            throw new RuntimeException("Could not update account", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}