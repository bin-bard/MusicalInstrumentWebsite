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

    // Tìm Account theo username với eager loading user
    public Account findByUsername(String username) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT a FROM Account a LEFT JOIN FETCH a.user WHERE a.username = :username",
                            Account.class)
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

    // Tìm Account theo email với eager loading user và account
    public Account findByEmail(String email) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT a FROM Account a JOIN FETCH a.user u WHERE u.email = :email",
                            Account.class)
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
            if (!account.isVerified()) {
                logger.log(Level.WARNING, "Account not verified: " + emailOrUsername);
                return null;
            }
            return account;
        }
        return null;
    }

    // Cập nhật Account với xử lý transaction
    public void update(Account account) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Account existingAccount = em.find(Account.class, account.getUsername());
            if (existingAccount == null) {
                throw new RuntimeException("Account not found");
            }

            // Cập nhật tất cả các trường
            existingAccount.setPassword(account.getPassword());
            existingAccount.setResetToken(account.getResetToken());
            existingAccount.setResetTokenExpiry(account.getResetTokenExpiry());
            existingAccount.setVerificationToken(account.getVerificationToken());
            existingAccount.setVerificationTokenExpiry(account.getVerificationTokenExpiry());
            existingAccount.setVerified(account.isVerified());

            em.merge(existingAccount);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error updating account", e);
            throw new RuntimeException("Could not update account: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Xóa token reset password
    public void clearResetToken(String username) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Account account = em.find(Account.class, username);
            if (account != null) {
                account.setResetToken(null);
                account.setResetTokenExpiry(null);
                em.merge(account);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error clearing reset token", e);
            throw new RuntimeException("Could not clear reset token", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Tìm account theo verification token với eager loading user
    public Account findByVerificationToken(String token) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT a FROM Account a LEFT JOIN FETCH a.user WHERE a.verificationToken = :token",
                            Account.class)
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.INFO, "No account found with verification token: " + token);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding account by verification token", e);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Account findByResetToken(String token) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT a FROM Account a LEFT JOIN FETCH a.user WHERE a.resetToken = :token",
                            Account.class)
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}