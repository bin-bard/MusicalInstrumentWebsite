package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.model.Account;
import com.instrumentwebsite.musicalinstruments.model.Users;
import com.instrumentwebsite.musicalinstruments.service.RegistrationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {

    private RegistrationService registrationService;

    @Override
    public void init() throws ServletException {
        registrationService = new RegistrationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp); // Hiển thị form đăng ký
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
            registrationService.registerUser(firstName, lastName, email, phoneNumber, username, password);
            resp.sendRedirect("/index"); // Chuyển đến trang đăng nhập sau khi đăng ký thành công
        } catch (Exception e) {
            req.setAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}
