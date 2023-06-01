package br.com.fiap.foodshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.foodshare.models.SolicitacaoProduto;



public interface SolicitacaoProdutoRepository extends JpaRepository<SolicitacaoProduto, Long>{
    
    
}

