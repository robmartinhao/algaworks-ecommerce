package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import com.algaworks.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {

        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos");

        Categoria categoria = new Categoria();
        categoriaPai.setNome("Celulares");
        categoria.setCategoriaPai(categoriaPai);

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.persist(categoriaPai);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaVerificacao.getCategoriaPai());

        Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
        Assert.assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());
    }
}