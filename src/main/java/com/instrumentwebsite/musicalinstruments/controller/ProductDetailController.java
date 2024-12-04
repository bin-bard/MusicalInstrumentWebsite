package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.dao.ProductReviewDao;
import com.instrumentwebsite.musicalinstruments.dao.OrderDao; 
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.model.ProductReview;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product-detail")
public class ProductDetailController extends HttpServlet {

    private ProductDao productDao = new ProductDao();
    private ProductReviewDao productReviewDao = new ProductReviewDao();
    private OrderDao orderDao = new OrderDao(); // Thêm OrderDao để kiểm tra đơn hàng

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.valueOf(request.getParameter("id"));
        Long customerId = (Long) request.getSession().getAttribute("customerId"); // Giả sử bạn lưu customerId trong session


        Product product = productDao.findById(productId);

        // Lấy các đánh giá của sản phẩm
        List<ProductReview> reviews = productReviewDao.getReviewsByProductId(productId);

        // Kiểm tra xem người dùng đã mua sản phẩm chưa
        boolean hasPurchased = orderDao.hasPurchasedProduct(customerId, productId);

        // Đặt thông tin vào request
        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);
        request.setAttribute("hasPurchased", hasPurchased); // Thêm thông tin đã mua sản phẩm

        // Chuyển hướng đến trang chi tiết sản phẩm
        request.getRequestDispatcher("/WEB-INF/views/client/product-detail.jsp").forward(request, response);
    }
}
