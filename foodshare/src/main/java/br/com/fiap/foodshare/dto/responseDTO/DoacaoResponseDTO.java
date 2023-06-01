package br.com.fiap.foodshare.dto.responseDTO;

import java.util.List;

import br.com.fiap.foodshare.dto.SolicitacaoProdutoDTO;
import br.com.fiap.foodshare.models.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoacaoResponseDTO {


    private Long id;

    @NotNull(message = "Para realizar uma doação é obrigatário solicitacaoProduto")
    private List<SolicitacaoProdutoDTO> solicitacaoProduto;

    @NotBlank(message = "O numero do id do cliente é obrigatório") 
    private Long cliente;

    @NotNull(message = "Status é obrigatório!")
    private Status status;
    
    
}
