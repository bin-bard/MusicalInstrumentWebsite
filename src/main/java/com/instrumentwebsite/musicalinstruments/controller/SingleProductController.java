package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/SingleProduct"})
public class SingleProductController extends HttpServlet {
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        if (productId != null) {
            Product product = productDao.findById(Long.parseLong(productId));
            if (product != null) {
                request.setAttribute("product", product);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/single-product.jsp");
        dispatcher.forward(request, response);
    }
}