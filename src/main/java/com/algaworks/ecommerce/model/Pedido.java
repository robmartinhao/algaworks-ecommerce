package com.algaworks.ecommerce.model;


import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import com.algaworks.ecommerce.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedido")
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
public class Pedido extends EntidadeBaseInteger {

    @ManyToOne(optional = false/*, cascade = CascadeType.PERSIST*/)
    @JoinColumn(name = "cliente_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido"/*, cascade = CascadeType.REMOVE*/)
    private List<ItemPedido> itens;

    @Column(name = "data_criacao", updatable = false , nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    public boolean isPago() {
        return status.equals(StatusPedido.PAGO);
    }

    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(
                        i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }

    @PrePersist
    public void aoPersistir() {
        this.dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        this.dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}