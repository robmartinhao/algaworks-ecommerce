package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void buscarPedidoComProdutoEspecifico() {

        String jpql = "select p from Pedido p inner join p.itens i where i.id.produtoId = 1";

        TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = query.getResultList();

        Assert.assertTrue(lista.size() == 2);
    }

    @Test
    public void usarPathExpressions() {
        String jpql = "select p.cliente.nome from Pedido p";

        TypedQuery<Object> typedQuery = entityManager.createQuery(jpql, Object.class);

        List<Object> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
