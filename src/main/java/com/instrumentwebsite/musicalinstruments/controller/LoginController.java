package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra nếu người dùng đã đăng nhập thì chuyển về trang chủ
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailOrUsername = req.getParameter("emailOrUsername");
        String password = req.getParameter("password");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            if (loginService.authenticate(emailOrUsername, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", emailOrUsername);

                // Thêm các thông tin khác vào session nếu cần
                // session.setAttribute("userId", user.getId());
                // session.setAttribute("userRole", user.getRole());

                resp.getWriter().write("{\"success\": true}");
            } else {
                resp.getWriter().write("{\"success\": false, \"error\": \"Invalid email/username or password\"}");
            }
        } catch (Exception e) {
            // Escape special characters in error message
            String errorMessage = e.getMessage().replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
            resp.getWriter().write("{\"success\": false, \"error\": \"" + errorMessage + "\"}");
        }
    }

    // Thêm phương thức logout nếu cần
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}