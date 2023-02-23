package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.TimeZone;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoAgregacao() {
        //avg, count, min, max, sum

        String jpql = "select sum(p.total) from Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(System.out::println);
    }

    @Test
    public void aplicarFuncoesNativas() {

//        String jpql = "select p from Pedido p where function('acima_media_faturamento', p.total) = 1";
        String jpql = "select function('dayname', p.dataCriacao) from Pedido p where function('acima_media_faturamento', p.total) = 1";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicarFuncoesColecao() {

        String jpql = "select size(p.itens) from Pedido p where size(p.itens) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);
        List<Integer> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(size -> System.out.println(size));
    }

    @Test
    public void aplicarFuncoesNumero() {

        //String jpql = "select abs(-10), mod(3, 2), sqrt(9) from Pedido p";
        String jpql = "select abs(p.total), mod(p.id, 2), sqrt(p.total) from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " +  arr[1] + " | " + arr[2]));
    }

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
