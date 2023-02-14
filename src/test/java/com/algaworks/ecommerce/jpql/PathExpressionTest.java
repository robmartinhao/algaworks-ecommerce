package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void usarPathExpressions() {
        String jpql = "select p.cliente.nome from Pedido p";

        final TypedQuery<Object> typedQuery = entityManager.createQuery(jpql, Object.class);

        List<Object> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
