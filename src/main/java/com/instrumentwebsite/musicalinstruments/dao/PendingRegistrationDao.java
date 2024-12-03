package com.instrumentwebsite.musicalinstruments.dao;

import com.instrumentwebsite.musicalinstruments.model.PendingRegistration;
import com.instrumentwebsite.musicalinstruments.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PendingRegistrationDao {
    private static final Logger logger = Logger.getLogger(PendingRegistrationDao.class.getName());
    private final EntityManagerFactory emf = EntityManagerFactoryProvider.getEntityManagerFactory();

    public void save(PendingRegistration registration) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(registration);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error saving pending registration", e);
            throw new RuntimeException("Could not save pending registration", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public PendingRegistration findByToken(String token) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT p FROM PendingRegistration p WHERE p.verificationToken = :token",
                            PendingRegistration.class)
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(PendingRegistration registration) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            if (!em.contains(registration)) {
                registration = em.merge(registration);
            }
            em.remove(registration);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error deleting pending registration", e);
            throw new RuntimeException("Could not delete pending registration", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}