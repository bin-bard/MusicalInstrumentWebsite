package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.AccountDao;
import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.util.EmailTemplate;
import com.instrumentwebsite.musicalinstruments.util.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private final AccountDao accountDao = new AccountDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            Account account = accountDao.findByEmail(email);
            if (account != null) {
                // Tạo token và thời gian hết hạn (3 phút)
                String resetToken = UUID.randomUUID().toString();
                LocalDateTime expiry = LocalDateTime.now().plusMinutes(3);

                // Lưu token vào database
                account.setResetToken(resetToken);
                account.setResetTokenExpiry(expiry);
                accountDao.update(account);

                // Tạo link reset password
                String resetLink = request.getScheme() + "://" +
                        request.getServerName() + ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        "/reset-password?token=" + resetToken;

                // Gửi email
                String emailContent = EmailTemplate.getPasswordResetTemplate(resetLink);
                MailUtil.sendMail(email,
                        "thanhtai.emaillist@gmail.com",
                        "Password Reset Request",
                        emailContent,
                        true);

                request.setAttribute("success", "Password reset instructions have been sent to your email.");
            } else {
                request.setAttribute("error", "Email address not found.");
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/views/forgotpassword.jsp").forward(request, response);
    }
}