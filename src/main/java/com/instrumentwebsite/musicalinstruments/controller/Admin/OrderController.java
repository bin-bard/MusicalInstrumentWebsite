// OrderController.java
package com.instrumentwebsite.musicalinstruments.controller.Admin;

import com.instrumentwebsite.musicalinstruments.dao.OrderDao;
import com.instrumentwebsite.musicalinstruments.model.Order;
import com.instrumentwebsite.musicalinstruments.model.OrderItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@WebServlet({"/admin-orderlist", "/admin-updateorder", "/admin-deleteproductinorder"})
public class OrderController extends HttpServlet {
    private OrderDao orderDao = new OrderDao();
    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin-orderlist".equals(path)) {
            List<Object[]> orderList = orderDao.getAllOrders();
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/WEB-INF/views/admin/order-list.jsp").forward(request, response);
        } else if ("/admin-updateorder".equals(path)) {
            Long orderId = Long.parseLong(request.getParameter("id"));
            Order order = orderDao.findById(orderId);
            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher("/WEB-INF/views/admin/update-order.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found.");
            }
        } else if ("/admin-deleteproductinorder".equals(path)) {
            try {
                Long orderId = Long.parseLong(request.getParameter("orderId"));
                Long productId = Long.parseLong(request.getParameter("productId"));

                orderDao.removeProductFromOrder(orderId, productId);

                response.sendRedirect(request.getContextPath() + "/admin-updateorder?id=" + orderId);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        logger.info("Handling POST request for path: " + path);

        if ("/admin-updateorder".equals(path)) {
            try {
                Long orderId = Long.parseLong(request.getParameter("orderId"));
                logger.info("Updating order with ID: " + orderId);
                Order order = orderDao.findById(orderId);
                if (order != null) {
                    String[] orderItemIds = request.getParameterValues("orderItemId");
                    String[] quantities = request.getParameterValues("quantities");
                    String status = request.getParameter("status");

                    if (orderItemIds != null && quantities != null && orderItemIds.length == quantities.length) {
                        BigDecimal grandTotal = BigDecimal.ZERO;

                        for (int i = 0; i < orderItemIds.length; i++) {
                            Long orderItemId = Long.parseLong(orderItemIds[i]);
                            int quantity = Integer.parseInt(quantities[i]);

                            // Find the corresponding OrderItem
                            boolean itemFound = false;
                            for (OrderItem item : order.getOrderItems()) {
                                if (item.getId().equals(orderItemId)) {
                                    logger.info("Updating quantity for OrderItem ID: " + orderItemId + " to " + quantity);
                                    item.setQuantity(quantity);
                                    grandTotal = grandTotal.add(item.getUnitPrice().multiply(BigDecimal.valueOf(quantity)));
                                    itemFound = true;
                                    break;
                                }
                            }

                            if (!itemFound) {
                                logger.warning("OrderItem with ID " + orderItemId + " not found in order.");
                                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "OrderItem not found.");
                                return;
                            }
                        }

                        // Update order status and total amount
                        order.setStatus(Order.OrderStatus.valueOf(status));
                        order.setTotalAmount(grandTotal);

                        // Update the order in the database
                        orderDao.updateOrder(order);

                        response.sendRedirect(request.getContextPath() + "/admin-orderlist");
                    } else {
                        logger.warning("Invalid order items.");
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order items.");
                    }
                } else {
                    logger.warning("Order not found.");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found.");
                }
            } catch (Exception e) {
                logger.severe("Error updating order: " + e.getMessage());
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data.");
            }
        }
    }

}