package com.instrumentwebsite.musicalinstruments.service;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.dao.UsersDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Buyer;
import com.instrumentwebsite.musicalinstruments.model.PendingRegistration;
import com.instrumentwebsite.musicalinstruments.dao.PendingRegistrationDao;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;

public class RegistrationService {
    private UsersDao usersDao = new UsersDao();
    private AccountDao accountDao = new AccountDao();
    private PendingRegistrationDao pendingRegistrationDao = new PendingRegistrationDao();

    public void createPendingRegistration(String firstName, String lastName, String email,
                                          String phoneNumber, String username, String password,
                                          String verificationToken, LocalDateTime expiry) throws Exception {
        // Kiểm tra email và username đã tồn tại chưa
        if (usersDao.findByEmail(email) != null) {
            throw new Exception("Email already exists.");
        }
        if (accountDao.findByUsername(username) != null) {
            throw new Exception("Username already exists.");
        }

        // Lưu thông tin vào bảng pending_registrations
        PendingRegistration registration = new PendingRegistration(
                firstName, lastName, email, phoneNumber,
                username, password, verificationToken, expiry);

        pendingRegistrationDao.save(registration);
    }

    public void verifyEmail(String token) throws Exception {
        // Tìm thông tin đăng ký
        PendingRegistration registration = pendingRegistrationDao.findByToken(token);
        if (registration == null) {
            throw new Exception("Invalid verification token");
        }

        if (registration.getVerificationTokenExpiry().isBefore(LocalDateTime.now())) {
            pendingRegistrationDao.delete(registration);
            throw new Exception("Verification token has expired. Please register again.");
        }

        EntityManager em = null;
        try {
            em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            // Tạo account và user thật
            Account account = new Account(registration.getUsername(), registration.getPassword());
            account.setVerified(true);

            Buyer buyer = new Buyer(
                    registration.getFirstName(),
                    registration.getLastName(),
                    registration.getEmail(),
                    registration.getPhoneNumber()
            );
            buyer.setAccount(account);
            account.setUser(buyer);

            em.persist(account);
            em.persist(buyer);

            // Xóa pending registration
            pendingRegistrationDao.delete(registration);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Could not create account: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}