package br.com.fiap.foodshare.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.foodshare.dto.ClienteDTO;
import br.com.fiap.foodshare.dto.responseDTO.ClienteResponseDTO;

public interface ClienteService {

    Page<ClienteResponseDTO> buscarTodos(Pageable pageable);

    ClienteResponseDTO buscarPorId(Long id);

    ClienteResponseDTO cadastrar(ClienteDTO cliente);

    ClienteResponseDTO update(Long id, ClienteDTO cliente);

    void delete(Long id);

}
