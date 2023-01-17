package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class HerancaTest extends EntityManagerTest {

    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Fernanda Morais");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVeriricacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVeriricacao.getId());
    }
}
