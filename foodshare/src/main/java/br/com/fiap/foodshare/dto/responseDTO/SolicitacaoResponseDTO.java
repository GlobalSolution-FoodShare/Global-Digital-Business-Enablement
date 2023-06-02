package br.com.fiap.foodshare.dto.responseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.foodshare.models.Solicitacao;
import br.com.fiap.foodshare.models.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolicitacaoResponseDTO {

    private Long id;
    private Long receptor;
    private LocalDateTime data;
    private List<SolicitacaoProdutoResponseDTO> solicitacaoProduto;
    private Status status;

    public SolicitacaoResponseDTO(Solicitacao solicitacao) {
        this.id = solicitacao.getId();
        this.receptor = solicitacao.getReceptor().getId();
        this.data = solicitacao.getData();
        this.solicitacaoProduto = solicitacao.getSolicitacaoProduto().stream()
                .map(SolicitacaoProdutoResponseDTO::new)
                .collect(Collectors.toList());
        this.status = solicitacao.getStatus();
    }

}
