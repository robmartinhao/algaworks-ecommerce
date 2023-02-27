package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void agruparEFiltrarResultado() {

        //         Total de vendas por categoria.
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                "join ip.produto pro " +
//                "join pro.categorias c " +
//                "join ip.pedido p " +
//                "where year(p.dataCriacao) = year(current date) and month(p.dataCriacao) = month(current_date)" +
//                "group by c.id";

        //         Total de vendas por cliente.
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
                "join ip.pedido p " +
                "join p.cliente c " +
                "where year(p.dataCriacao) = year(current date) and month(p.dataCriacao) >= (month(current_date) -3)" +
                "group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }


    @Test
    public void agruparResultado() {

        //         Quantidade de produtos por categoria.
        //        String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";


        //         Total de vendas por categoria.
        //        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro " +
        //                 "join pro.categorias c " +
        //                "group by c.id";

        //         Total de vendas por cliente.
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.pedido p " +
                "join p.cliente c " +
                "group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }
}
