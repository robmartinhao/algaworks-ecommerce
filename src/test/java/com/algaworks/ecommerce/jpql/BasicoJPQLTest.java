package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void projetarResultado() {
        String jpql = "select id, nome from Produto";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertTrue(lista.get(0).length == 2);

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }

    @Test
    public void  selecionarUmAtributoParaRetorno() {
        String jpql = "select p.nome from Produto p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(lista.get(0).getClass()));

        String jpqlCliente = "select p.cliente from Pedido p";
        TypedQuery<Cliente> clienteTypedQuery = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> listaCLientes = clienteTypedQuery.getResultList();
        Assert.assertTrue(Cliente.class.equals(listaCLientes.get(0).getClass()));
    }

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
