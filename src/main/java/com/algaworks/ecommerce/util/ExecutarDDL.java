package com.algaworks.ecommerce.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class ExecutarDDL {
    public static void main(String[] args) {
        Map<String, String> propriedades = new HashMap<>();

        //Comentar esse linha de configuração no arquivo persistence.xml para funcionar
        propriedades.put("jakarta.persistence.jdbc.url",
                "jdbc:mysql://localhost/loja_ecommerce?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC");

        propriedades.put("javax.persistence.schema-generation.database.action",
                "drop-and-create");

        propriedades.put("javax.persistence.schema-generation.create-source",
                "metadata-then-script");
        propriedades.put("javax.persistence.schema-generation.drop-source",
                "metadata-then-script");

        propriedades.put("javax.persistence.schema-generation.create-script-source",
                "META-INF/banco-de-dados/script-criacao.sql");
        propriedades.put("javax.persistence.schema-generation.drop-script-source",
                "META-INF/banco-de-dados/script-remocao.sql");

        propriedades.put("javax.persistence.sql-load-script-source",
                "META-INF/banco-de-dados/dados-iniciais.sql");

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU", propriedades);

        entityManagerFactory.close();
    }
}
