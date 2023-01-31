package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
//        entityManager.find(Pedido.class, 1);

        TypedQuery<Pedido> query = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = query.getSingleResult();
        Assert.assertNotNull(pedido);

        List<Pedido> listaPedidos = query.getResultList();
        Assert.assertFalse(listaPedidos.isEmpty());
    }

    @Test
    public void mostrarDiferencaQueries() {
        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedido2);

    }
}
