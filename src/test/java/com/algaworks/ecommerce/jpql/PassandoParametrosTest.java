package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPagamento;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class PassandoParametrosTest extends EntityManagerTest {

    @Test
    public void passarParametroDate() {
        String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> query = entityManager.createQuery(jpql, NotaFiscal.class);
        query.setParameter(1, new Date(), TemporalType.TIMESTAMP);

        final List<NotaFiscal> lista = query.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }

    @Test
    public void passarParametro() {
        String jpql = "select p from Pedido p join p.pagamento pag " +
                "where p.id = :pedidoId and pag.status = ?1";

        TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
        query.setParameter("pedidoId", 2);
        query.setParameter(1, StatusPagamento.PROCESSANDO);

        List<Pedido> lista = query.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }
}
