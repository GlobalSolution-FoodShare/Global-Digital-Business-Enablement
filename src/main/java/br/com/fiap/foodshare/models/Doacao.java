package br.com.fiap.foodshare.models;

import java.time.LocalDateTime;

import br.com.fiap.foodshare.models.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FS_T_DOACAO")
@Data
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOACAO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Doador doador;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SOLICITACAO_PRODUTO")
    private SolicitacaoProduto solicitacaoProduto;

    @Column(name = "DT_DOACAO")
    private LocalDateTime data;

    @Column(name = "DS_STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return "Doacao [id=" + id + ", solicitacaoProduto=" + solicitacaoProduto + ", data=" + data + ", status="
                + status + "]";
    }
    

}