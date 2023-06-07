package br.com.fiap.foodshare.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.foodshare.dto.FeedbackDTO;
import br.com.fiap.foodshare.dto.responseDTO.FeedbackResponseDTO;
import br.com.fiap.foodshare.exception.BadRequestException;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Doacao;
import br.com.fiap.foodshare.models.Feedback;
import br.com.fiap.foodshare.models.Solicitacao;
import br.com.fiap.foodshare.repository.DoacaoRepository;
import br.com.fiap.foodshare.repository.FeedbackRepository;
import br.com.fiap.foodshare.repository.SolicitacaoRepository;
import br.com.fiap.foodshare.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Override
    public Page<FeedbackResponseDTO> buscarTodos(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        return feedbacks.map(feedback -> new FeedbackResponseDTO(feedback));
    }

    @Override
    public FeedbackResponseDTO buscarPorId(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Feedback não encontrado com o id: " + id));
        return new FeedbackResponseDTO(feedback);
    }

    @Override
    public FeedbackResponseDTO cadastrar(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setNota(feedbackDTO.getNota());
        feedback.setDescricao(feedbackDTO.getDescricao());

        // Verificar se a doação existe
        Doacao doacao = doacaoRepository.findById(feedbackDTO.getIdDoacao())
                .orElseThrow(() -> new RestNotFoundException(
                        "Doação não encontrada com o id: " + feedbackDTO.getIdDoacao()));

        Solicitacao solicitacao = solicitacaoRepository.findById(feedbackDTO.getIdSolicitacao())
                .orElseThrow(() -> new RestNotFoundException(
                        "Doação não encontrada com o id: " + feedbackDTO.getIdDoacao()));

        boolean existsFeedback = feedbackRepository.existsBySolicitacaoIdOrDoacaoId(
                feedbackDTO.getIdSolicitacao(), feedbackDTO.getIdDoacao());

        if (existsFeedback) {
            throw new BadRequestException("Já existe um feedback cadastrado para a solicitação E doação.");
        }

        feedback.setDoacao(doacao);
        feedback.setSolicitacao(solicitacao);

        Feedback novoFeedback = feedbackRepository.save(feedback);
        return new FeedbackResponseDTO(novoFeedback);
    }

    @Override
    public FeedbackResponseDTO atualizar(Long id, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Feedback não encontrado com o id: " + id));
        feedback.setNota(feedbackDTO.getNota());
        feedback.setDescricao(feedbackDTO.getDescricao());

        // Verificar se a doação existe
        Doacao doacao = doacaoRepository.findById(feedbackDTO.getIdDoacao())
                .orElseThrow(() -> new RestNotFoundException(
                        "Doação não encontrada com o id: " + feedbackDTO.getIdDoacao()));

        Solicitacao solicitacao = solicitacaoRepository.findById(feedbackDTO.getIdSolicitacao())
                .orElseThrow(() -> new RestNotFoundException(
                        "Solicitação não encontrada com o id: " + feedbackDTO.getIdSolicitacao()));

        feedback.setDoacao(doacao);
        feedback.setSolicitacao(solicitacao);

        Feedback feedbackAtualizado = feedbackRepository.save(feedback);
        return new FeedbackResponseDTO(feedbackAtualizado);
    }

    @Override
    public void deletar(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Feedback não encontrado com o id: " + id));
        feedbackRepository.delete(feedback);
    }

}
