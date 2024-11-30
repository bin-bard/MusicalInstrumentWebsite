package com.instrumentwebsite.musicalinstruments.controller.Admin;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin-product", "/admin-addproduct"})
public class ProductController extends HttpServlet {
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/admin-addproduct".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/add-product.jsp").forward(request, response);
        } else if ("/admin-product".equals(path)) {

            List<Product> productList = productDao.getAllProducts();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(request, response);

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}