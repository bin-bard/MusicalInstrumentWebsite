package com.instrumentwebsite.musicalinstruments.controller.Admin;

import com.instrumentwebsite.musicalinstruments.dao.ProductDao;
import com.instrumentwebsite.musicalinstruments.dao.CategoryDao;
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet({"/admin-product", "/admin-addproduct", "/admin-updateproduct","/admin-deleteproduct"})
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class ProductController extends HttpServlet {
    private ProductDao productDao = new ProductDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/admin-addproduct".equals(path)) {
            // Load categories to populate the dropdown
            request.setAttribute("categories", categoryDao.getAllCategories());
            request.getRequestDispatcher("/WEB-INF/views/admin/add-product.jsp").forward(request, response);
        } else if ("/admin-product".equals(path)) {
            List<Product> productList = productDao.getAllProducts();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(request, response);
        }else if ("/admin-updateproduct".equals(path)) {
            // Lấy ID sản phẩm từ tham số yêu cầu
            Long productId = Long.parseLong(request.getParameter("id"));
            Product product = productDao.findById(productId);
            if (product != null) {
                // Đặt sản phẩm và danh mục vào request
                request.setAttribute("product", product);
                request.setAttribute("categories", categoryDao.getAllCategories());
                // Chuyển tiếp đến trang cập nhật sản phẩm
                request.getRequestDispatcher("/WEB-INF/views/admin/update-product.jsp").forward(request, response);
        }
        } else if ("/admin-deleteproduct".equals(path)) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    Long productId = Long.parseLong(idParam);
                    productDao.deleteProduct(productId);
                    response.sendRedirect(request.getContextPath() + "/admin-product");
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu ID sản phẩm");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String path = request.getServletPath();
        if ("/admin-addproduct".equals(path)) {
            // Handle form submission to add a new product

            // Retrieve form data
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double price = Double.parseDouble(request.getParameter("price"));
            Long categoryId = Long.parseLong(request.getParameter("categoryId"));

            // Handle image upload
            Part filePart = request.getPart("image");
            String imageURL = null;
            if (filePart != null && filePart.getSize() > 0) {
                // Save the uploaded file and get its URL
                String fileName = new File(filePart.getSubmittedFileName()).getName();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                filePart.write(uploadPath + File.separator + fileName);
                imageURL = "uploads/" + fileName;
            }

            // Create Product object
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(BigDecimal.valueOf(price));
            product.setImageURL(imageURL);

            // Set Category
            Category category = categoryDao.findById(categoryId);
            product.setCategory(category);

            // Save Product
            productDao.save(product);

            // Redirect to product list
            response.sendRedirect("admin-product");

        } else if ("/admin-updateproduct".equals(path)) {
            // Xử lý cập nhật sản phẩm
            Long productId = Long.parseLong(request.getParameter("id"));
            Product product = productDao.findById(productId);
            if (product != null) {
                // Lấy dữ liệu từ biểu mẫu
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                Double price = Double.parseDouble(request.getParameter("price"));
                Long categoryId = Long.parseLong(request.getParameter("categoryId"));

                // Xử lý tải hình ảnh
                Part filePart = request.getPart("image");
                String imageURL = product.getImageURL(); // Giữ URL hình ảnh hiện tại theo mặc định
                if (filePart != null && filePart.getSize() > 0) {
                    // Lưu tệp tải lên và lấy URL của nó
                    String fileName = new File(filePart.getSubmittedFileName()).getName();
                    String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    filePart.write(uploadPath + File.separator + fileName);
                    imageURL = "uploads/" + fileName;
                }

                // Cập nhật chi tiết sản phẩm
                product.setName(name);
                product.setDescription(description);
                product.setPrice(BigDecimal.valueOf(price));
                product.setImageURL(imageURL);

                // Cập nhật danh mục
                Category category = categoryDao.findById(categoryId);
                product.setCategory(category);

                // Lưu sản phẩm đã cập nhật
                productDao.update(product);

                // Chuyển hướng đến danh sách sản phẩm
                response.sendRedirect("admin-product");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}