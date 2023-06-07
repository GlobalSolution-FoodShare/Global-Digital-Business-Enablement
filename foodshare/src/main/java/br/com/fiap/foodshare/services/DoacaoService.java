package br.com.fiap.foodshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.foodshare.dto.DoacaoDTO;
import br.com.fiap.foodshare.dto.responseDTO.DoacaoResponseDTO;
import br.com.fiap.foodshare.models.enums.Status;

public interface DoacaoService {

    Page<DoacaoResponseDTO> buscarTodos(Pageable pageable);

    DoacaoResponseDTO buscarPorId(Long id);

    DoacaoResponseDTO cadastrar(DoacaoDTO doacao);

    DoacaoResponseDTO atualizar(Long id, DoacaoDTO doacao);

    DoacaoResponseDTO atualizarStatus(Long id, Status novoStatus);
    
    List<DoacaoResponseDTO> buscarDoacoesPorIdCliente(Long idCliente);

    void deletar(Long id);

}
