package br.com.fiap.foodshare.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FS_T_SOLICITACAO_PRODUTO")
@Data
public class SolicitacaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITACAO_PRODUTO")
    private Long idSolicitacaoProduto;

    @ManyToOne
    @JoinColumn(name = "ID_SOLICITACAO")
    private Solicitacao solicitacao;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @Column(name = "QTD_PRODUTO")
    private Integer quantidadeProduto;
}
