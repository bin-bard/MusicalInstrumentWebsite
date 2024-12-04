package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.service.RegistrationService;
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

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
    private RegistrationService registrationService;

    @Override
    public void init() throws ServletException {
        registrationService = new RegistrationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Hiển thị form đăng ký
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Tạo verification token
            String verificationToken = UUID.randomUUID().toString();
            LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

            // Lưu thông tin tạm thời và gửi email xác nhận
            registrationService.createPendingRegistration(firstName, lastName, email,
                    phoneNumber, username, password, verificationToken, expiry);

            // Tạo verification link
            String verifyLink = req.getScheme() + "://" +
                    req.getServerName() + ":" +
                    req.getServerPort() +
                    req.getContextPath() +
                    "/verify-email?token=" + verificationToken;

            // Gửi email xác nhận
            String emailContent = EmailTemplate.getVerificationTemplate(username, verifyLink);
            MailUtil.sendMail(email,
                    "thanhtai.emaillist@gmail.com",
                    "Verify your HarmonyHub account",
                    emailContent,
                    true);

            // Chuyển đến trang thông báo
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/buyer/registration-pending.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Registration failed: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}