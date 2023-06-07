package br.com.fiap.foodshare.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import br.com.fiap.foodshare.dto.ProdutoDTO;
import br.com.fiap.foodshare.dto.responseDTO.ProdutoResponseDTO;

public interface ProdutoService {

    Page<ProdutoResponseDTO> buscarTodos(Pageable pageable);

    ProdutoResponseDTO buscarPorId(Long id);

    ProdutoResponseDTO cadastrar(ProdutoDTO produto);

    ProdutoResponseDTO atualizar(Long id, ProdutoDTO produto);

    void deletar(Long id);
    
}
