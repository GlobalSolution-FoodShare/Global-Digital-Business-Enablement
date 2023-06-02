package br.com.fiap.foodshare.dto.responseDTO;

import br.com.fiap.foodshare.models.Doacao;
import br.com.fiap.foodshare.models.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoacaoResponseDTO {
    private Long id;
    private Long cliente;
    private SolicitacaoProdutoResponseDTO solicitacaoProduto;
    private Status status;

    public DoacaoResponseDTO(Doacao doacao) {
        this.id = doacao.getId();
        this.cliente = doacao.getDoador().getId();
        this.status = doacao.getStatus();
        this.solicitacaoProduto = new SolicitacaoProdutoResponseDTO(doacao.getSolicitacaoProduto());
    }

}
