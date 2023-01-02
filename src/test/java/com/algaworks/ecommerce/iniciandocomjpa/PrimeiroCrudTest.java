package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void insereCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Daniel");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }

    @Test
    public void atualizaCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Almir Perereira");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteverificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Almir Perereira", clienteverificacao.getNome());
    }

    @Test
    public void buscaCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertEquals(1, cliente.getId().intValue());
        Assert.assertEquals("Osvaldo", cliente.getNome());
    }

    @Test
    public void removeCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(clienteVerificacao);
    }
}
