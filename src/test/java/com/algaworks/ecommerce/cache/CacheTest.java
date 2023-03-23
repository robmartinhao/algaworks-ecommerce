package com.algaworks.ecommerce.cache;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CacheTest {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @Test
    public void buscarDoCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1:");
        Pedido pedido1 = entityManager1.find(Pedido.class, 1);
        System.out.println(pedido1.getId() + " - " + pedido1.getTotal());

        System.out.println("Buscando a partir da instância 1:");
        Pedido pedido2 = entityManager1.find(Pedido.class, 1);
        System.out.println(pedido2.getId() + " - " + pedido2.getTotal());

        entityManager1.close();
        entityManager2.close();
    }
}
