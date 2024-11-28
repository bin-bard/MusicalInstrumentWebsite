package com.instrumentwebsite.musicalinstruments.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/categorylist", "/addcategory"})
public class CategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/addcategory".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/add-category.jsp").forward(request, response);
        } else if ("/categorylist".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}