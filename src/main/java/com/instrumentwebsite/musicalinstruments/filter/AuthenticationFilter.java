package com.instrumentwebsite.musicalinstruments.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*") // Bảo vệ tất cả các URL bắt đầu bằng /admin/
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn) {
            // Người dùng đã đăng nhập, cho phép tiếp tục
            chain.doFilter(request, response);
        } else {
            // Chưa đăng nhập, chuyển hướng về trang đăng nhập
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
}