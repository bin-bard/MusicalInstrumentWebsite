/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.dao.ProductReviewDao;
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.model.ProductReview;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@WebServlet("/addReview")
public class AddReviewController extends HttpServlet {

    private ProductReviewDao productReviewDao = new ProductReviewDao();
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(request.getParameter("productId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // Lấy sản phẩm
        Product product = productDao.findById(productId);
        
        // Tạo đối tượng ProductReview
        ProductReview review = new ProductReview();
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        review.setReviewDate(LocalDateTime.now());

        // Lưu đánh giá vào cơ sở dữ liệu
        productReviewDao.save(review);

        // Chuyển hướng lại trang chi tiết sản phẩm
        response.sendRedirect("product-detail?id=" + productId);
    }
}
