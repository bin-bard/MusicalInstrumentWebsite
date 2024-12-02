package Servlet;

import com.instrumentwebsite.musicalinstruments.dao.CartDao;
import com.instrumentwebsite.musicalinstruments.dao.CartItemDao;
import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.dao.CustomerDao;  // Thêm import để sử dụng CustomerDao
import com.instrumentwebsite.musicalinstruments.model.Cart;
import com.instrumentwebsite.musicalinstruments.model.CartItem;
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.model.Customer;  // Thêm import để sử dụng Customer

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    private final CartDao cartDao = new CartDao();
    private final CartItemDao cartItemDao = new CartItemDao();
    private final ProductDao productDao = new ProductDao();
    private final CustomerDao customerDao = new CustomerDao();  // Thêm CustomerDao

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Long customerId = Long.parseLong(request.getParameter("customerId"));

        try {
            // Lấy Customer từ cơ sở dữ liệu
            Customer customer = customerDao.findById(customerId);
            if (customer == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found.");
                return;
            }

            // Tìm giỏ hàng của khách hàng
            Cart cart = cartDao.findCartByCustomerId(customerId).orElse(null);

            if (cart == null) {
                // Nếu không có giỏ hàng, tạo mới giỏ hàng và gán Customer cho giỏ hàng
                cart = new Cart();
                cart.setCustomer(customer);  // Gán Customer vào giỏ hàng
                cartDao.saveCart(cart);  // Lưu giỏ hàng mới
            }

            // Tìm sản phẩm trong cơ sở dữ liệu
            Product product = productDao.findById(productId);

            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            CartItem existingCartItem = cartDao.findCartItemByCartAndProduct(cart, product);

            if (existingCartItem != null) {
                // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                cartDao.updateCartItem(existingCartItem);  // Cập nhật CartItem
            } else {
                // Nếu sản phẩm chưa có, tạo mới CartItem và thêm vào giỏ hàng
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(cart);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
                cartDao.saveCartItem(newCartItem);  // Lưu CartItem mới
            }

            response.sendRedirect("cart.jsp");  // Chuyển hướng đến giỏ hàng
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error while adding to cart.");
        }
    }
}
