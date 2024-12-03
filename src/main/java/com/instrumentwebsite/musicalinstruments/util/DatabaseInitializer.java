package com.instrumentwebsite.musicalinstruments.util;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.dao.UsersDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Admin;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        createDefaultAdmin();
    }

    private void createDefaultAdmin() {
        AccountDao accountDao = new AccountDao();
        UsersDao usersDao = new UsersDao();

        // Kiểm tra xem admin đã tồn tại chưa
        if (accountDao.findByUsername("admin") == null) {
            try {
                Account adminAccount = new Account("admin", "admin123"); // Nên mã hóa mật khẩu
                adminAccount.setVerified(true);

                Admin admin = new Admin("Admin", "User", "admin@harmonyhub.com", "0123456789");
                admin.setAccount(adminAccount);
                adminAccount.setUser(admin);

                accountDao.save(adminAccount);
                usersDao.save(admin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}