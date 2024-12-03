// src/main/java/com/instrumentwebsite/musicalinstruments/dao/OrderDao.java
package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Category;
import com.instrumentwebsite.musicalinstruments.model.Order;
import com.instrumentwebsite.musicalinstruments.model.OrderItem;
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

public class OrderDao {

    private EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    public List<Object[]> getAllOrders() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT o.orderDate, c.firstName, c.lastName, o.id, o.status, o.paymentMethod, SUM(oi.quantity * oi.unitPrice) " +
                    "FROM Order o " +
                    "JOIN o.customer c " +
                    "JOIN o.orderItems oi " +
                    "GROUP BY o.orderDate, c.firstName, c.lastName, o.id, o.status, o.paymentMethod";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Order findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT o FROM Order o " +
                    "LEFT JOIN FETCH o.customer c " +
                    "LEFT JOIN FETCH o.shippingAddress " +
                    "LEFT JOIN FETCH o.orderItems oi " + // Thêm dòng này
                    "WHERE o.id = :id";
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("id", id);
            Order order = query.getSingleResult();

            // Khởi tạo thủ công các collection nếu cần
            order.getCustomer().getAddresses().size();
            order.getOrderItems().size();
            for (OrderItem item : order.getOrderItems()) {
                item.getProduct().getName(); // Đảm bảo rằng product cũng được fetch nếu cần
            }

            return order;
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy đơn hàng với ID: " + id);
            return null;
        } finally {
            em.close();
        }
    }

    // OrderDao.java
    public void updateOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void removeProductFromOrder(Long orderId, Long productId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                OrderItem itemToRemove = null;
                for (OrderItem item : order.getOrderItems()) {
                    if (item.getProduct().getId().equals(productId)) {
                        itemToRemove = item;
                        break;
                    }
                }
                if (itemToRemove != null) {
                    order.getOrderItems().remove(itemToRemove);
                    em.remove(em.contains(itemToRemove) ? itemToRemove : em.merge(itemToRemove));

                    // Recalculate total amount
                    BigDecimal totalAmount = BigDecimal.ZERO;
                    for (OrderItem item : order.getOrderItems()) {
                        totalAmount = totalAmount.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                    }
                    order.setTotalAmount(totalAmount);
                    em.merge(order);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        Long orderId = 1L; // Replace with a valid order ID from your database
        Order order = orderDao.findById(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Total Amount: " + order.getTotalAmount());
            System.out.println("Customer Name: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
            System.out.println("Customer Email: " + order.getCustomer().getEmail());
            System.out.println("Customer Addresses:");
            order.getCustomer().getAddresses().forEach(address -> {
                System.out.println("  Street: " + address.getStreet());
                System.out.println("  City: " + address.getCity());
                System.out.println("  State: " + address.getState());
                System.out.println("  Zip Code: " + address.getZipCode());
                System.out.println("  Country: " + address.getCountry());
            });
            System.out.println("Shipping Address:");
            System.out.println("  Street: " + order.getShippingAddress().getStreet());
            System.out.println("  City: " + order.getShippingAddress().getCity());
            System.out.println("  State: " + order.getShippingAddress().getState());
            System.out.println("  Zip Code: " + order.getShippingAddress().getZipCode());
            System.out.println("  Country: " + order.getShippingAddress().getCountry());
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("Payment Method: " + order.getPaymentMethod());
            System.out.println("Order Items:");
            order.getOrderItems().forEach(item -> {
                System.out.println("  Product Name: " + item.getProduct().getName());
                System.out.println("  Quantity: " + item.getQuantity());
                System.out.println("  Unit Price: " + item.getUnitPrice());
                System.out.println("  Subtotal: " + BigDecimal.valueOf(item.getQuantity()).multiply(item.getUnitPrice()));
            });
        } else {
            System.out.println("Order not found.");
        }
    }


}