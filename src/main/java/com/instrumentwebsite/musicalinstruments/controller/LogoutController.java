package com.instrumentwebsite.musicalinstruments.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session hiện tại (không tạo mới nếu không tồn tại)
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Xóa tất cả các thuộc tính trong session
            session.invalidate();
        }

        // Chuyển hướng về trang chủ
        response.sendRedirect(request.getContextPath() + "/");
    }
}