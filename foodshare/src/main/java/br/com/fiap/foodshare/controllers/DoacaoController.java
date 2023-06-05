package br.com.fiap.foodshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodshare.dto.DoacaoDTO;
import br.com.fiap.foodshare.dto.StatusDTO;
import br.com.fiap.foodshare.dto.responseDTO.DoacaoResponseDTO;
import br.com.fiap.foodshare.services.DoacaoService;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<Page<DoacaoResponseDTO>> buscarTodos(Pageable pageable) {
        Page<DoacaoResponseDTO> doacoes = doacaoService.buscarTodos(pageable);
        return ResponseEntity.ok(doacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        DoacaoResponseDTO doacao = doacaoService.buscarPorId(id);
        return ResponseEntity.ok(doacao);
    }

    @PostMapping
    public ResponseEntity<DoacaoResponseDTO> cadastrar(@RequestBody DoacaoDTO doacaoDTO) {
        DoacaoResponseDTO novaDoacao = doacaoService.cadastrar(doacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoacaoResponseDTO> atualizar(@PathVariable Long id, @RequestBody DoacaoDTO doacaoDTO) {
        DoacaoResponseDTO doacaoAtualizada = doacaoService.atualizar(id, doacaoDTO);
        return ResponseEntity.ok(doacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        doacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DoacaoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestBody StatusDTO novoStatus) {
        DoacaoResponseDTO doacaoAtualizada = doacaoService.atualizarStatus(id, novoStatus.getStatus());
        return ResponseEntity.ok(doacaoAtualizada);
    }
}
