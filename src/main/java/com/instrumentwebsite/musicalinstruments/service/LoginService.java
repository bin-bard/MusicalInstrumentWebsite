package com.instrumentwebsite.musicalinstruments.service;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.dao.UsersDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Users;

public class LoginService {
    private AccountDao accountDao = new AccountDao();
    private UsersDao usersDao = new UsersDao();

    public boolean authenticate(String emailOrUsername, String password) throws Exception {
        // Kiểm tra đăng nhập bằng username
        Account account = accountDao.findByUsername(emailOrUsername);
        if (account != null && account.getPassword().equals(password)) {
            return true;
        }

        // Kiểm tra đăng nhập bằng email
        Users user = usersDao.findByEmail(emailOrUsername);
        if (user != null && user.getAccount().getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}