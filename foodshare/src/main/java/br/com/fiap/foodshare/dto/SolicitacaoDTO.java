package br.com.fiap.foodshare.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SolicitacaoDTO {

    private Long id;

    @NotNull(message = "Id cliente é obrigatório")
    private Long cliente;

    @NotNull
    private List<SolicitacaoProdutoDTO> solicitacoesProduto;

    private LocalDateTime data;
    
}
