package br.com.fiap.foodshare.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FS_T_SOLICITACAO")
@Data
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITACAO")
    private Long idSolicitacao;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Receptor receptor;

    @Column(name = "DT_SOLICITACAO")
    private LocalDateTime dataSolicitacao;


    @OneToMany(mappedBy = "solicitacao")
    private List<SolicitacaoProduto> solicitacaoProduto;


    @Column(name = "DS_STATUS")
    private String status;
}