package br.com.fiap.foodshare.dto;

import br.com.fiap.foodshare.models.Feedback;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FeedbackDTO {

    @NotNull
    @Min(value = 0, message = "A nota deve ser no mínimo 0")
    @Max(value = 5, message = "A nota deve ser no máximo 5")
    private Integer nota;

    @NotNull(message = "ID da Doacao é obrigátorio")
    private Long idDoacao;

    @NotNull(message = "ID da solicitação é obrigátorio")
    private Long idSolicitacao;

    @NotNull
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;

    public FeedbackDTO(Feedback feedback) {
        this.nota = feedback.getNota();
        this.idDoacao = feedback.getDoacao().getId();
        this.idSolicitacao = feedback.getSolicitacao().getId();
        this.descricao = feedback.getDescricao();
    }


    

}
