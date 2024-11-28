package com.instrumentwebsite.musicalinstruments.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/brandlist", "/addbrand"})
public class BrandController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/addbrand".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/add-brand.jsp").forward(request, response);
        } else if ("/brandlist".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/brand-list.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}