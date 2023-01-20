package com.algaworks.ecommerce.model;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("cartao")
//@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    @Column(name = "numero_cartao", length = 50, nullable = false)
    private String numeroCartao;
}
