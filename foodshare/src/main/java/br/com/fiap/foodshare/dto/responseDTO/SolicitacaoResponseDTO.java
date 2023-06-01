package br.com.fiap.foodshare.dto.responseDTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.fiap.foodshare.models.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SolicitacaoResponseDTO {

    private Long id;

    @NotNull(message = "Id cliente é obrigatório")
    private Long idCliente;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime data;

    @NotNull(message = "Status é obrigatório")
    private Status status;
    
}
