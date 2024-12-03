// src/main/java/com/instrumentwebsite/musicalinstruments/controller/ShopController.java
package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.CategoryDao;
import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.model.Category;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/shop")
public class ShopController extends HttpServlet {
    private final ProductDao productDao = new ProductDao();
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdParam = request.getParameter("categoryId");
        List<Product> products;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            try {
                Long categoryId = Long.parseLong(categoryIdParam);
                products = productDao.findByCategory(categoryId);  // Lọc theo category
            } catch (NumberFormatException e) {
                // Nếu không phải là số hợp lệ, lấy tất cả sản phẩm
                products = productDao.findAll();
            }
        } else {
            products = productDao.findAll();  // Nếu không có tham số categoryId, lấy tất cả sản phẩm
        }

        // Tải danh sách các category
        List<Category> categories = categoryDao.getAllCategories();

        // Đặt các thuộc tính vào request để truyền tới JSP
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("ctx", request.getContextPath());

        // Chuyển hướng tới trang shop.jsp
        request.getRequestDispatcher("/WEB-INF/views/shop.jsp").forward(request, response);
    }
}