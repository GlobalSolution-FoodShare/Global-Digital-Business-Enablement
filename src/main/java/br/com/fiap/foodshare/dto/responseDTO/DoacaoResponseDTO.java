package br.com.fiap.foodshare.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.fiap.foodshare.models.Doacao;
import br.com.fiap.foodshare.models.Endereco;
import br.com.fiap.foodshare.models.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DoacaoResponseDTO {
    private Long id;
    private Long cliente;
    private SolicitacaoProdutoResponseDTO solicitacaoProduto;
    private Status status;
    private Long solicitacao;
    private String nomeSolicitante;
    private Endereco endereco;

    public DoacaoResponseDTO(Doacao doacao) {
        this.id = doacao.getId();
        this.cliente = doacao.getDoador().getId();
        this.status = doacao.getStatus();
        this.solicitacaoProduto = new SolicitacaoProdutoResponseDTO(doacao.getSolicitacaoProduto());
        this.solicitacao = doacao.getSolicitacaoProduto().getSolicitacao() != null ? doacao.getSolicitacaoProduto().getSolicitacao().getId() : null;
        this.nomeSolicitante = doacao.getSolicitacaoProduto().getSolicitacao().getReceptor() != null ? doacao.getSolicitacaoProduto().getSolicitacao().getReceptor().getNomeCompleto() : null;
        this.endereco = doacao.getSolicitacaoProduto().getSolicitacao().getReceptor()  != null ? doacao.getSolicitacaoProduto().getSolicitacao().getReceptor().getEndereco() : null;
    }
    


}
