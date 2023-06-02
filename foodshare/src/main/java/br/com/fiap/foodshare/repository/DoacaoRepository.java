package br.com.fiap.foodshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.foodshare.models.Doacao;
import br.com.fiap.foodshare.models.Doador;
import br.com.fiap.foodshare.models.SolicitacaoProduto;


public interface DoacaoRepository extends JpaRepository<Doacao, Long>{
    
    Page<Doacao> findAll(Pageable pageable);


    @Query("SELECT COUNT(d) > 0 FROM Doacao d WHERE d.doador = :doador AND d.solicitacaoProduto = :solicitacaoProduto")
    boolean existsByDoadorAndSolicitacaoProduto(Doador doador,  SolicitacaoProduto solicitacaoProduto);

}
