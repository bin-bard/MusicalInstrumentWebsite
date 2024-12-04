package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.dao.CategoryDao;
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.model.Category;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/shop")
public class ShopController extends HttpServlet {

    private ProductDao productDao = new ProductDao();
    private CategoryDao categoryDao = new CategoryDao();
 

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");
        String priceRange = request.getParameter("priceRange");
        String categoryParam = request.getParameter("category");

        Long categoryId = null;
        if (categoryParam != null && !categoryParam.isEmpty()) {
            try {
                categoryId = Long.valueOf(categoryParam);
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi, giữ categoryId là null
                categoryId = null;
            }
        }

        // Pagination
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = 6;
        int offset = (page - 1) * pageSize;

        // Get products based on filters
        List<Product> productList = productDao.getProducts(search, categoryId, priceRange, offset, pageSize);
        int totalProducts = productDao.getTotalProductCount(search, categoryId, priceRange);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // Get all categories for the filter
        List<Category> categories = categoryDao.getAllCategories();

        // Set Attributes
        request.setAttribute("productList", productList);
        request.setAttribute("categories", categories);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/views/client/shop.jsp").forward(request, response);
    }

}
