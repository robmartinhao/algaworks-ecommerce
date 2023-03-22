package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class EntityGraphTest extends EntityManagerTest {

    @Test
    public void buscarAtributosEssenciaisDePedido() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("dataCriacao", "status", "total", "notaFiscal");

//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("jakarta.persistence.fetchgraph", entityGraph);
////        properties.put("jakarta.persistence.loadgraph", entityGraph);
//
//        Pedido pedido = entityManager.find(Pedido.class, 1, properties);
//        Assert.assertNotNull(pedido);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
