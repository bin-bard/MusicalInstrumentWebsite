package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.Cart;
import com.instrumentwebsite.musicalinstruments.model.CartItem;
import com.instrumentwebsite.musicalinstruments.model.Product;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class CartDao {

    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    // Tìm giỏ hàng theo customerId
    public Optional<Cart> findCartByCustomerId(Long customerId) {
    try (EntityManager em = emf.createEntityManager()) {
        return em.createQuery("SELECT c FROM Cart c WHERE c.customer.id = :customerId", Cart.class)
                 .setParameter("customerId", customerId)
                 .getResultStream()
                 .findFirst();
        }
    }

    // Lưu hoặc cập nhật giỏ hàng
    public void saveCart(Cart cart) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (cart.getId() == null) {
                em.persist(cart);  // Lưu giỏ hàng mới
            } else {
                em.merge(cart);  // Cập nhật giỏ hàng
            }
            em.getTransaction().commit();
        }
    }

 
    public void saveCartItem(CartItem cartItem) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(cartItem);  
            em.getTransaction().commit();
        }
    }

    // Tìm CartItem trong giỏ hàng theo Cart và Product
    public CartItem findCartItemByCartAndProduct(Cart cart, Product product) {
        try (EntityManager em = emf.createEntityManager()) {
            List<CartItem> result = em.createQuery(
                    "SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product", CartItem.class)
                    .setParameter("cart", cart)
                    .setParameter("product", product)
                    .getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

    // Cập nhật CartItem
    public void updateCartItem(CartItem cartItem) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(cartItem);  // Cập nhật CartItem
            em.getTransaction().commit();
        }
    }
}
