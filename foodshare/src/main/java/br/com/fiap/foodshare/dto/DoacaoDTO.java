package br.com.fiap.foodshare.dto;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.fiap.foodshare.models.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoacaoDTO {

    private Long id;

    @NotNull(message = "Para realizar uma doação é obrigatário solicitacaoProduto")
    private List<SolicitacaoProdutoDTO> solicitacaoProduto;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotBlank(message = "O numero do id do cliente é obrigatório")
    private Long cliente;

    @NotNull(message = "Status é obrigatório!")
    private Status status;

}
