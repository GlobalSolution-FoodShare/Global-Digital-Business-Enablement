package br.com.fiap.foodshare.dto.responseDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitacaoProdutoResponseDTO {

    private Long id;

    @NotNull(message = "Numero solicitação é obrigatório")
    private Long solicitacao;

    @NotNull(message = "Produto é obrigatório")
    private ProdutoResponseDTO produto;

    @NotNull(message = "quantidade é obrigatório")
    private Integer quantidade;

}
