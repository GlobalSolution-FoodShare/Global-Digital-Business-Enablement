package br.com.fiap.foodshare.dto.responseDTO;

import br.com.fiap.foodshare.models.Feedback;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class FeedbackResponseDTO {

    private Long id;

    @NotNull
    @Size(min = 0, max = 5, message = "A nota deve ser entre 0 e 5")
    private Integer nota;

    @NotNull(message = "ID da Doacao é obrigátorio")
    private Long doacao;

    @NotNull(message = "ID da solicitação é obrigátorio")
    private Long solicitacao;

    @NotNull
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;


    public FeedbackResponseDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.nota = feedback.getNota();
        this.doacao = feedback.getDoacao().getId();
        this.solicitacao = feedback.getSolicitacao().getId();
        this.descricao = feedback.getDescricao();
    }

}
