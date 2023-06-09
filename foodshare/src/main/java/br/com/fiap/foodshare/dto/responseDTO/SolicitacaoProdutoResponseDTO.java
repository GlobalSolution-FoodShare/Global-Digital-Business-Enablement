package br.com.fiap.foodshare.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.fiap.foodshare.models.Produto;
import br.com.fiap.foodshare.models.SolicitacaoProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SolicitacaoProdutoResponseDTO {

    private Long id;
    private Produto produto;
    private Integer quantidade;
    private Boolean jaFoiDoado;

    public SolicitacaoProdutoResponseDTO(SolicitacaoProduto solicitacaoProduto) {
        this.id = solicitacaoProduto.getId();
        this.produto = solicitacaoProduto.getProduto();
        this.quantidade = solicitacaoProduto.getQuantidade();
    }

}
