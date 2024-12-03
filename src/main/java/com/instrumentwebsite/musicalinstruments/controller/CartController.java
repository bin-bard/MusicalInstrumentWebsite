// src/main/java/com/instrumentwebsite/musicalinstruments/controller/CartController.java
package com.instrumentwebsite.musicalinstruments.controller;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.model.CartItem;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/Cart", "/removeFromCart", "/UpdateCart"})
public class CartController extends HttpServlet {

    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/removeFromCart".equals(path)) {
            // Xử lý xóa sản phẩm khỏi giỏ hàng
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null && !productIdStr.isEmpty()) {
                try {
                    Long productId = Long.parseLong(productIdStr);
                    HttpSession session = request.getSession();
                    Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");

                    if (cart != null && cart.containsKey(productId)) {
                        cart.remove(productId);
                        session.setAttribute("cart", cart);
                    }

                    response.sendRedirect(request.getContextPath() + "/Cart");
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing product ID.");
            }
        } else {
            // Xử lý hiển thị giỏ hàng
            HttpSession session = request.getSession();
            Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");

            if (cart == null) {
                cart = new HashMap<>();
            }

            // Cập nhật thông tin sản phẩm mới nhất từ cơ sở dữ liệu
            for (CartItem cartItem : cart.values()) {
                Product latestProduct = productDao.findById(cartItem.getProduct().getId());
                if (latestProduct != null) {
                    cartItem.setProduct(latestProduct);
                }
            }

            // Tính tổng tiền
            BigDecimal total = BigDecimal.ZERO;
            for (CartItem cartItem : cart.values()) {
                BigDecimal itemTotal = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
                total = total.add(itemTotal);
            }

            request.setAttribute("cart", cart);
            request.setAttribute("total", total.toString());
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/UpdateCart".equals(path)) {
            HttpSession session = request.getSession();
            Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");

            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            // Lặp qua tất cả các tham số để tìm các tham số bắt đầu bằng "quantity_"
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                String paramName = entry.getKey();
                if (paramName.startsWith("quantity_")) {
                    String productIdStr = paramName.substring("quantity_".length());
                    String quantityStr = entry.getValue()[0];

                    if (productIdStr != null && !productIdStr.isEmpty() && quantityStr != null && !quantityStr.isEmpty()) {
                        try {
                            Long productId = Long.parseLong(productIdStr);
                            int quantity = Integer.parseInt(quantityStr);

                            if (cart.containsKey(productId)) {
                                if (quantity > 0) {
                                    cart.get(productId).setQuantity(quantity);
                                } else {
                                    // Nếu số lượng bằng hoặc nhỏ hơn 0, loại bỏ sản phẩm khỏi giỏ hàng
                                    cart.remove(productId);
                                }
                            }
                        } catch (NumberFormatException e) {
                            // Ghi log lỗi hoặc xử lý theo cách bạn muốn
                            e.printStackTrace();
                            // Bạn có thể thêm một thông báo lỗi để hiển thị cho người dùng
                        }
                    } else {
                        // Xử lý khi tham số bị thiếu hoặc rỗng
                        // Bạn có thể ghi log hoặc bỏ qua
                        System.err.println("Tham số quantity hoặc productId bị thiếu.");
                    }
                }
            }

            session.setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/Cart");
        }
    }
}