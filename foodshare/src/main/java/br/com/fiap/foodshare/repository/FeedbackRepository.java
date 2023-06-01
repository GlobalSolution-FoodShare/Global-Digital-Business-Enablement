package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Feedback;



public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
    
}
