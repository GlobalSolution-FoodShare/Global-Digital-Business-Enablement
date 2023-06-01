package br.com.fiap.foodshare.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "FS_T_FEEDBACK")
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FEEDBACK")
    private Long id;

    @Column(name = "NR_NOTA")
    @NotNull
    @Min(0)
    @Max(5)
    private Integer nota;

    @OneToOne
    @JoinColumn(name = "ID_DOACAO")
    private Doacao doacao;

    @OneToOne
    @JoinColumn(name = "ID_SOLICITACAO")
    private Solicitacao solicitacao;

    @Column(name = "DS_FEEDBACK")
    private String descricao;
}
