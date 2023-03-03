package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BasicoCriteriaTest extends EntityManagerTest {

    @Test
    public void buscaPorIdentificador() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

//        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> query = entityManager
                //.createQuery(jpql, Pediddo.class);
                .createQuery(criteriaQuery);

        Pedido pedido = query.getSingleResult();
        Assert.assertNotNull(pedido);

        List<Pedido> listaPedidos = query.getResultList();
        Assert.assertFalse(listaPedidos.isEmpty());

        System.out.println(pedido.getId() + " - " + pedido.getStatus());
    }
}
