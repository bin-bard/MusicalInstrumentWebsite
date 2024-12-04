package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Category;
import com.instrumentwebsite.musicalinstruments.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class ProductDao {

    private EntityManagerFactory emf;

    public ProductDao() {
        this.emf = Persistence.createEntityManagerFactory("instrumentPU");
    }
    
    

    public List<Product> getAllProducts() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p JOIN FETCH p.category", Product.class)
                    .getResultList();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
          
        }
    }

    public Product findById(Long productId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Product.class, productId);
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
          }
        }
    }

    public void update(Product product) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
         
        }
    }

    public void save(Product product) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
           
        }
    }

    public void deleteProduct(Long productId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
           
        }
    }

    // Phân trang sản phẩm
    public List<Product> getProductsByPage(int offset, int limit) {
        EntityManager em = emf.createEntityManager();
        try {
            // Tạo câu truy vấn JPA để lấy danh sách sản phẩm phân trang
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p JOIN FETCH p.category", Product.class);
            query.setFirstResult(offset);  // Tính toán bắt đầu từ sản phẩm nào
            query.setMaxResults(limit);    // Số lượng sản phẩm mỗi trang
            return query.getResultList();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
          
        }
    }

    // Lấy tổng số sản phẩm trong cơ sở dữ liệu
    public int getTotalProductCount() {
        EntityManager em = emf.createEntityManager();
        try {
            // Truy vấn tổng số sản phẩm
            Long count = (Long) em.createQuery("SELECT COUNT(p) FROM Product p")
                    .getSingleResult();
            return count.intValue();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
          
        }
    }
    public List<Product> getProducts(String search, Long categoryId, String priceRange,  int offset, int limit) {
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder queryStr = new StringBuilder("SELECT p FROM Product p WHERE 1=1");

            // Search Filter
            if (search != null && !search.isEmpty()) {
                queryStr.append(" AND LOWER(p.name) LIKE LOWER(:search)");
            }

            // Category Filter
            if (categoryId != null) {
                queryStr.append(" AND p.category.id = :categoryId");
            }

            // Price Range Filter
            if (priceRange != null) {
                switch (priceRange) {
                    case "low":
                        queryStr.append(" AND p.price BETWEEN 0 AND 50");
                        break;
                    case "medium":
                        queryStr.append(" AND p.price BETWEEN 50 AND 200");
                        break;
                    case "high":
                        queryStr.append(" AND p.price > 200");
                        break;
                }
            }

           

            TypedQuery<Product> query = em.createQuery(queryStr.toString(), Product.class);

            // Set Parameters
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search + "%");
            }
            if (categoryId != null) {
                query.setParameter("categoryId", categoryId);
            }
           

            // Pagination
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            return query.getResultList();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
         
        }
    }

    public int getTotalProductCount(String search, Long categoryId, String priceRange) {
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder queryStr = new StringBuilder("SELECT COUNT(p) FROM Product p WHERE 1=1");

            // Same filters as `getProducts`
            if (search != null && !search.isEmpty()) {
                queryStr.append(" AND LOWER(p.name) LIKE LOWER(:search)");
            }
            if (categoryId != null) {
                queryStr.append(" AND p.category.id = :categoryId");
            }
            if (priceRange != null) {
                switch (priceRange) {
                    case "low":
                        queryStr.append(" AND p.price BETWEEN 0 AND 50");
                        break;
                    case "medium":
                        queryStr.append(" AND p.price BETWEEN 50 AND 200");
                        break;
                    case "high":
                        queryStr.append(" AND p.price > 200");
                        break;
                }
            }
           

            TypedQuery<Long> query = em.createQuery(queryStr.toString(), Long.class);

            // Set Parameters
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search + "%");
            }
            if (categoryId != null) {
                query.setParameter("categoryId", categoryId);
            }
           

            return query.getSingleResult().intValue();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
         
        }
    }

    public List<Product> getProductsByBrand(Long brandId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Product p WHERE p.brand.id = :brandId", Product.class)
                    .setParameter("brandId", brandId)
                    .getResultList();
        } finally {
             if (em != null) {
                em.close(); // Đóng EntityManager
            }
       
        }
    }
    
    

    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();

        // Create a new product
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("This is a test product.");
        product.setPrice(BigDecimal.valueOf(99.99));
        product.setImageURL("test-product.jpg");

        // Assuming you have a category with ID 1 in your database
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(1L);
        product.setCategory(category);

        // Save the product
        productDao.save(product);
    }
}
