package br.com.fiap.foodshare.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.foodshare.dto.FeedbackDTO;
import br.com.fiap.foodshare.dto.responseDTO.FeedbackResponseDTO;

public interface FeedbackService {

    Page<FeedbackResponseDTO> buscarTodos(Pageable pageable);

    FeedbackResponseDTO buscarPorId(Long id);

    FeedbackResponseDTO cadastrar(FeedbackDTO doacao);

    FeedbackResponseDTO atualizar(Long id, FeedbackDTO doacao);

    void deletar(Long id);

}
