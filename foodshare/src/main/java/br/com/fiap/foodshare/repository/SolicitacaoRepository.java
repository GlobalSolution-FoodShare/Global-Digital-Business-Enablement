package br.com.fiap.foodshare.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.foodshare.models.Solicitacao;
import br.com.fiap.foodshare.models.SolicitacaoProduto;



public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{

    @Query("SELECT COUNT(d) > 0 FROM Doacao d WHERE d.solicitacaoProduto = :solicitacaoProduto")
    boolean existsDoacaoBySolicitacaoProduto(SolicitacaoProduto solicitacaoProduto);

    Page<Solicitacao> findAll(Pageable pageable);

    
}
