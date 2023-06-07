package br.com.fiap.foodshare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.fiap.foodshare.dto.SolicitacaoDTO;
import br.com.fiap.foodshare.dto.StatusDTO;
import br.com.fiap.foodshare.dto.responseDTO.SolicitacaoResponseDTO;
import br.com.fiap.foodshare.services.impl.SolicitacaoServiceImpl;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoServiceImpl solicitacaoService;

	@GetMapping
	public ResponseEntity<Page<SolicitacaoResponseDTO>> buscarTodos(Pageable pageable) {
		Page<SolicitacaoResponseDTO> solicitacoes = solicitacaoService.buscarTodos(pageable);
		return ResponseEntity.ok(solicitacoes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SolicitacaoResponseDTO> buscarPorId(@PathVariable Long id) {
		SolicitacaoResponseDTO solicitacao = solicitacaoService.buscarPorId(id);
		return ResponseEntity.ok(solicitacao);
	}

	@PostMapping
	public ResponseEntity<SolicitacaoResponseDTO> cadastrar(@RequestBody SolicitacaoDTO solicitacaoDTO) {
		SolicitacaoResponseDTO solicitacao = solicitacaoService.cadastrar(solicitacaoDTO);
		return ResponseEntity.ok(solicitacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SolicitacaoResponseDTO> atualizar(@PathVariable Long id,
			@RequestBody SolicitacaoDTO solicitacaoDTO) {
		SolicitacaoResponseDTO solicitacao = solicitacaoService.atualizar(id, solicitacaoDTO);
		return ResponseEntity.ok(solicitacao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		solicitacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<SolicitacaoResponseDTO> atualizarStatus(@PathVariable Long id,
			@RequestBody StatusDTO statusDto) {
		SolicitacaoResponseDTO solicitacao = solicitacaoService.atualizarStatus(id, statusDto.getStatus());
		return ResponseEntity.ok(solicitacao);
	}

	  @GetMapping("/cliente={id}")
	    public List<SolicitacaoResponseDTO> buscarSolicitacoesPorIdCliente(@PathVariable("id") Long id) {
	        return solicitacaoService.buscarSolicitacoesPorIdCliente(id);
	    }
}
