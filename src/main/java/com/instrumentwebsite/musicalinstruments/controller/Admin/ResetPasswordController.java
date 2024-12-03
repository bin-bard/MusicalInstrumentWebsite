package com.instrumentwebsite.musicalinstruments.controller.Admin;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private final AccountDao accountDao = new AccountDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");

        try {
            Account account = accountDao.findByResetToken(token);
            if (account == null || account.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
                request.setAttribute("error", "Invalid or expired reset token");
                request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password-error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("token", token);
            request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password-error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match");
                request.setAttribute("token", token);
                request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password.jsp").forward(request, response);
                return;
            }

            Account account = accountDao.findByResetToken(token);
            if (account == null || account.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
                request.setAttribute("error", "Invalid or expired reset token");
                request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password-error.jsp").forward(request, response);
                return;
            }

            account.setPassword(newPassword);
            account.setResetToken(null);
            account.setResetTokenExpiry(null);
            accountDao.update(account);

            request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password-success.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.setAttribute("token", token);
            request.getRequestDispatcher("/WEB-INF/views/buyer/reset-password.jsp").forward(request, response);
        }

    }

}