package br.com.fiap.foodshare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodshare.dto.ClienteDTO;
import br.com.fiap.foodshare.dto.responseDTO.ClienteResponseDTO;
import br.com.fiap.foodshare.services.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<Page<ClienteResponseDTO>> getAllClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClienteResponseDTO> pageResult = clienteService.buscarTodos(pageable);
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getCliente(@PathVariable Long id) {
        ClienteResponseDTO clienteDTO = clienteService.buscarPorId(id);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.cadastrar(clienteDTO);
        return new ResponseEntity<>(clienteResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.atualizar(id, clienteDTO);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @GetMapping("/raio")
    public ResponseEntity<List<ClienteResponseDTO>> buscarClientesNoRaio(@RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double raio) {
        List<ClienteResponseDTO> clientesNoRaio = clienteService.buscarPorRaioDistancia(latitude, longitude, raio);

        return ResponseEntity.ok(clientesNoRaio);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {

        clienteService.deletar(id);

    }

}
