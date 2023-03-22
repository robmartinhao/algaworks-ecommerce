package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Test;

import java.util.Set;


public class ValidacaoTest extends EntityManagerTest {

    @Test
    public void validarCliente() {
        Cliente cliente = new Cliente();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);

        for (ConstraintViolation<Cliente> violation : violations) {
            System.out.println((violation.getMessage()));
        }

        entityManager.getTransaction().begin();

        entityManager.merge(cliente);

        entityManager.getTransaction().commit();
    }
}
