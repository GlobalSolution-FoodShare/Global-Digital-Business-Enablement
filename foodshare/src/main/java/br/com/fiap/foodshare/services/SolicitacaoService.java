package br.com.fiap.foodshare.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.foodshare.dto.SolicitacaoDTO;
import br.com.fiap.foodshare.dto.responseDTO.SolicitacaoResponseDTO;
import br.com.fiap.foodshare.models.enums.Status;

public interface SolicitacaoService {

    
    Page<SolicitacaoResponseDTO> buscarTodos(Pageable pageable);

    SolicitacaoResponseDTO buscarPorId(Long id);

    SolicitacaoResponseDTO cadastrar(SolicitacaoDTO solicitacao);

    SolicitacaoResponseDTO atualizar(Long id, SolicitacaoDTO solicitacao);

    Page<SolicitacaoResponseDTO> buscarSolicitacoesPorIdCliente(Long idCliente, Pageable pageable);
    
	SolicitacaoResponseDTO atualizarStatus(Long id, Status novoStatus);
    void deletar(Long id);

    
}
