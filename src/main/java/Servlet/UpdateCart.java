package Servlet;

import com.instrumentwebsite.musicalinstruments.dao.CartItemDao;
import com.instrumentwebsite.musicalinstruments.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UpdateCart", urlPatterns = {"/UpdateCart"})
public class UpdateCart extends HttpServlet {

    private final CartItemDao cartItemDao = new CartItemDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long cartItemId = Long.parseLong(request.getParameter("cartItemId"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            // Lấy CartItem từ cơ sở dữ liệu
            CartItem cartItem = cartItemDao.findById(cartItemId);

            if (cartItem != null) {
                // Cập nhật số lượng sản phẩm trong CartItem
                cartItem.setQuantity(newQuantity);
                cartItemDao.update(cartItem);  // Cập nhật CartItem

                // Chuyển hướng đến giỏ hàng
                response.sendRedirect("cart.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cart item not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while updating cart.");
        }
    }
}
