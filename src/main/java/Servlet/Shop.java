package Servlet;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Shop", urlPatterns = {"/Shop"})
public class Shop extends HttpServlet {

    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdParam = request.getParameter("categoryId");
        List<Product> products;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            try {
                Long categoryId = Long.parseLong(categoryIdParam);
                products = productDao.findByCategory(categoryId);  // Lọc theo loại nhạc cụ
            } catch (NumberFormatException e) {
                // Nếu không phải số hợp lệ, lấy tất cả sản phẩm
                products = productDao.findAll();
            }
        } else {
            products = productDao.findAll();  // Nếu không có tham số categoryId, lấy tất cả sản phẩm
        }

        if (products != null && !products.isEmpty()) {
            request.setAttribute("products", products);
            request.getRequestDispatcher("/shop.jsp").forward(request, response);
        } else {
            response.getWriter().println("No products found.");
        }
    }
}
