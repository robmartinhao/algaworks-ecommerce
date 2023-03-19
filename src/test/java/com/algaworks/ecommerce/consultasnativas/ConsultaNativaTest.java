package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.Query;
import org.junit.Test;

import java.util.List;

public class ConsultaNativaTest extends EntityManagerTest {

    @Test
    public void passarParamentros() {
        String sql = "select prd_id id , prd_nome nome, prd_descricao descricao, " +
                "prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, " +
                "prd_preco preco, prd_foto foto " +
                "from ecm_produto where prd_id = :id";
        Query nativeQuery = entityManager.createNativeQuery(sql, Produto.class);
        nativeQuery.setParameter("id", 201);

        List<Produto> lista = nativeQuery.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())
        ));
    }

    @Test
    public void executarSQLRetornandoEntidade03() {
        String sql = "select id , nome, descricao, " +
                "null data_criacao, null data_ultima_atualizacao, " +
                "preco, null foto " +
                "from erp_produto";
        Query nativeQuery = entityManager.createNativeQuery(sql, Produto.class);
        List<Produto> lista = nativeQuery.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())
        ));
    }

    @Test
    public void executarSQLRetornandoEntidade02() {
        String sql = "select prd_id id , prd_nome nome, prd_descricao descricao, " +
                "prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, " +
                "prd_preco preco, prd_foto foto " +
                "from ecm_produto";
        Query nativeQuery = entityManager.createNativeQuery(sql, Produto.class);
        List<Produto> lista = nativeQuery.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())
        ));
    }

    @Test
    public void executarSQLRetornandoEntidade() {
        String sql = "select id , nome,  descricao, " +
                "data_criacao, data_ultima_atualizacao, " +
                "preco, foto " +
                "from produto_loja";
        Query nativeQuery = entityManager.createNativeQuery(sql, Produto.class);
        List<Produto> lista = nativeQuery.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())
        ));
    }

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
