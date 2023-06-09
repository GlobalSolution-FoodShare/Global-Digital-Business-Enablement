package br.com.fiap.foodshare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.foodshare.dto.FeedbackDTO;
import br.com.fiap.foodshare.dto.responseDTO.FeedbackResponseDTO;
import br.com.fiap.foodshare.services.FeedbackService;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<Page<FeedbackResponseDTO>> buscarTodos(Pageable pageable) {
        Page<FeedbackResponseDTO> feedbacks = feedbackService.buscarTodos(pageable);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> buscarPorId(@PathVariable Long id) {
        FeedbackResponseDTO feedback = feedbackService.buscarPorId(id);
        return ResponseEntity.ok(feedback);
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> cadastrar(@RequestBody @Validated FeedbackDTO feedbackDTO) {
        FeedbackResponseDTO novoFeedback = feedbackService.cadastrar(feedbackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFeedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody @Validated FeedbackDTO feedbackDTO) {
        FeedbackResponseDTO feedbackAtualizado = feedbackService.atualizar(id, feedbackDTO);
        return ResponseEntity.ok(feedbackAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        feedbackService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
