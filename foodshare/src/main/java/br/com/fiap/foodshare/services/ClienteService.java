package br.com.fiap.foodshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.foodshare.dto.ClienteDTO;
import br.com.fiap.foodshare.dto.responseDTO.ClienteResponseDTO;

public interface ClienteService {

    Page<ClienteResponseDTO> buscarTodos(Pageable pageable);

    ClienteResponseDTO buscarPorId(Long id);

    ClienteResponseDTO cadastrar(ClienteDTO cliente);

    ClienteResponseDTO atualizar(Long id, ClienteDTO cliente);
    
    List<ClienteResponseDTO> buscarPorRaioDistancia(Double latitude, Double longitude, Double raio);

    void deletar(Long id);

}
