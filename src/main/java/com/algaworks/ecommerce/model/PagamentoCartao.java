package com.algaworks.ecommerce.model;


import com.algaworks.ecommerce.model.enums.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Column(name = "status")
    private StatusPagamento status;

    private String numero;
}
