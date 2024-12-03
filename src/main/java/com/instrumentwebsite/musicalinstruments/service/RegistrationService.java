package com.instrumentwebsite.musicalinstruments.service;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.dao.UsersDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Users;

public class RegistrationService {

    private UsersDao usersDao = new UsersDao();
    private AccountDao accountDao = new AccountDao();

    public void registerUser(String firstName, String lastName, String email, String phoneNumber, String username, String password) throws Exception {
        if (usersDao.findByEmail(email) != null) {
            throw new Exception("Email already exists.");
        }

        if (accountDao.findByUsername(username) != null) {
            throw new Exception("Username already exists.");
        }

        // Tạo mới tài khoản và người dùng
        Account account = new Account(username, password);
        Users user = new Users(firstName, lastName, email, phoneNumber);
        user.setAccount(account);

        // Lưu vào cơ sở dữ liệu
        accountDao.save(account);
        usersDao.save(user);
    }
}
