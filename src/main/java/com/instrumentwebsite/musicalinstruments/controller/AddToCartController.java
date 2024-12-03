// src/main/java/com/instrumentwebsite/musicalinstruments/controller/AddToCartController.java
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AddToCart")
public class AddToCartController extends HttpServlet {

    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long productId = Long.parseLong(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Product product = productDao.findById(productId);

            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
                return;
            }

            HttpSession session = request.getSession();
            Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");

            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            if (cart.containsKey(productId)) {
                CartItem cartItem = cart.get(productId);
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cart.put(productId, cartItem);
            }

            // Redirect to the CartServlet
            response.sendRedirect(request.getContextPath() + "/Cart");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID or quantity.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding to cart.");
        }
    }
}