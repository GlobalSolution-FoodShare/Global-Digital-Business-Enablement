package br.com.fiap.foodshare.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.foodshare.models.Feedback;



public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

    Page<Feedback> findAll(Pageable pageable);

    @Query("SELECT COUNT(f) > 0 FROM Feedback f WHERE f.solicitacao.id = :solicitacaoId OR f.doacao.id = :doacaoId")
    boolean existsBySolicitacaoIdOrDoacaoId(Long solicitacaoId,Long doacaoId);

    
}
