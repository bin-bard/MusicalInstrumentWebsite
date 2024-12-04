package com.instrumentwebsite.musicalinstruments.util;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.dao.UsersDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Admin;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebListener
public class DatabaseInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Database initialization started");
        try {
            createDefaultAdmin();
            logger.info("Database initialization completed successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during database initialization", e);
        }
    }

    private void createDefaultAdmin() {
        logger.info("Attempting to create default admin account");
        AccountDao accountDao = new AccountDao();
        UsersDao usersDao = new UsersDao();

        try {
            Account existingAdmin = accountDao.findByUsername("admin");
            if (existingAdmin == null) {
                logger.info("No existing admin found, creating new admin account");

                Account adminAccount = new Account("admin", "admin123");
                adminAccount.setVerified(true);

                Admin admin = new Admin("Admin", "User", "admin@harmonyhub.com", "0123456789");

                // Lưu Admin trước
                usersDao.save(admin);

                // Thiết lập mối quan hệ
                admin.setAccount(adminAccount);
                adminAccount.setUser(admin);

                // Sau đó lưu Account
                accountDao.save(adminAccount);

                logger.info("Admin account created successfully");
            } else {
                logger.info("Admin account already exists");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating admin account", e);
            throw new RuntimeException("Failed to create admin account", e);
        }
    }
}