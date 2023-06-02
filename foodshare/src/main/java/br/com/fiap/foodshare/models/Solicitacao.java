package br.com.fiap.foodshare.models;

import java.time.LocalDateTime;
import java.util.List;

import br.com.fiap.foodshare.models.enums.Status;
import jakarta.persistence.CascadeType;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Receptor receptor;

    @Column(name = "DT_SOLICITACAO")
    private LocalDateTime data;

    @OneToMany(mappedBy = "solicitacao", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SolicitacaoProduto> solicitacaoProduto;

    @Column(name = "DS_STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return "Solicitacao [idSolicitacao=" + id + ", receptor=" + receptor + ", data=" + data
                + ", solicitacaoProduto=" + solicitacaoProduto + ", status=" + status + "]";
    }

}