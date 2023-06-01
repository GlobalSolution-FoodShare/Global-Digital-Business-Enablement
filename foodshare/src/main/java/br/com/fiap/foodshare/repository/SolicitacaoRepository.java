package br.com.fiap.foodshare.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.Solicitacao;



public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
    
}
