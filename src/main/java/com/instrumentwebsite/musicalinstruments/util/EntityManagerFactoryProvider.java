package com.instrumentwebsite.musicalinstruments.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryProvider {

    // Singleton: chỉ tạo một lần EntityManagerFactory
     private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("instrumentPU");

    // Trả về EntityManagerFactory để sử dụng
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    // Đóng EntityManagerFactory khi ứng dụng dừng
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
