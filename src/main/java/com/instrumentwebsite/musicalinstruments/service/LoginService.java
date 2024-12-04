package com.instrumentwebsite.musicalinstruments.service;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.dao.UsersDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Users;

public class LoginService {
    private AccountDao accountDao = new AccountDao();
    private UsersDao usersDao = new UsersDao();

    public boolean authenticate(String emailOrUsername, String password) throws Exception {
        Account account = accountDao.findByUsername(emailOrUsername);
        if (account == null) {
            account = accountDao.findByEmail(emailOrUsername);
        }

        if (account != null && account.getPassword().equals(password)) {
            if (!account.isVerified()) {
                throw new Exception("Please verify your email address before logging in.");
            }
            return true;
        }
        return false;
    }
}