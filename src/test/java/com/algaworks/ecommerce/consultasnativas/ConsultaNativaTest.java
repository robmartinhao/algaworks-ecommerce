package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.Query;
import org.junit.Test;

import java.util.List;

public class ConsultaNativaTest extends EntityManagerTest {

    @Test
    public void executarSQL() {
        String sql = "select id, nome from produto";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> lista = nativeQuery.getResultList();

        lista.stream().forEach(arr -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", arr[0], arr[1])
        ));
    }
}
