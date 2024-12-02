// OrderController.java
package com.instrumentwebsite.musicalinstruments.controller.Admin;

import com.instrumentwebsite.musicalinstruments.dao.OrderDao;
import com.instrumentwebsite.musicalinstruments.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin-orderlist", "/admin-updateorder"})
public class OrderController extends HttpServlet {
    private OrderDao orderDao = new OrderDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin-orderlist".equals(path)) {
        List<Object[]> orderList = orderDao.getAllOrders();
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/WEB-INF/views/admin/order-list.jsp").forward(request, response);
    } else if ("/admin-updateorder".equals(path)) {

            request.getRequestDispatcher("/WEB-INF/views/admin/update-order.jsp").forward(request, response);
//
//        // Lấy ID đơn hàng từ tham số yêu cầu
//        Long orderId = Long.parseLong(request.getParameter("id"));
//        Order order = orderDao.findById(orderId);
//        if (order != null) {
//            // Đặt đơn hàng vào request
//            request.setAttribute("order", order);
//            // Chuyển tiếp đến trang cập nhật đơn hàng
//            request.getRequestDispatcher("/WEB-INF/views/admin/update-order.jsp").forward(request, response);
//        }
    } else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    }

}