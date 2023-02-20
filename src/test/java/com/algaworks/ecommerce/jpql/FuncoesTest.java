package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.TimeZone;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncoesData() {
        //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        //current date, current_time, current

        //String jpql = "select year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao) from Pedido p";
        String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second (p.dataCriacao) from Pedido p " +
                "where hour(p.dataCriacao) > 4";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " +  arr[1] + " | " + arr[2]));
    }


    @Test
    public void aplicarFuncaoString() {
        // concat, lenght, locate, substring, lower, upper, trim

        //String jpql = "select c.nome, concat('Categoria:', c.nome) from Categoria c";
        //String jpql = "select c.nome, length(c.nome) from Categoria c";
        //String jpql = "select c.nome, locate('a', c.nome) from Categoria c";
        //String jpql = "select c.nome, substring(c.nome, 1, 3) from Categoria c";
        //String jpql = "select c.nome, lower(c.nome) from Categoria c";
        //String jpql = "select c.nome, upper(c.nome) from Categoria c";


        String jpql = "select c.nome, length(c.nome) from Categoria c where length(c.nome) > 10 ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }
}
