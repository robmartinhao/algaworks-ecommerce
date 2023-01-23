package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners(GenericoListener.class)
@Entity
@Table(name = "produto",
    uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames ={ "nome"} ) },
    indexes = { @Index(name = "idx_nome", columnList = "nome") }
)
public class Produto extends EntidadeBaseInteger {

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(length = 100, nullable = false) // nome varchar(100) not null
    private String nome;

    @Lob
    private String descricao;

    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            foreignKey = @ForeignKey(name = "fk_produto_categoria_produto"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"),
            inverseForeignKey = @ForeignKey(name = "fk_produto_categoria_categoria"))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] foto;

    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produtoId"),
            foreignKey = @ForeignKey(name = "fk_produto_tag_produto"))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id"),
            foreignKey = @ForeignKey(name = "fk_produto_atributo_produto"))
    private List<Atributo> atributos;

}
