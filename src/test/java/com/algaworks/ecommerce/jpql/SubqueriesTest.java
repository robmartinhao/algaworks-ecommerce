package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SubqueriesTest extends EntityManagerTest {

    @Test
    public void pesquisarComAllExercicio() {

        // Todos os produtos que foram vendidos pelo mesmo preço
//        String jpql = "select distinct pro from ItemPedido itp " +
//                "join itp.produto pro " +
//                "where itp.precoProduto = ALL (select precoProduto from ItemPedido where precoProduto = pro.preco and id <> itp.id)";
//
//        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
//
//        List<Produto> lista = typedQuery.getResultList();
//        Assert.assertFalse(lista.isEmpty());
//
//        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComAny() {

        // Todos os produtos já foram vendidos por um preço diferente do atual
        String jpql = "select p from Produto p where " +
                "p.preco <> ANY " +
//                "ou SOME no lugar do ANY" +
                " (select precoProduto from ItemPedido  where produto = p)";

        // Todos os produtos que já foram vendidos, pelo menos, uma vez pelo preço atual
//        String jpql = "select p from Produto p where " +
//                "p.preco = ANY (select precoProduto from ItemPedido where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComAll() {

        // Todos os produtos não foram vendidos mais depois que encarecem
        String jpql = "select p from Produto p where " +
                "p.preco > ALL (select precoProduto from ItemPedido  where produto = p)";
        // Ou
//        String jpql = "select p from Produto p where " +
//                "p.preco > (select max(precoProduto) from ItemPedido  where produto = p)";

        // Todos os produtos que sempre foram vendidos pelo preço atual
//        String jpql = "select p from Produto p where " +
//                "p.preco = ALL (select precoProduto from ItemPedido  where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueriesComExistsExercicio() {

        String jpql = "select c from Cliente c where " +
                " (select count(cliente) from Pedido where cliente = c) >= 2";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueriesExercicio() {

        String jpql = "select p from Produto p " +
                "where exists " +
                "(select 1 from ItemPedido where produto = p and precoProduto <> p.preco)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComINExercicio() {
        String jpql = "select p from Pedido p where p.id in " +
                " (select p2.id from ItemPedido i2 " +
                "      join i2.pedido p2 join i2.produto pro2 join pro2.categorias c2 where c2.id = 2)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComExists() {

        final String jpql = "select p from Produto p " +
                "where exists (select 1 from ItemPedido ip2 " +
                "join ip2.produto p2 where p2 = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComIN() {

        final String jpql = "select p from Pedido p where p.id " +
                "in (select p2.id from ItemPedido i2 " +
                "join i2.pedido p2 " +
                "join i2.produto pro2 " +
                "where pro2.preco > 100) ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueries() {

        // Bons clientes. Versão 2
        final String jpql = "select c from Cliente c where " +
                "100 < (select sum(p.total) from Pedido p where p.cliente = c)";

        // Bons clientes. Versão 1
//        final String jpql = "select c from Cliente c where " +
//                "100 < (select sum(p.total) from c.pedidos p)";

        // Todos os pedidos acima da média de vendas
//        final String jpql = "select p from Pedido p where " +
//                "p.total > (select avg(total) from Pedido )";

        // O produto ou os produtos mais caros da base.
//        final String jpql = "select p from Produto p where " +
//                "p.preco = (select max(preco) from Produto)";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId() + ", Nome: " + obj.getNome()));
    }
}
