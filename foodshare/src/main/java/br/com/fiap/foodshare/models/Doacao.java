package br.com.fiap.foodshare.models;

import java.time.LocalDateTime;

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
@Table(name = "FS_T_DOACAO")
@Data
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOACAO")
    private Long idDoacao;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "ID_SOLICITACAO_PRODUTO")
    private SolicitacaoProduto solicitacaoProduto;

    @Column(name = "DT_DOACAO")
    private LocalDateTime dataDoacao;

    @Column(name = "DS_STATUS")
    private String status;
}