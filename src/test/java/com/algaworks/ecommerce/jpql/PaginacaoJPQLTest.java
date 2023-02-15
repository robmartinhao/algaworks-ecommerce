package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PaginacaoJPQLTest extends EntityManagerTest {

    @Test
    public void paginarResultados() {
        String jpql = "select c from Categoria c order by c.id";

        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);

        // FIRST_RESULT = MAX_RESULTS * (pagina -1)
        typedQuery.setFirstResult(1);
        typedQuery.setMaxResults(2);

        List<Categoria> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }
}
