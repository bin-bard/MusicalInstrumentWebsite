package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.service.RegistrationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verify-email")
public class VerifyEmailController extends HttpServlet {
    private RegistrationService registrationService;

    @Override
    public void init() throws ServletException {
        registrationService = new RegistrationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String token = req.getParameter("token");

        try {
            registrationService.verifyEmail(token);
            req.getRequestDispatcher("/WEB-INF/views/buyer/verification-success.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/buyer/verification-failed.jsp").forward(req, resp);
        }
    }
}
