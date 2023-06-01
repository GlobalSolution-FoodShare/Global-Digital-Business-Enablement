package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Doacao;



public interface DoacaoRepository extends JpaRepository<Doacao, Long>{
    
    
}
