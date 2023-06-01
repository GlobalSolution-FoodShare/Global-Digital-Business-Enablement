package br.com.fiap.foodshare.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class FeedbackDTO {

    private Long id;

    @NotNull
    @Size(min = 0, max = 5, message = "A nota deve ser entre 0 e 5")
    private Integer nota;

    @NotNull(message = "ID da Doacao é obrigátorio")
    private Long idDoacao;

    @NotNull(message = "ID da solicitação é obrigátorio")
    private Long idSolicitacao;

    @NotNull
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;

}
