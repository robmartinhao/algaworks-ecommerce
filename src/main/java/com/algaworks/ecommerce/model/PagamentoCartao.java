package com.algaworks.ecommerce.model;


import com.algaworks.ecommerce.model.enums.StatusPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class PagamentoCartao {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private Integer pedidoId;
    private StatusPagamento statusPagamento;
    private String numero;
}
