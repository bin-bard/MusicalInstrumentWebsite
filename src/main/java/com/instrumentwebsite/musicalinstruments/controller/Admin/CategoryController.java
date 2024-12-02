// src/main/java/com/instrumentwebsite/musicalinstruments/controller/Admin/CategoryController.java
package com.instrumentwebsite.musicalinstruments.controller.Admin;

import com.instrumentwebsite.musicalinstruments.dao.CategoryDao;
import com.instrumentwebsite.musicalinstruments.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin-addcategory", "/admin-categorylist", "/admin-deletecategory", "/admin-updatecategory"})
public class CategoryController extends HttpServlet {

    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin-addcategory".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/add-category.jsp").forward(request, response);
        } else if ("/admin-categorylist".equals(path)) {
            request.setAttribute("categories", categoryDao.getAllCategories());
            request.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(request, response);
        } else if ("/admin-deletecategory".equals(path)) {
            Long categoryId = Long.parseLong(request.getParameter("id"));

            if (categoryDao.hasProducts(categoryId)) {
                request.setAttribute("errorMessage", "Không thể xóa danh mục vì có sản phẩm liên quan.");
                request.setAttribute("categories", categoryDao.getAllCategories());
                request.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(request, response);
            } else {
                categoryDao.deleteCategory(categoryId);
                response.sendRedirect("admin-categorylist");
            }
        } else if ("/admin-updatecategory".equals(path)) {
            Long categoryId = Long.parseLong(request.getParameter("id"));
            Category category = categoryDao.findById(categoryId);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/WEB-INF/views/admin/update-category.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin-addcategory".equals(path)) {
            String name = request.getParameter("name");
            Category category = new Category();
            category.setName(name);
            categoryDao.save(category);
            response.sendRedirect("admin-categorylist");
        } else if ("/admin-updatecategory".equals(path)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            Category category = categoryDao.findById(id);
            category.setName(name);
            categoryDao.update(category);
            response.sendRedirect("admin-categorylist");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}