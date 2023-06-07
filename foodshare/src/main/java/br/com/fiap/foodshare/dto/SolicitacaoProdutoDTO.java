package br.com.fiap.foodshare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitacaoProdutoDTO {

    private Long id;

    @NotNull(message = "Numero solicitação é obrigatório")
    private Long solicitacao;

    @NotNull(message = "Produto é obrigatório")
    private ProdutoDTO produto;

    @NotNull(message = "quantidade é obrigatório")
    private Integer quantidade;

}
